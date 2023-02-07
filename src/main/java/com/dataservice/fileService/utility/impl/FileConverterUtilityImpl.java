package com.dataservice.fileService.utility.impl;

import com.dataservice.fileService.utility.FileConverterUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Component
public class FileConverterUtilityImpl implements FileConverterUtility {
    private static final Logger logger = LoggerFactory.getLogger(FileConverterUtilityImpl.class);
    @Override
    public File multipartToFile(MultipartFile file)  {
        File newFile = null;
        try {
            String tempFileName = file.getOriginalFilename() + "_temp";
            newFile = new File(System.getProperty("java.io.tmpdir") + "/" + tempFileName);
            file.transferTo(newFile);
        }catch(Exception e){
            logger.error("Exception in multipart is ",e);
            System.out.println("Exception in multipart to FIle is "+e);

        }
        return newFile;
    }
}
