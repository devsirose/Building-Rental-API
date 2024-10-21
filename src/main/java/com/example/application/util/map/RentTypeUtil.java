package com.example.application.util.map;

import com.example.application.model.RentType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public interface RentTypeUtil {
    static Map<String, String> rentTypeMapping() {
        Map<String, String> rentTypeMapping = new HashMap<>();
        Arrays.stream(RentType.values()).forEach(rentType -> {
            rentTypeMapping.put(rentType.getRentTypeCode(), rentType.getRentTypeName());
        });
        return rentTypeMapping;
    }
}
