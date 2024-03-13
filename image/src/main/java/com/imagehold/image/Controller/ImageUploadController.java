package com.imagehold.image.Controller;

import com.imagehold.image.ByteArrayMultipartFile;
import com.imagehold.image.Service.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/images")
public class ImageUploadController {

    @Autowired
    private ImageStorageService imageService;

    @PostMapping("/")
    public ResponseEntity<String> uploadImage(@RequestBody byte[] imageBytes) {
        try {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String timestamp = now.format(formatter);
            String fileName = "image_" + timestamp + ".jpg";

            MultipartFile multipartFile = new ByteArrayMultipartFile(imageBytes, fileName);

            String response = imageService.uploadImage(multipartFile);


            return ResponseEntity.ok().body("The image was uploaded successfully: " + response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("An error occurred while uploading the image: " + e.getMessage());
        }
    }
}
