package com.example.application.service;

import com.example.application.dto.request.BuildingSearchDTO;
import com.example.application.dto.response.ApiResponseDTO;
import com.example.application.dto.response.BuildingResponeDTO;
import com.example.application.dto.shared.BuildingUpdateDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BuildingService {
    List<BuildingResponeDTO> findBuildingByProperties(BuildingSearchDTO buildingSearchDTO);

    ApiResponseDTO<Object> findBuildingByPropertiesWithPagination(BuildingSearchDTO buildingSearchDTO, Pageable pageable);

    ResponseEntity<ApiResponseDTO<Object>> deleteBuildingByIds(List<Long> ids);

    ResponseEntity<ApiResponseDTO<?>> saveBuilding(BuildingUpdateDTO buildingUpdateDTO);

    void assignBuildingToStaff(Long buildingId, List<Long> staffIds);

    BuildingUpdateDTO findById(Long buildingId);

    List<BuildingResponeDTO> findAll();

}
