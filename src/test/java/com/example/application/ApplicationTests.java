package com.example.application;

import com.example.application.model.UserEntity;
import com.example.application.repository.BuildingRepository;
import com.example.application.repository.RoleRepository;
import com.example.application.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BuildingRepository buildingRepository;

    @Test
    public void getPaginationResult() {
        UserEntity userEntity = UserEntity.builder().username("admin")
                .password("admin")
                .status(1)
                .fullName("admin")
                .build();
        userRepository.save(userEntity);
    }
}
