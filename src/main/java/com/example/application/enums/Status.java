package com.example.application.enums;

public enum Status {
    SUCCESS("SUCCESS"),
    ERROR("ERROR"),
    FAIL("FAIL");
    private final String status;

    Status(String status) {
        this.status = status;
    }
}
