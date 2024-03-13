package com.imagehold.image.Service;

import org.springframework.core.io.InputStreamSource;

public interface EmailService {

    void sendEmailWithAttachment(String to, String subject, String text, InputStreamSource inputStreamSource, String fileName);
}
