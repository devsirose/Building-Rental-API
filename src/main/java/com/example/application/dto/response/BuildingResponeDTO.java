package com.example.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class BuildingResponeDTO {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String buildingName;
    @JsonProperty("address")
    private String address;
    @JsonProperty("number_of_basement")
    private Integer numberOfBasement;
    @JsonProperty("manager_name")
    private String managerName;
    @JsonProperty("manager_phone_number")
    private String managerPhone;
    @JsonProperty("floor_area")
    private Integer floorArea;
    @JsonProperty("rent_area")
    private String rentAreas;
    @JsonProperty("empty_area")
    private Integer emptyArea;
    @JsonProperty("rent_price")
    private Integer rentFee;
    @JsonProperty("service_fee")
    private String serviceFee;
    @JsonProperty("brokerage_fee")
    private Double brokerageFee;
    @JsonProperty("staff_manager")
    private List<Long> staffIds = new ArrayList<>();
}





