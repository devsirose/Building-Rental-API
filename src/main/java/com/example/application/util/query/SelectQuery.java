package com.example.application.util.query;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SelectQuery {
    //SELECT :fields
    private List<String> fields;

    public SelectQuery() {
        fields = new ArrayList<>();
    }

    public String getQuery() {
        StringBuilder stringBuilder = new StringBuilder("SELECT ");
        for (int i = 0; i < fields.size(); i++) {
            stringBuilder.append(fields.get(i));
            if (i != fields.size() - 1) {
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }

    public void addField(String field) {
        if (fields == null) {
            fields = new ArrayList<>();
        }
        fields.add(field);
    }
}
