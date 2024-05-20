package com.imagehold.image.Service;

import com.imagehold.image.Entity.Image;
import com.imagehold.image.exception.PhotoNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ImageStorageService {
    String uploadImage(MultipartFile file) throws Exception;

    Image findById(Long id) throws PhotoNotFoundException;

    List<Image> findAll();

    byte[] zipAllImages() throws IOException;

}
