package com.dataservice.fileService.validation;

import com.dataservice.fileService.model.FileDetails;


public interface ValidationService {
    boolean validateUploadedFile(FileDetails uploadedFile);

    boolean validateFileType(FileDetails uploadedFile);

    boolean validateFileSize(FileDetails uploadedFile);

    boolean validateFileHeaders(FileDetails uploadedFile);

    boolean ValidateFileContent(FileDetails uploadedFile);

    boolean validateInputParameter(FileDetails primaryKey);
}
