package com.example.application.util.query;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class JoinQuery {
    //JOIN  :fields ON :field1 = :field2;
    private JoinType joinType;
    private List<String> tables;
    private List<String> leftFields;
    private List<String> rightFields;

    public JoinQuery() {
        joinType = JoinType.INNER_JOIN;
        tables = new ArrayList<>();
        leftFields = new ArrayList<>();
        rightFields = new ArrayList<>();
    }

    public String getQuery() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tables.size(); i++) {
            stringBuilder.append(joinType.getAbbreviation()).append(" ")
                    .append(tables.get(i)).append(" ON ")
                    .append(leftFields.get(i)).append(" = ")
                    .append(rightFields.get(i)).append(" ");
        }
        return stringBuilder.toString();
    }

    public void addJoinTable(String table, String leftField, String rightField) {
        if (tables == null) {
            tables = new ArrayList<>();
            leftFields = new ArrayList<>();
            rightFields = new ArrayList<>();
        }
        tables.add(table);
        leftFields.add(leftField);
        rightFields.add(rightField);
    }
}
