package com.example.application.controller.shared;

import com.example.application.dto.request.FormLoginRequestDTO;
import com.example.application.dto.request.FormResgisterRequestDTO;
import com.example.application.dto.response.ApiResponseDTO;
import com.example.application.enums.Status;
import com.example.application.service.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/auth")
public class IdentityController {
    private final IdentityService identityService;

    @Autowired
    public IdentityController(IdentityService identityService) {
        this.identityService = identityService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> processLogin(FormLoginRequestDTO formLogin) {
        ApiResponseDTO<?> response = identityService.processLogin(formLogin.getUsername(), formLogin.getPassword());
        return response.getStatus() == Status.SUCCESS ? ResponseEntity.ok(response) : ResponseEntity.badRequest().body(response);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> processRegister(FormResgisterRequestDTO formResgisterRequestDTO) {
        ApiResponseDTO<?> response = identityService.processRegister(formResgisterRequestDTO);
        return response.getStatus() == Status.SUCCESS ? ResponseEntity.ok(response) : ResponseEntity.badRequest().body(response);
    }

    @GetMapping(value = "/logout")
    public ResponseEntity<?> processLogout(Authentication authentication) {
        return ResponseEntity.ok().body(ApiResponseDTO.builder().status(Status.SUCCESS).message("Successfully logged out").build());
    }

    @PostMapping(value = "/refresh")
    public ResponseEntity<?> processRefresh(String refreshToken) {
        ApiResponseDTO<?> response = identityService.processRefreshToken(refreshToken);
        return response.getStatus() == Status.SUCCESS ? ResponseEntity.ok(response) : ResponseEntity.badRequest().body(response);
    }

}
