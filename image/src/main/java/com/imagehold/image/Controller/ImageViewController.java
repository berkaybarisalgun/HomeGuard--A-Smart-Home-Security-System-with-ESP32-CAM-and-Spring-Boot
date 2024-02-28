package com.imagehold.image.Controller;

import com.imagehold.image.Entity.Image;
import com.imagehold.image.Repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/images")
public class ImageViewController {

    @Autowired
    private ImageRepository imageRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long id) {
        Image image = imageRepository.findById(id).orElseThrow(() -> new RuntimeException("Resim bulunamadı."));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/jpeg")) // Can be modified according to the type of the image
                .body(new ByteArrayResource(image.getData()));
    }
}