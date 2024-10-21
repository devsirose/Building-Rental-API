package com.example.application.controller.admin;

import com.example.application.dto.request.BuildingSearchDTO;
import com.example.application.dto.response.ApiResponseDTO;
import com.example.application.dto.response.BuildingResponeDTO;
import com.example.application.dto.shared.BuildingUpdateDTO;
import com.example.application.service.BuildingService;
import com.example.application.service.UserService;
import com.example.application.util.query.SortUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/admin/building")
public class BuildingController {
    private final BuildingService buildingService;

    @Autowired
    public BuildingController(BuildingService buildingService, UserService userService, ModelMapper modelMapper) {
        this.buildingService = buildingService;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ApiResponseDTO<Object>> deleteBuilding(@PathVariable("id") List<Long> ids) {
        return buildingService.deleteBuildingByIds(ids);
    }


    @GetMapping()
    public String getBuildings(BuildingSearchDTO buildingSearchDTO, RedirectAttributes redirectAttributes) {
        List<BuildingResponeDTO> buildingResponeDTOS = buildingService.findBuildingByProperties(buildingSearchDTO);
        redirectAttributes.addFlashAttribute("buildingDTOs", buildingResponeDTOS);
        redirectAttributes.addFlashAttribute("buildingSearchDTO", buildingSearchDTO);
        return "redirect:/admin/search-building";
    }

    @GetMapping("/pagination")
    @ResponseBody
    public ApiResponseDTO getBuildingsWithPagination(BuildingSearchDTO buildingSearchDTO,
                                                     @RequestParam("page") Long pageNumber, @RequestParam("size") Long pageSize,
                                                     @RequestParam(value = "sort_by", required = false) List<String> sortBys,
                                                     @RequestParam(value = "sort_order", required = false) List<String> sortOrders) {
        Sort sort = SortUtil.fromFieldsToSort(sortBys, sortOrders);
        ApiResponseDTO apiResponseDTO = buildingService.findBuildingByPropertiesWithPagination(buildingSearchDTO, PageRequest.of(pageNumber.intValue(), pageSize.intValue(), sort));
        return apiResponseDTO;
    }

    @PostMapping()
    @ResponseBody
    public ResponseEntity<ApiResponseDTO<?>> createBuilding(@RequestBody BuildingUpdateDTO buildingUpdateDTO) {
        return buildingService.saveBuilding(buildingUpdateDTO);
    }

    @PostMapping(value = "/{id}")
    public String updateBuilding(@RequestBody BuildingUpdateDTO buildingUpdateDTO, RedirectAttributes redirectAttributes) {
        buildingService.saveBuilding(buildingUpdateDTO);
        redirectAttributes.addFlashAttribute("buildingRequestDTO", buildingUpdateDTO);
        return "redirect:/admin/search-building";
    }

    @PostMapping(value = "/img/{id}")
    @ResponseBody
    public void uploadBuildingImage(@RequestBody RequestBody body, @PathVariable("id") Long buildingId) {
    }

}
