package com.example.application.util.map;

import com.example.application.model.UserEntity;

import java.util.ArrayList;
import java.util.List;

public interface StaffUtil {
    static List<Long> toString(List<UserEntity> staffs) {
        if (staffs == null || staffs.isEmpty()) {
            return new ArrayList<>();
        }
        return staffs.stream().map(UserEntity::getId).toList();
    }
}
