package com.example.application.util.query;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QueryBuilder {
    private SelectQuery selectQuery;
    private JoinQuery joinQuery;
    private ConditionQuery conditionQuery;
    //FROM :root
    private String root;

    public String createQuery() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(selectQuery.getQuery()).append(" ")
                .append("FROM ").append(root).append(" ");
        if (joinQuery.getTables().size() != 0) {
            stringBuilder.append(joinQuery.getQuery()).append(" ");
        }
        if (!conditionQuery.getQuery().equals("WHERE ")) {
            stringBuilder.append(conditionQuery.getQuery()).append(" ");
        }
        return stringBuilder.toString();
    }
}
