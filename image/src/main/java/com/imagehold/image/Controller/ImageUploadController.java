package com.imagehold.image.Controller;

import com.imagehold.image.Service.ImageStorageService;
import com.imagehold.image.Service.Impl.ImageStorageServiceImpl;
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
    private ImageStorageService imageService;


    @PostMapping(path = "/", consumes = "multipart/form-data")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file) {
        try {
            String response = imageService.uploadImage(file);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("An error occurred while uploading the image: " + e.getMessage());
        }
    }
}