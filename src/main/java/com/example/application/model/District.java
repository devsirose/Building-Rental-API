package com.example.application.model;

public enum District {
    QUAN_1("QUAN_1", "Quận 1"),
    QUAN_2("QUAN_2", "Quận 2"),
    QUAN_3("QUAN_3", "Quận 3"),
    QUAN_4("QUAN_4", "Quận 4"),
    QUAN_5("QUAN_5", "Quận 5"),
    QUAN_6("QUAN_6", "Quận 6"),
    QUAN_7("QUAN_7", "Quận 7"),
    QUAN_8("QUAN_8", "Quận 8"),
    QUAN_9("QUAN_9", "Quận 9"),
    QUAN_10("QUAN_10", "Quận 10"),
    QUAN_11("QUAN_11", "Quận 11"),
    QUAN_12("QUAN_12", "Quận 12"),
    BINH_TAN("BINH_TAN", "Bình Tân"),
    BINH_THANH("BINH_THANH", "Bình Thạnh"),
    GO_VAP("GO_VAP", "Gò Vấp"),
    PHU_NHUAN("PHU_NHUAN", "Phú Nhuận"),
    TAN_BINH("TAN_BINH", "Tân Bình"),
    TAN_PHU("TAN_PHU", "Tân Phú"),
    THU_DUC("THU_DUC", "Thủ Đức"),
    BIEN_HOA("BIEN_HOA", "Biên Hòa");

    private final String districtCode;
    private final String districtName;

    District(String districtCode, String districtName) {
        this.districtCode = districtCode;
        this.districtName = districtName;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }
}





