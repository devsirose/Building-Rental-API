package com.example.application.util.map;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface AddressUtil {
    static String toString(String street, String ward, String district) {
        List<String> items = new ArrayList<>();
        items.add(street == null || street.isEmpty() ? "." : street);
        items.add(ward == null || ward.isEmpty() ? "." : ward);
        items.add(district == null || district.isEmpty() ? "." : district);
        return items.stream().map(item -> {
            return item;
        }).collect(Collectors.joining(","));
    }
}
