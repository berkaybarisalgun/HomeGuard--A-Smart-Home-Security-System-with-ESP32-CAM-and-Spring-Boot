package com.imagehold.image.Controller;

import com.imagehold.image.ByteArrayMultipartFile;
import com.imagehold.image.Entity.Image;
import com.imagehold.image.Service.ImageStorageService;
import com.imagehold.image.exception.PhotoNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/api/images")
public class ImageController {

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

    @GetMapping("/{id}")
    public ResponseEntity<Resource> findImage(@PathVariable Long id) throws PhotoNotFoundException {
        Image image = imageService.findById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/jpeg")) // Can be modified according to the type of the image
                .body(new ByteArrayResource(image.getData()));
    }

    @GetMapping("/all")
    public ResponseEntity<byte[]> downloadAllImages() throws IOException {
        List<Image> images = imageService.findAll();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);

        for (Image image : images) {
            ZipEntry zipEntry = new ZipEntry(image.getName()); // Ensure names are unique or use IDs
            zipOutputStream.putNextEntry(zipEntry);
            zipOutputStream.write(image.getData());
            zipOutputStream.closeEntry();
        }
        zipOutputStream.close();
        byteArrayOutputStream.close();

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/zip"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"all_images.zip\"")
                .body(byteArrayOutputStream.toByteArray());
    }




}
