package com.example.application.dto.response;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IdentityResponseDTO {
    private String token;
    private String refreshToken;
    private String username;
}
