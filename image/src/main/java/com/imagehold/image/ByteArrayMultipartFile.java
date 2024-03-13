package com.imagehold.image;

import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.springframework.util.StringUtils;

public class ByteArrayMultipartFile implements MultipartFile {

    private final byte[] fileContent;
    private String fileName;

    public ByteArrayMultipartFile(byte[] fileContent, String fileName) {
        this.fileContent = fileContent;
        this.fileName = fileName;
    }

    @Override
    public String getName() {
        return StringUtils.cleanPath(fileName);
    }

    @Override
    public String getOriginalFilename() {
        return fileName;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return fileContent == null || fileContent.length == 0;
    }

    @Override
    public long getSize() {
        return fileContent.length;
    }

    @Override
    public byte[] getBytes() {
        return fileContent;
    }

    @Override
    public InputStream getInputStream() {
        return new ByteArrayInputStream(fileContent);
    }

    @Override
    public void transferTo(java.io.File dest) throws java.io.IOException, java.lang.IllegalStateException {
        throw new UnsupportedOperationException("Transfer to file is not supported");
    }
}
