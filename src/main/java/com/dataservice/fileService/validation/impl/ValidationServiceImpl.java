package com.dataservice.fileService.validation.impl;

import com.dataservice.fileService.constants.CommonConstants;
import com.dataservice.fileService.constants.FileHeaders;
import com.dataservice.fileService.constants.FileType;
import com.dataservice.fileService.model.FileDetails;
import com.dataservice.fileService.validation.ValidationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.regex.Pattern;
@Service
public class ValidationServiceImpl implements ValidationService {
    @Override
    public boolean validateUploadedFile(FileDetails uploadedFile){
        return validateFileType(uploadedFile) && validateFileSize(uploadedFile) && validateFileHeaders(uploadedFile);
    }

    @Override
    public boolean validateFileType(FileDetails uploadedFile) {
       for(FileType types: FileType.values()){
           if(types.getType().equals(uploadedFile.getFileType())){
               return true;
           }
       }
        return false;

    }

    @Override
    public boolean validateFileSize(FileDetails uploadedFile) {
        return (uploadedFile.getFileSize() < CommonConstants.MAXFILESIZE);
    }

    @Override
    public boolean validateFileHeaders(FileDetails uploadedFile) {
        String[] fileHeaders = uploadedFile.getFileHeaders();
        FileHeaders[] templateHeader =FileHeaders.values();
        int templatesLength = templateHeader.length;
       for(int i=0;i<templatesLength;i++){
           if(!fileHeaders[i].equals(templateHeader[i].toString())){
               return false;
           }
       }
        return true;
    }

    @Override
    public boolean ValidateFileContent(FileDetails uploadedFile) {

        return false;
    }

    @Override
    public boolean validateInputParameter(FileDetails fileDetails) {

        return  Pattern.matches(CommonConstants.KEY_CONSTRAINT,fileDetails.getPrimaryKey()) ;
    }
}
