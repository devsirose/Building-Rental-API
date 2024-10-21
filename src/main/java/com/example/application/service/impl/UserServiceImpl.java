package com.example.application.service.impl;

import com.example.application.dto.response.UserResponseDTO;
import com.example.application.model.RoleEntity;
import com.example.application.model.UserEntity;
import com.example.application.repository.RoleRepository;
import com.example.application.repository.UserRepository;
import com.example.application.service.UserService;
import com.example.application.util.mapper.UserMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public Map<Long, String> getAllStaffIdAndName() {
        Map<Long, String> staffMappings = new TreeMap<>();
        List<UserEntity> staffs = userRepository.findAllStaff();
        for (UserEntity staff : staffs) {
            staffMappings.put(staff.getId(), staff.getFullName());
        }
        return staffMappings;
    }

    @Override
    public boolean isExistByUsername(String username) {
        return userRepository.existsByUsername(username);
    }


    @Override
    public UserResponseDTO saveUserWithDefaultRole(String subject,String password) {
        UserEntity userEntity = new UserEntity();
        if (password == null) {
            userEntity.setEmail(subject);
        }
        else {
            userEntity.setPassword(passwordEncoder.encode(password));
            userEntity.setUsername(subject);
        }
        RoleEntity role = roleRepository.findRoleByCode("USER");
        userEntity.addRole(role);
        return saveUser(userEntity);
    }

    private UserResponseDTO saveUser(UserEntity user) {
        return modelMapper.map(userRepository.save(user), UserResponseDTO.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = null;
        userEntity = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User does not exist"));
        return UserMapper.toUser(userEntity);
    }
}
