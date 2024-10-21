package com.example.application.util.mapper;

import com.example.application.model.UserEntity;
import com.example.application.util.map.GrantedUserUtil;
import org.springframework.security.core.userdetails.User;

public interface UserMapper {
    static User toUser(UserEntity userEntity) {
        return new User(userEntity.getUsername(), userEntity.getPassword(), GrantedUserUtil.toGrantedAuthorities(userEntity.getRoleEntities()));
    }
}
