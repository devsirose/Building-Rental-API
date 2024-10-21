package com.example.application.util.query;

import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public interface SortUtil {
    static Sort fromFieldsToSort(List<String> sortBys, List<String> sortOders) {
        if (sortBys == null || sortBys.isEmpty()) return Sort.unsorted();
        List<Sort.Order> orders = new ArrayList<>();
        for (int i = 0; i < sortBys.size(); i++) {
            Sort.Order order = new Sort.Order(Sort.Direction.fromString(sortOders.get(i)), sortBys.get(i));
            orders.add(order);
        }
        return Sort.by(orders);
    }
}
