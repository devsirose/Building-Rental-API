package com.example.application.service.impl;

import com.example.application.dto.request.FormResgisterRequestDTO;
import com.example.application.dto.response.ApiResponseDTO;
import com.example.application.dto.response.IdentityResponseDTO;
import com.example.application.enums.Status;
import com.example.application.model.UserEntity;
import com.example.application.repository.UserRepository;
import com.example.application.service.IdentityService;
import com.example.application.service.UserService;
import com.example.application.util.jwt.JWTokenAuthenticationUtil;
import com.example.application.util.map.GrantedUserUtil;
import com.nimbusds.jose.JOSEException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class IdentityServiceImpl implements IdentityService {
    private final AuthenticationManager authenticationManager;
    private final JWTokenAuthenticationUtil jwTokenAuthenticationUtil;
    private final UserDetailsService userDetailsService;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final UserRepository userRepository;
    private final Date expirationTimeAccessToken = new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli());
    private final Date expirationTimeRefreshToken = new Date(Instant.now().plus(30, ChronoUnit.DAYS).toEpochMilli());

    @Autowired
    public IdentityServiceImpl(AuthenticationManager authenticationManager, JWTokenAuthenticationUtil jwTokenAuthenticationUtil, UserDetailsService userDetailsService, UserRepository userRepository, ModelMapper modelMapper, UserService userService, UserRepository userRepository1) {
        this.authenticationManager = authenticationManager;
        this.jwTokenAuthenticationUtil = jwTokenAuthenticationUtil;
        this.userDetailsService = userDetailsService;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.userRepository = userRepository1;
    }

    @Override
    public ApiResponseDTO<?> processLogin(String username, String password) {
        ApiResponseDTO<?> responseDTO = null;
        String token = null;
        String refreshToken = null;
        Authentication authResult = null;
        try {
            UserDetails userEntity = userDetailsService.loadUserByUsername(username);
            authResult = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            UserEntity user = userRepository.findByUsername(username).get();
            token = jwTokenAuthenticationUtil.generateToken(user.getId().toString(), authResult.getAuthorities(), expirationTimeAccessToken);
            refreshToken = jwTokenAuthenticationUtil.generateToken(user.getId().toString(), authResult.getAuthorities(), expirationTimeRefreshToken);
        } catch (UsernameNotFoundException usernameNotFoundException) {
            responseDTO = ApiResponseDTO.builder().status(Status.FAIL).message("User not found").build();
            return responseDTO;
        } catch (BadCredentialsException badCredentialsException) {
            responseDTO = ApiResponseDTO.builder().status(Status.FAIL).message("Bad credential").build();
            return responseDTO;
        } catch (JOSEException joseException) {
            responseDTO = ApiResponseDTO.builder().status(Status.FAIL).message(joseException.getMessage()).build();
            return responseDTO;
        }
        responseDTO = ApiResponseDTO.builder().status(Status.SUCCESS).message("Success").data(IdentityResponseDTO.builder().token(token).refreshToken(refreshToken).username(authResult.getName()).build()).build();
        return responseDTO;
    }


    @Override
    public ApiResponseDTO<?> processRegister(FormResgisterRequestDTO formResgisterRequestDTO) {
        ApiResponseDTO<IdentityResponseDTO> responseDTO = null;
        ModelMapper mapper = new ModelMapper();
        UserEntity userEntity = mapper.map(formResgisterRequestDTO, UserEntity.class);
        if (!userService.isExistByUsername(formResgisterRequestDTO.getUsername())) {
            userService.saveUserWithDefaultRole(formResgisterRequestDTO.getUsername(),formResgisterRequestDTO.getPassword());
        } else {
            responseDTO.setStatus(Status.FAIL);
            responseDTO.setMessage("Username already exists");
            return responseDTO;
        }
        return processLogin(userEntity.getUsername(), userEntity.getPassword());
    }

    @Override
    public ApiResponseDTO<?> processChangePassword(String username, String oldPassword, String newPassword) {
        return null;
    }

    @Override
    public ApiResponseDTO<?> processRefreshToken(String refreshToken) {
        ApiResponseDTO responseDTO = null;
        try {
            if (jwTokenAuthenticationUtil.isValidToken(refreshToken)) {
                UserEntity user = userRepository.findById(Long.parseLong(jwTokenAuthenticationUtil.parseSubject(refreshToken))).get();
                String token = jwTokenAuthenticationUtil.generateToken(user.getUsername(), GrantedUserUtil.toGrantedAuthorities(user.getRoleEntities()), expirationTimeAccessToken);
                refreshToken = jwTokenAuthenticationUtil.generateToken(user.getUsername(), GrantedUserUtil.toGrantedAuthorities(user.getRoleEntities()), expirationTimeRefreshToken);
                responseDTO.setStatus(Status.SUCCESS);
                responseDTO.setData(IdentityResponseDTO.builder().token(token).refreshToken(refreshToken).username(user.getUsername()).build());
                responseDTO.setMessage("Refresh token successfully");
            } else {
                responseDTO.setStatus(Status.FAIL);
                responseDTO.setMessage("Refresh token is invalid");
            }
        } catch (JOSEException | ParseException joseParseException) {
            responseDTO.setStatus(Status.FAIL);
            responseDTO.setMessage(joseParseException.getMessage());
        }
        return responseDTO;
    }
}
