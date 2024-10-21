package com.example.application.service.impl;

import com.example.application.dto.response.ApiResponseDTO;
import com.example.application.dto.response.OAuth2ExchangeEmailDTO;
import com.example.application.enums.Status;
import com.example.application.model.RoleEntity;
import com.example.application.model.UserEntity;
import com.example.application.repository.RoleRepository;
import com.example.application.repository.UserRepository;
import com.example.application.service.IdentityOauth2Service;
import com.example.application.service.UserService;
import com.example.application.util.jwt.JWTokenAuthenticationUtil;
import com.example.application.util.jwt.impl.BasicJWTokenAuthenticationUtil;
import com.example.application.util.map.GrantedUserUtil;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.net.URI;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class IdentityOauth2ServiceImpl implements IdentityOauth2Service {
    private final RestOperations restTemplate;
    private final UserService userService;
    private final UserRepository userRepository;
    private final JWTokenAuthenticationUtil jwTokenAuthenticationUtil = new BasicJWTokenAuthenticationUtil();
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    @Autowired
    public IdentityOauth2ServiceImpl(RestOperations restTemplate, UserService userService, UserRepository userRepository, AuthenticationManager authenticationManager, RoleRepository roleRepository) {
        this.restTemplate = restTemplate;
        this.userService = userService;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
    }

    @Override
    public ApiResponseDTO<?> doAuthenticateInternal(OAuth2AuthorizedClient authorizedClient) {
        String provider = authorizedClient.getClientRegistration().getRegistrationId();
        //exchange email & check first login &  save client with default role
        String email = exchangeEmail(authorizedClient.getAccessToken().getTokenValue(), provider);
        UserEntity userEntity = userRepository.findByEmailAndProvider(email, authorizedClient.getClientRegistration().getClientId())
                .orElse(UserEntity.builder().email(email).provider(provider).build());
        if (userEntity.getId() == null) {
            userRepository.save(userEntity);
        }
        RoleEntity role = roleRepository.findRoleByCode("MANAGER");
        userEntity.addRole(role);
        userEntity = userRepository.save(userEntity);
        //Internal Authentication
        Authentication authRequest = new UsernamePasswordAuthenticationToken(userEntity.getId(), userEntity.getPassword(), GrantedUserUtil.toGrantedAuthorities(userEntity.getRoleEntities()));
        String token = null;
        try {
//            authenticationManager.authenticate(authRequest);
//            SecurityContext context = SecurityContextHolder.createEmptyContext();
//            context.setAuthentication(authRequest);
//            SecurityContextHolder.setContext(context);
            token = jwTokenAuthenticationUtil.generateToken(userEntity.getId().toString(), GrantedUserUtil.toGrantedAuthorities(userEntity.getRoleEntities()), new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()));
        } catch (AuthenticationException e) {
            return ApiResponseDTO.builder().status(Status.FAIL).message("Oauth2 Authentication Failed").build();
        } catch (JOSEException joseException) {
            return ApiResponseDTO.builder().status(Status.FAIL).message(joseException.getMessage()).build();
        }
        return ApiResponseDTO.builder().data(token).status(Status.SUCCESS).message("User token response successfully").build();
    }

    private String exchangeEmail(String accessToken, String authorizedProvider) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        URI userInfoUri = URI.create("https://api.github.com/user/emails");
        RequestEntity<Void> request = new RequestEntity<>(headers, HttpMethod.GET, userInfoUri);

        ResponseEntity<OAuth2ExchangeEmailDTO[]> response = restTemplate.exchange(request, OAuth2ExchangeEmailDTO[].class);
        String email = null;
        if (response.getStatusCode().is2xxSuccessful()) {
            for (OAuth2ExchangeEmailDTO emailDTO : response.getBody()) {
                if (emailDTO.isPrimary()) {
                    email = emailDTO.getEmail();
                    break;
                }
            }
        }
        return email;
    }
}
