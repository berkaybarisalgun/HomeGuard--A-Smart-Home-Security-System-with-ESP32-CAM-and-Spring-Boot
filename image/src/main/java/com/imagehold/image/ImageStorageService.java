package com.imagehold.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageStorageService {

    @Autowired
    private ImageRepository imageRepository;

    public void store(byte[] imageBytes, String fileName) {
        try {
            Image image = new Image();
            image.setName(fileName);
            image.setData(imageBytes);
            imageRepository.save(image);
        } catch (Exception e) {
            throw new RuntimeException("The file could not be saved: " + e.getMessage());
        }
    }
}


