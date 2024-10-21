package com.example.application.repository.custom;

import com.example.application.dto.request.BuildingSearchDTO;
import com.example.application.model.BuildingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepositoryCustom {
    List<BuildingEntity> findByProperties(BuildingSearchDTO buildingSearchDTO);

    Page<BuildingEntity> findByPropertiesWithPagination(BuildingSearchDTO buildingSearchDTO, Pageable pageable);


}
