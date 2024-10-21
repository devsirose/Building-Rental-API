package com.example.application.util.map;

import com.example.application.model.District;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public interface DistrictUtil {
    static Map<String, String> districtMapping() {
        Map<String, String> districtMapping = new TreeMap<>();
        Arrays.stream(District.values()).forEach(district -> {
            districtMapping.put(district.getDistrictCode(), district.getDistrictName());
        });
        return districtMapping;
    }
}
