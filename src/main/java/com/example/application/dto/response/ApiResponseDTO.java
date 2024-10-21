package com.example.application.dto.response;

import com.example.application.enums.Status;
import lombok.*;

@Builder
@Getter
@Setter
public class ApiResponseDTO<T> {
    private T data;
    private Status status;
    private String message;
}
