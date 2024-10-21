package com.example.application.util.query;

public enum JoinType {
    JOIN("JOIN"),
    INNER_JOIN("INNER JOIN"),
    OUTER_JOIN("OUTER JOIN"),
    LEFT_JOIN("LEFT JOIN"),
    RIGHT_JOIN("RIGHT JOIN");
    private final String abbreviation;

    JoinType(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    String getAbbreviation() {
        return this.abbreviation;
    }
}
