package com.example.application.dto.request;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormResgisterRequestDTO {
    private String username;
    private String password;
    private String fullName;
    private String phone;
    private String email;
}
