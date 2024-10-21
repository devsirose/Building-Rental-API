package com.example.application.repository;

import com.example.application.model.BuildingEntity;
import com.example.application.repository.custom.BuildingRepositoryCustom;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Lazy
public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>, BuildingRepositoryCustom {
    //    @Query("SELECT u FROM BuildingEntity u WHERE " +
//            ":#{#buildingSearchDTO.getName()} IS NOT NULL AND u.name like %:#{#buildingSearchDTO.getName()}%")
//    List<BuildingEntity> findByProperties(BuildingSearchDTO buildingSearchDTO);
    @Transactional
    @Modifying
    void deleteBuildingEntitiesByIdIn(List<Long> ids);
}
