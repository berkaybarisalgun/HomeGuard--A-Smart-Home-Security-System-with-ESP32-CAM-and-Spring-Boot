package com.imagehold.image.Service.Impl;

import com.imagehold.image.Entity.Image;
import com.imagehold.image.Repository.ImageRepository;
import com.imagehold.image.Service.ImageStorageService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ImageStorageServiceImpl implements ImageStorageService {

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private ImageRepository imageRepository;


    private static final int MAX_SIZE = 200_000;


    @Override
    public String uploadImage(MultipartFile file) throws Exception {
        System.out.println(emailService);
        System.out.println(imageRepository);
        String originalFileName = file.getOriginalFilename();
        String fileExtension = "";

        if (originalFileName != null && originalFileName.contains(".")) {
            fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        }

        if (file.getSize() > MAX_SIZE) {
            throw new RuntimeException("File size exceeds the maximum allowed limit of 200,000 bytes");
        }
        if (file.getOriginalFilename().toLowerCase().endsWith(".jpeg") || file.getOriginalFilename().toLowerCase().endsWith(".jpg")) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String timestamp = now.format(formatter);
            fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String fileName = "image_" + timestamp + fileExtension;

            Image image = new Image();
            image.setName(fileName);
            image.setData(file.getBytes());
            image.setCreatedAt(now);
            imageRepository.save(image);

            try {
                emailService.sendEmailWithAttachment("bbarisalgun@gmail.com", "Image sent", "The image is attached..", new ByteArrayResource(file.getBytes()), fileName);
                System.out.println("Email has been sent successfully with the uploaded image: " + fileName);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to send email with the image.");
            }

            return "The image was uploaded successfully " + fileName;
        } else {
            throw new RuntimeException("Unsupported file format. Only JPEG and JPG files are allowed.");
        }
    }
}
