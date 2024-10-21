package com.example.application.service;

import com.example.application.dto.request.FormResgisterRequestDTO;
import com.example.application.dto.response.ApiResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface IdentityService {
    ApiResponseDTO<?> processLogin(String username, String password);

    ApiResponseDTO<?> processRegister(FormResgisterRequestDTO formResgisterRequestDTO);

    ApiResponseDTO<?> processChangePassword(String username, String oldPassword, String newPassword);

    ApiResponseDTO<?> processRefreshToken(String refreshToken);
}
