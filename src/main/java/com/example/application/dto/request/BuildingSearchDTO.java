package com.example.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class BuildingSearchDTO {
    private String name;
    private Double floorArea;
    private String district;
    private String ward;
    private String street;
    private Integer numberOfBasement;
    private String direction;
    private String level;
    private Double minRentAreaValue;
    private Double maxRentAreaValue;
    private Double minRentPrice;
    private Double maxRentPrice;
    private String managerName;
    private String managerPhone;
    private Long staffId;
    private List<String> rentTypeCode = new ArrayList<>();
}