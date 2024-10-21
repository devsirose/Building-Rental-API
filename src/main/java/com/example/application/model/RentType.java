package com.example.application.model;

public enum RentType {
    NGUYEN_CAN("NGUYEN_CAN", "Nguyên Căn"),
    NOI_THAT("NOI_THAT", "Nội Thất"),
    TANG_TRET("TANG_TRET", "Tầng Trệt");

    private final String rentTypeCode;
    private final String rentTypeName;

    RentType(String rentTypeCode, String rentTypeName) {
        this.rentTypeCode = rentTypeCode;
        this.rentTypeName = rentTypeName;
    }

    public String getRentTypeCode() {
        return rentTypeCode;
    }

    public String getRentTypeName() {
        return rentTypeName;
    }
}
