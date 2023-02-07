package com.dataservice.fileService.service;

import com.dataservice.fileService.model.DatapointDataModel;
import com.dataservice.fileService.model.FileDetails;

import java.io.IOException;

public interface DatapointService {
    String processFile(FileDetails fileDetails) throws IOException;

    DatapointDataModel getDataById(String primaryKey);

    void deleteDataById(String primaryKey);
}
