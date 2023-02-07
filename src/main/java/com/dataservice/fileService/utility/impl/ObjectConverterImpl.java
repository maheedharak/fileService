package com.dataservice.fileService.utility.impl;

import com.dataservice.fileService.constants.FileType;
import com.dataservice.fileService.dao.DatapointData;
import com.dataservice.fileService.model.DatapointDataModel;
import com.dataservice.fileService.model.FileDetails;
import com.dataservice.fileService.utility.FileConverterUtility;
import com.dataservice.fileService.utility.FileParseUtility;
import com.dataservice.fileService.utility.ObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ObjectConverterImpl implements ObjectConverter {
    private FileParseUtility fileParseUtility;
    private FileConverterUtility fileConverterUtility;

    @Autowired
    public ObjectConverterImpl(FileParseUtility fileParseUtility, FileConverterUtility fileConverterUtility) {

        this.fileConverterUtility = fileConverterUtility;
        this.fileParseUtility = fileParseUtility;
    }
    public ObjectConverterImpl(){

    }

    @Override
    public DatapointData generateDatapointDataObj(DatapointDataModel dataModel, long dataPointId) {
        return new DatapointData(dataModel.getPRIMARY_KEY(),
                dataModel.getNAME(),
                dataModel.getDescription(),
                dataModel.getUPDATED_TIMESTAMP(),
                dataPointId);

    }

    @Override
    public DatapointDataModel generateDatapointModelObj(DatapointData dataModel) {
        return new DatapointDataModel(dataModel.getPrimaryKey(),
                dataModel.getName(),
                dataModel.getDescription(),
                dataModel.getUpdatedTimestamp());

    }

    @Override
    public FileDetails generateFileDetailsObj(MultipartFile multipartFile) throws IOException {
        System.out.println("into fileDetails");
        String fileName = multipartFile.getOriginalFilename();
        String fileType = fileName!=null ? fileName.substring(fileName.lastIndexOf(".") + 1) : FileType.CSV.getType();
        File file = fileConverterUtility.multipartToFile(multipartFile);
        return new FileDetails(file, fileName, fileType, fileParseUtility.parseCsvFileForHeaders(file), multipartFile.getSize());
    }


}
