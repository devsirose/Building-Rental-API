package com.example.application.service;

import com.example.application.dto.response.ApiResponseDTO;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;

@Service
public interface IdentityOauth2Service {
    ApiResponseDTO<?> doAuthenticateInternal(OAuth2AuthorizedClient authorizedClient);
}
