package com.example.application.service.impl;


import com.example.application.dto.request.BuildingSearchDTO;
import com.example.application.dto.response.ApiResponseDTO;
import com.example.application.dto.response.BuildingResponeDTO;
import com.example.application.dto.shared.BuildingUpdateDTO;
import com.example.application.model.BuildingEntity;
import com.example.application.model.UserEntity;
import com.example.application.repository.BuildingRepository;
import com.example.application.repository.UserRepository;
import com.example.application.service.BuildingService;
import com.example.application.util.mapper.BuildingMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {
    private final BuildingRepository buildingRepository;
    private final UserRepository userRepository;
    private final BuildingMapper buildingMapper;

    @Autowired
    public BuildingServiceImpl(BuildingRepository buildingRepository, UserRepository userRepository, ModelMapper modelMapper, BuildingMapper buildingMapper) {
        this.buildingRepository = buildingRepository;
        this.userRepository = userRepository;
        this.buildingMapper = buildingMapper;
    }

    @Override
    public List<BuildingResponeDTO> findBuildingByProperties(BuildingSearchDTO buildingSearchDTO) {
        return buildingMapper.buildingsDtoMapper(buildingRepository.findByProperties(buildingSearchDTO));
    }

    @Override
    public ApiResponseDTO<Object> findBuildingByPropertiesWithPagination(BuildingSearchDTO buildingSearchDTO, Pageable pageable) {
        Page<BuildingEntity> buildingEntityPage = buildingRepository.findByPropertiesWithPagination(buildingSearchDTO, pageable);
        List<BuildingEntity> buildings = buildingEntityPage.getContent();
//        PaginationResponseDTO paginationResponseDTO = PaginationResponseDTO.builder()
//                .currentPage(pageable.getPageNumber())
//                .totalPages(buildingEntityPage.getTotalPages())
//                .totalRecords(Long.valueOf(buildingEntityPage.getTotalElements()))
//                .nextPage(buildingEntityPage.hasNext() ? Integer.valueOf(pageable.getPageNumber() + 1) : null)
//                .prev_page(buildingEntityPage.hasPrevious() ? Integer.valueOf(pageable.getPageNumber() - 1) : null)
//                .build();
        return ApiResponseDTO.builder().data(buildingMapper.buildingsDtoMapper(buildings))
                .message("List building with pagination result")
                .build();
    }

    @Override
    public ResponseEntity<ApiResponseDTO<Object>> deleteBuildingByIds(List<Long> ids) {
        buildingRepository.deleteBuildingEntitiesByIdIn(ids);
        return ResponseEntity.ok().body(ApiResponseDTO.builder().message("succeed delete building").build());
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> saveBuilding(BuildingUpdateDTO buildingUpdateDTO) {
        ApiResponseDTO<BuildingResponeDTO> response =null;
        try {
            response.setData(buildingMapper.buildingDtoMapper(buildingRepository.saveAndFlush(buildingMapper.buildingEntityMapper(buildingUpdateDTO))));
            response.setMessage("succeed save building");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setMessage("error save building");
            return ResponseEntity.badRequest().body(response);
        }
    }


    @Override
    public void assignBuildingToStaff(Long buildingId, List<Long> staffIds) {
        BuildingEntity building = buildingRepository.findById(buildingId).get();
        List<UserEntity> staffs = userRepository.findUserEntitiesByIdIn(staffIds);
        building.setStaffs(staffs);
        buildingRepository.save(building);
    }

    @Override
    public BuildingUpdateDTO findById(Long buildingId) {
        return buildingMapper.buildingUpdateDTOMapper(buildingRepository.findById(buildingId).get());
    }

    @Override
    public List<BuildingResponeDTO> findAll() {
        return buildingMapper.buildingsDtoMapper(buildingRepository.findAll());
    }
}
