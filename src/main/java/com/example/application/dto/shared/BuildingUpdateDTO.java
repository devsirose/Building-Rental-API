package com.example.application.dto.shared;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class BuildingUpdateDTO {

    private Long id;
    private String name;
    private String district;
    private String street;
    private String ward;
    private String structure;
    private Integer numberOfBasement;
    private String managerName;
    private String managerPhone;
    private Integer floorArea;
    private List<String> rentAreas = new ArrayList<>();
    private String type = "";
    private Integer rentPrice;
    private String serviceFee;
    private String carFee;
    private String motorbikeFee;
    private String overtimeFee;
    private String waterFee;
    private String electricityFee;
    private String direction;
    private String deposit;
    private Double brokerageFee;
    private String payment;
    private String rentTime;
    private String decorationTime;
    private String note;
    private String linkOfBuilding;
    private String image;
    private String level;
    private String rentPriceDescription;
}
