package com.example.application.repository;

import com.example.application.model.RentAreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentAreaRepository extends JpaRepository<RentAreaEntity, Long> {
    @Query("SELECT u FROM RentAreaEntity u WHERE u.buildingEntity.id = :buildingId")
    List<RentAreaEntity> findByBuildingId(@Param("buildingId") Long buildingId);
//    @Transactional
//    @Modifying
//    @Query(value = "DELETE FROM RentAreaEntity u WHERE u.buildingEntity.id = :buildingId")
//    void deleteByBuildingEntity_Id(@Param("buildingId")Long buildingId);
}
