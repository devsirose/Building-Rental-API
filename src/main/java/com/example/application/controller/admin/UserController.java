package com.example.application.controller.admin;

import com.example.application.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("admin/building/{buildingId}/staff")
public class UserController {
    private final BuildingService buildingService;

    @Autowired
    public UserController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @PostMapping()
    public void assignBuildingToStaffs(@PathVariable(value = "buildingId") Long buildingId, @RequestBody List<Long> staffIds) {
        buildingService.assignBuildingToStaff(buildingId, staffIds);
    }

}
