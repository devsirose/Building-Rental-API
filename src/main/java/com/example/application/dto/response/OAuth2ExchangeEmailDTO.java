package com.example.application.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OAuth2ExchangeEmailDTO {
    private String email;
    private boolean primary;
}
