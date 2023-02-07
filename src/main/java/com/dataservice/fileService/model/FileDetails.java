package com.dataservice.fileService.model;

import java.io.File;

public class FileDetails {

    public void setUploadedFile(File uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public void setFileHeaders(String[] fileHeaders) {
        this.fileHeaders = fileHeaders;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public FileDetails(File uploadedFile, String fileName, String fileType, String[] fileHeaders, long fileSize) {
        this.uploadedFile = uploadedFile;
        this.fileType = fileType;
        this.fileHeaders = fileHeaders;
        this.fileSize = fileSize;
        this.fileName = fileName;
    }

    public FileDetails(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    private File uploadedFile;

    public String getFileType() {
        return fileType;
    }

    private String fileType;

    public File getUploadedFile() {
        return uploadedFile;
    }

    public String[] getFileHeaders() {
        return fileHeaders;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    private String[] fileHeaders;
    private long fileSize;

    private String fileName;
    private boolean isValid;

    private String primaryKey;

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }
}
