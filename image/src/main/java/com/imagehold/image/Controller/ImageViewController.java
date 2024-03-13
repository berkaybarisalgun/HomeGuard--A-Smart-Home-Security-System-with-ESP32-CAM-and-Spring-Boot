package com.imagehold.image.Controller;

import com.imagehold.image.Entity.Image;
import com.imagehold.image.Repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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