package com.example.application.controller.admin;

import com.example.application.dto.request.BuildingSearchDTO;
import com.example.application.dto.response.BuildingResponeDTO;
import com.example.application.dto.shared.BuildingUpdateDTO;
import com.example.application.service.BuildingService;
import com.example.application.service.UserService;
import com.example.application.util.map.DistrictUtil;
import com.example.application.util.map.RentTypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private final UserService userService;
    private final BuildingService buildingService;

    @Autowired
    public AdminController(UserService userService, BuildingService buildingService) {
        this.userService = userService;
        this.buildingService = buildingService;
    }

    @GetMapping()
    public String showAdminHomePage() {
        return "admin/index";
    }

    @GetMapping("/search-building")
    public String showSearchingForm(Model model) {
        if (!model.containsAttribute("buildingSearchDTO")) {
            model.addAttribute("buildingSearchDTO", new BuildingSearchDTO());
        }
        if (!model.containsAttribute("buildingDTOs")) {
            List<BuildingResponeDTO> buildingResponeDTO = buildingService.findAll();
            model.addAttribute("buildingDTOs", buildingResponeDTO);
        }
        return "admin/building/searching-form";
    }

    @GetMapping("/update-building")
    public String showUpdatingForm(@RequestParam(value = "buildingId", required = false) Long buildingId, Model model) {
        if (!model.containsAttribute("buildingUpdateDTO")) {
            BuildingUpdateDTO buildingUpdateDTO = new BuildingUpdateDTO();
            if (buildingId != null) {
                buildingUpdateDTO = buildingService.findById(buildingId);
            }
            buildingUpdateDTO.setId(buildingId);
            model.addAttribute("buildingUpdateDTO", buildingUpdateDTO);
        }
        return "admin/building/updating-form";
    }

    @ModelAttribute(name = "districtMappings")
    public Map<String, String> getCodeAndNameAllDistrict() {
        return DistrictUtil.districtMapping();
    }

    @ModelAttribute(name = "rentTypeMappings")
    public Map<String, String> getCodeAndNameAllRentType() {
        return RentTypeUtil.rentTypeMapping();
    }

    @ModelAttribute(name = "staffMappings")
    public Map<Long, String> getIdAndNameAllStaff() {
        return userService.getAllStaffIdAndName();
    }
}