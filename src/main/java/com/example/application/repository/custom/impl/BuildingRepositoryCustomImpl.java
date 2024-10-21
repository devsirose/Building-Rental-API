package com.example.application.repository.custom.impl;

import com.example.application.dto.request.BuildingSearchDTO;
import com.example.application.model.BuildingEntity;
import com.example.application.repository.custom.BuildingRepositoryCustom;
import com.example.application.util.query.ConditionQuery;
import com.example.application.util.query.JoinQuery;
import com.example.application.util.query.QueryBuilder;
import com.example.application.util.query.SelectQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryCustomImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public List<BuildingEntity> findByProperties(BuildingSearchDTO buildingSearchDTO) {
        String query = buildFindingPropertiesQuery(buildingSearchDTO);
        Query jpqlQuery = entityManager.createNativeQuery(query, BuildingEntity.class);
        return jpqlQuery.getResultList();
    }


    @Override
    public Page<BuildingEntity> findByPropertiesWithPagination(BuildingSearchDTO buildingSearchDTO, Pageable pageable) {
        String query = buildFindingPropertiesQuery(buildingSearchDTO);
        query = appendOptionQuery(query, pageable);
        Query jpqlQuery = entityManager.createNativeQuery(query, BuildingEntity.class);
        List<BuildingEntity> buildings = jpqlQuery.getResultList();
        return new PageImpl<BuildingEntity>(buildings, pageable, countAllBy(BuildingEntity.class));
    }

    public Long countAllBy(Class<?> tClass) {
        // creating criteria builder and query
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<?> buildingEntityRoot = criteriaQuery.from(tClass);

        // select query
        criteriaQuery.select(builder.count(buildingEntityRoot));

        // execute and get the result
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }


    private String buildFindingPropertiesQuery(BuildingSearchDTO buildingSearchDTO) {
        SelectQuery selectQuery = new SelectQuery();
        selectQuery.addField("building.*");
        JoinQuery joinQuery = buildJoinQuery(buildingSearchDTO);
        ConditionQuery conditionQuery = new ConditionQuery();
        conditionQuery = buildSpecialConditionQuery(buildingSearchDTO, conditionQuery);
        conditionQuery = buildNormalConditionQuery(buildingSearchDTO, conditionQuery);
        QueryBuilder queryBuilder = QueryBuilder.builder()
                .root("building")
                .selectQuery(selectQuery)
                .joinQuery(joinQuery)
                .conditionQuery(conditionQuery)
                .build();
        return queryBuilder.createQuery();
    }

    private String appendOptionQuery(String query, Pageable pageable) {
        Sort sort = pageable.getSort();
        if (sort.isSorted()) {
            query += " ORDER BY ";
            String orderQuery = sort.get().map(order -> {
                String sortField = order.getProperty() + " " + order.getDirection();
                return sortField;
            }).collect(Collectors.joining(","));
            query += (orderQuery);
        }
        query += (" LIMIT ") + (pageable.getPageSize());
        query += (" OFFSET ") + (pageable.getOffset());
        return query;
    }

    public ConditionQuery buildSpecialConditionQuery(BuildingSearchDTO buildingSearchDTO, ConditionQuery conditionQuery) {
        Field[] fields = buildingSearchDTO.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String key = field.getName();
            Object value = getFieldWithAccess(buildingSearchDTO, field);
            if (value == null || value.toString().isEmpty()) continue;
            if (value instanceof List<?>) {
                List<String> renttypecodes = (List<String>) value;
                if (renttypecodes.isEmpty()) continue;
                conditionQuery.append(" AND ");
                conditionQuery.append("(");
                for (int i = 0; i < renttypecodes.size(); i++) {
                    String column = "type";
                    conditionQuery.append(column + " like N'%" + renttypecodes.get(i) + "%'");
                    if (i != renttypecodes.size() - 1) {
                        conditionQuery.append(" OR ");
                    }
                }
                conditionQuery.append(")");
            } else if (key.contains("RentArea")) {
                conditionQuery.append(" AND ");
                String column = "rentarea.value";
                if (key.startsWith("min")) {
                    conditionQuery.append(column + ">=" + value);
                } else {
                    conditionQuery.append(column + "<=" + value);
                }
            } else if (key.contains("RentPrice")) {
                conditionQuery.append(" AND ");
                String column = "building.rentprice";
                if (key.startsWith("min")) {
                    conditionQuery.append(column + ">=" + value);
                } else {
                    conditionQuery.append(column + "<=" + value);
                }
            } else if (key.contains("staffId")) {
                conditionQuery.append(" AND ");
                String column = "user.id";
                conditionQuery.append(column + " = " + value);
            }
        }
        return conditionQuery;
    }

    public ConditionQuery buildNormalConditionQuery(BuildingSearchDTO buildingSearchDTO, ConditionQuery conditionQuery) {
        Field[] fields = buildingSearchDTO.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String key = field.getName();
            if (!key.equals("staffId") && !key.equals("rentTypeCode") && !key.contains("RentArea") && !key.contains("RentPrice")) {

                Object value = getFieldWithAccess(buildingSearchDTO, field);
                if (value == null || value.toString().isEmpty()) continue;
                if (value instanceof String) {
                    conditionQuery.append(" AND ");
                    conditionQuery.append("building." + key.toLowerCase() + " like " + "'%" + (value.toString()).toLowerCase() + "%'");
                } else {
                    conditionQuery.append(" AND ");
                    conditionQuery.append("building." + key.toLowerCase() + " = " + value);
                }
            }
        }
        conditionQuery.append(" GROUP BY building.id");
        return conditionQuery;
    }

    public JoinQuery buildJoinQuery(BuildingSearchDTO buildingSearchDTO) {
        JoinQuery joinQuery = new JoinQuery();
        Field[] fields = buildingSearchDTO.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String key = field.getName();
            Object value = getFieldWithAccess(buildingSearchDTO, field);
            if (value == null || value.toString().isEmpty()) continue;
            if ((key.equals("minRentAreaValue") || key.equals("maxRentAreaValue")) && !joinQuery.getTables().contains("rentarea")) {
                joinQuery.addJoinTable("rentarea", "building.id", "rentarea.buildingid");
            }
            if (key.equals("staffId")) {
                joinQuery.addJoinTable("assignmentbuilding", "building.id", "assignmentbuilding.buildingid");
                joinQuery.addJoinTable("user", "assignmentbuilding.staffid", "user.id");
            }
        }
        return joinQuery;
    }

    private Object getFieldWithAccess(BuildingSearchDTO buildingSearchDTO, Field field) {
        try {
            field.setAccessible(true);
            return field.get(buildingSearchDTO);
        } catch (IllegalAccessException exception) {
            exception.printStackTrace();
            return null;
        }
    }

}
