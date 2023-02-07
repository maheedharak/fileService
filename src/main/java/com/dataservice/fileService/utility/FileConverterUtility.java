package com.dataservice.fileService.utility;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface FileConverterUtility {
    File multipartToFile(MultipartFile file) throws IOException;
}
