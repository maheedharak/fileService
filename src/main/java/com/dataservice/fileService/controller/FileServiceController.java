package com.dataservice.fileService.controller;

import com.dataservice.fileService.constants.CommonConstants;
import com.dataservice.fileService.constants.URLMappings;
import com.dataservice.fileService.model.DatapointDataModel;
import com.dataservice.fileService.model.FileDetails;
import com.dataservice.fileService.service.DatapointService;
import com.dataservice.fileService.utility.ObjectConverter;
import com.dataservice.fileService.validation.ValidationService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = URLMappings.FILE_CONTROLLER_URL)
public class FileServiceController {

    private ValidationService validationService;

    private ObjectConverter objConverter;

    private DatapointService datapointService;

    @Autowired
    FileServiceController(ValidationService validationService, ObjectConverter objConverter, DatapointService datapointService) {
        this.validationService = validationService;
        this.objConverter = objConverter;
        this.datapointService = datapointService;
    }

    @PostMapping(path = URLMappings.UPLOAD_CSV, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> uploadFile(@Valid @RequestParam("uploadFile") MultipartFile dataFile) throws IOException {
        FileDetails fileDetailsObj = objConverter.generateFileDetailsObj(dataFile);
        if (fileDetailsObj != null && validationService.validateUploadedFile(fileDetailsObj)) {
            return ResponseEntity.ok(datapointService.processFile(fileDetailsObj));
        }

        return ResponseEntity.ok((CommonConstants.DATA_INSERTION_FAILED).toString());

    }

    @GetMapping(path = URLMappings.GET_DATA, consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDataByPrimaryKey(@RequestParam("primaryKey")  String primaryKey) {
        DatapointDataModel dataModelObj = datapointService.getDataById(primaryKey);
        return ResponseEntity.ok(dataModelObj == null ? CommonConstants.NORECORDSFOUND : dataModelObj);
    }

    @DeleteMapping(path = URLMappings.DELETE_DATA, consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteDataByPrimaryKey(@Valid @RequestParam("primaryKey") String primaryKey) {
        datapointService.deleteDataById(primaryKey);
        return ResponseEntity.ok(CommonConstants.DATA_DELETION_FAILED);
    }

}
