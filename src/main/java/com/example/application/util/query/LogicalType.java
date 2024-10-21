package com.example.application.util.query;

public enum LogicalType {
    OR("OR"),
    AND("AND");
    private final String logic;

    LogicalType(String logic) {
        this.logic = logic;
    }

    String getLogic() {
        return this.logic;
    }
}
