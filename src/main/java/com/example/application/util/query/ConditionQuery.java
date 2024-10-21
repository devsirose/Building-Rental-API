package com.example.application.util.query;

public class ConditionQuery {
    StringBuilder conditionQueryBuilder;

    public ConditionQuery() {
        conditionQueryBuilder = new StringBuilder("WHERE 1=1 ");
    }

    public String getQuery() {
        return conditionQueryBuilder.toString();
    }

    public void append(String clause) {
        conditionQueryBuilder.append(clause);
    }

}
