package com.example.application.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface AttachmentService {
    //updload building Image and return url path in file folder ref to this image
    void uploadBuildingImage(MultipartFile file, Long buildingId);

    byte[] downloadBuildingImage(Long buildingId);
}
