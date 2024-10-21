package com.example.application.util.mapper;

import com.example.application.dto.response.BuildingResponeDTO;
import com.example.application.dto.shared.BuildingUpdateDTO;
import com.example.application.model.BuildingEntity;
import com.example.application.repository.BuildingRepository;
import com.example.application.repository.RentAreaRepository;
import com.example.application.util.map.AddressUtil;
import com.example.application.util.map.RentAreaUtil;
import com.example.application.util.map.StaffUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BuildingMapper {
    private final BuildingRepository buildingRepository;
    private final RentAreaRepository rentAreaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BuildingMapper(BuildingRepository buildingRepository, RentAreaRepository rentAreaRepository, ModelMapper modelMapper) {
        this.buildingRepository = buildingRepository;
        this.rentAreaRepository = rentAreaRepository;
        this.modelMapper = modelMapper;
    }

    public BuildingResponeDTO buildingDtoMapper(BuildingEntity buildingEntity) {
        BuildingResponeDTO buildingResponeDTO = modelMapper.map(buildingEntity, BuildingResponeDTO.class);
        buildingResponeDTO.setRentAreas(RentAreaUtil.toString(buildingEntity.getRentAreaEntities()));
        buildingResponeDTO.setAddress(AddressUtil.toString(buildingEntity.getStreet(), buildingEntity.getWard(), buildingEntity.getDistrict()));
        buildingResponeDTO.setStaffIds(StaffUtil.toString(buildingEntity.getStaffs()));
        return buildingResponeDTO;
    }

    public List<BuildingResponeDTO> buildingsDtoMapper(List<BuildingEntity> buildingEntities) {
        List<BuildingResponeDTO> buildingResponeDTOS = new ArrayList<>();
        buildingEntities.forEach((BuildingEntity buildingEntity) -> {
            buildingResponeDTOS.add(buildingDtoMapper(buildingEntity));
        });
        return buildingResponeDTOS;
    }

    public BuildingUpdateDTO buildingUpdateDTOMapper(BuildingEntity building) {
        BuildingUpdateDTO buildingUpdateDTO = modelMapper.map(building, BuildingUpdateDTO.class);
        buildingUpdateDTO.setRentAreas(RentAreaUtil.toList(building.getRentAreaEntities()));
        return buildingUpdateDTO;
    }

    public BuildingEntity buildingEntityMapper(BuildingUpdateDTO buildingUpdateDTO) {
        BuildingEntity buildingEntity = modelMapper.map(buildingUpdateDTO, BuildingEntity.class);
        buildingEntity.setRentArea(RentAreaUtil.toRentAreaEnties(buildingUpdateDTO.getRentAreas()));
        return buildingRepository.save(buildingEntity);
    }
}
