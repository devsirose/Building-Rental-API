package com.example.application.controller.shared;

import com.example.application.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {
    private final AttachmentService attachmentService;

    @Autowired
    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @PostMapping(value = "/image/building/{id}")
    public void uploadBuildingImage(@RequestParam("file") MultipartFile file, @PathVariable("id") Long buildingId) {
        attachmentService.uploadBuildingImage(file, buildingId);
    }

    @GetMapping("/image/building/{id}")
    public ResponseEntity<?> downloadBuildingImage(@PathVariable("id") Long buildingId) throws Exception {
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/png"))
                .body(new ByteArrayResource(attachmentService.downloadBuildingImage(buildingId)));
    }
}
