package com.example.application.service.impl;

import com.example.application.model.BuildingEntity;
import com.example.application.repository.BuildingRepository;
import com.example.application.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AttachmentServiceImpl implements AttachmentService {
    private final BuildingRepository buildingRepository;
    private final String BUILDING_IMAGE_PATH = "resources/static/custom/img/building/";

    @Autowired
    public AttachmentServiceImpl(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    @Override
    public void uploadBuildingImage(MultipartFile file, Long buildingId) {
        BuildingEntity building = buildingRepository.findById(buildingId).get();
        String path = BUILDING_IMAGE_PATH + buildingId + "." + file.getContentType().substring(file.getContentType().indexOf("/") + 1);
        building.setImage(path);
        try {
            saveFileToFolder(file.getBytes(), path);
        } catch (IOException e) {
            System.out.println("YES");
            e.printStackTrace();
        }
        buildingRepository.save(building);
    }

    public void saveFileToFolder(byte[] data, String path) throws IOException {
        // Write the byte array data to the file
        Path file = Paths.get(path).toAbsolutePath();
        Files.write(file, data);
    }

    @Override
    public byte[] downloadBuildingImage(Long buildingId) {
        BuildingEntity building = buildingRepository.findById(buildingId).get();
        String filePath = building.getImage();
        Path path = Paths.get(filePath);
        byte[] imageData = null;
        try {
            imageData = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageData;
    }
}
