package com.example.application.service;

import com.example.application.dto.response.UserResponseDTO;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface UserService {
    Map<Long, String> getAllStaffIdAndName();

    boolean isExistByUsername(String username);
    UserResponseDTO saveUserWithDefaultRole(String subject, String password);
}
