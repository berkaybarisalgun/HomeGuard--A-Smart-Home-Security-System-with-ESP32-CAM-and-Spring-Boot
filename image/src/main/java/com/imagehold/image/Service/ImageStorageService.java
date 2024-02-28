package com.imagehold.image.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface ImageStorageService {
    String uploadImage(MultipartFile file) throws Exception;
}
