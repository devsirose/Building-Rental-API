package com.example.application.util.map;

import com.example.application.model.RentAreaEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface RentAreaUtil {
    static String toString(List<RentAreaEntity> rentAreaEntities) {
        if (rentAreaEntities == null || rentAreaEntities.isEmpty()) {
            return "";
        }
        return rentAreaEntities.stream()
                .map(rentAreaEntity -> rentAreaEntity.getValue().toString())
                .collect(Collectors.joining(","));
    }

    static List<String> toList(List<RentAreaEntity> rentAreaEntities) {
        if (rentAreaEntities == null || rentAreaEntities.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> rentAreas = new ArrayList<>();
        for (RentAreaEntity rentAreaEntity : rentAreaEntities) {
            rentAreas.add(rentAreaEntity.getValue().toString());
        }
        return rentAreas;
    }

    static List<RentAreaEntity> toRentAreaEnties(List<String> rentAreas) {
        return rentAreas.stream().map(rentArea -> {
            return RentAreaEntity.builder()
                    .value(Integer.valueOf(rentArea))
                    .build();
        }).collect(Collectors.toList());
    }
}
