//package com.imagehold.image.Service.Impl;
//
//import jakarta.mail.internet.MimeMessage;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.InputStreamSource;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//import javax.mail.MessagingException;
//
//@Service
//@NoArgsConstructor
//@AllArgsConstructor
//public class EmailServiceImpl {
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    /**
//     * Sends an email with a file attachment.
//     * @param to Recipient's email address
//     * @param subject Email subject
//     * @param text Email body
//     * @param inputStreamSource InputStreamSource of the file
//     * @param fileName Name of the file
//     * @throws MessagingException If there is an error during email sending
//     */
//    public void sendEmailWithAttachment(String to, String subject, String text, InputStreamSource inputStreamSource, String fileName) throws MessagingException, jakarta.mail.MessagingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//        helper.setTo(to);
//        helper.setSubject(subject);
//        helper.setText(text);
//
//        // Add the image as an email attachment
//        helper.addAttachment(fileName, inputStreamSource, "image/jpeg");
//
//        mailSender.send(message);
//    }
//}
