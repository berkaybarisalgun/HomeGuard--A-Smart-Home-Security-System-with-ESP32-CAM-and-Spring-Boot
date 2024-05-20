package com.imagehold.image.Controller;

import com.imagehold.image.Entity.Image;
import com.imagehold.image.Service.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/download")
public class ImageDownloadController {

    @Autowired
    private ImageStorageService imageService;

    @GetMapping("/all")
    public ResponseEntity<byte[]> downloadAllImages() throws IOException {
        byte[] zipData = imageService.zipAllImages();

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/zip"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"all_images.zip\"")
                .body(zipData);
    }


}
