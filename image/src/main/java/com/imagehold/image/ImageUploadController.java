package com.imagehold.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/images")
public class ImageUploadController {

    @Autowired
    private ImageStorageService imageStorageService;

    @PostMapping("/")
    public ResponseEntity<String> uploadImage(@RequestBody byte[] imageBytes) {
        try {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String timestamp = now.format(formatter);
            String fileName = "image_" + timestamp + ".jpg";
            imageStorageService.store(imageBytes, fileName);
            return ResponseEntity.ok().body("The image was uploaded successfully " + fileName);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("An error occurred while uploading the image: " + e.getMessage());
        }
    }
}