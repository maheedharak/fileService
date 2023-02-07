package com.dataservice.fileService.service.impl;

import com.dataservice.fileService.constants.CommonConstants;
import com.dataservice.fileService.dao.Datapoint;
import com.dataservice.fileService.dao.DatapointData;
import com.dataservice.fileService.model.DatapointDataModel;
import com.dataservice.fileService.model.FileDetails;
import com.dataservice.fileService.repository.DatapointDataRepo;
import com.dataservice.fileService.repository.DatapointRepo;
import com.dataservice.fileService.service.DatapointService;
import com.dataservice.fileService.utility.FileParseUtility;
import com.dataservice.fileService.utility.ObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DatapointServiceImpl implements DatapointService {
    private final FileParseUtility fileParseUtility;

    private final DatapointDataRepo datapointDataRepo;

    private final DatapointRepo dataPointRepo;

    private final ObjectConverter objectConverter;

    private static final String USERNAME = "dummy";

    @Autowired
    DatapointServiceImpl(FileParseUtility fileParseUtility, DatapointDataRepo datapointDataRepo, DatapointRepo dataPointRepo, ObjectConverter objectConverter) {
        this.fileParseUtility = fileParseUtility;
        this.datapointDataRepo = datapointDataRepo;
        this.dataPointRepo = dataPointRepo;
        this.objectConverter = objectConverter;
    }

    @Override
    public String processFile(FileDetails fileDetails) throws IOException {
        Map<Integer,String> errorMap = new HashMap<>();
        List<DatapointDataModel> dataModelLst = fileParseUtility.parseCsvFile(fileDetails.getUploadedFile(),errorMap);
        if (errorMap.isEmpty() && !dataModelLst.isEmpty()) {
            Datapoint datapoint = new Datapoint(fileDetails.getFileName(), USERNAME, LocalDateTime.now());
            Datapoint savedDatapoint = dataPointRepo.saveAndFlush(datapoint);
            long datapointId = savedDatapoint.getId();
            int dataSize = dataModelLst.size();
       //     int i = 0;
            int startIndex = 0 ;
            int endIndex = Math.min(CommonConstants.THRESHHOLD_SIZE,dataSize);
            List<DatapointData> dataPointDataLst = dataModelLst.stream()
                    .map(obj -> objectConverter.generateDatapointDataObj(obj,datapointId))
                    .collect(Collectors.toList());
           do{

try {
    this.datapointDataRepo.saveAllAndFlush(dataPointDataLst.subList(startIndex,endIndex));
}catch(Exception e){
    System.out.println("Exception is "+e);
}
                startIndex = endIndex;
                endIndex = endIndex + CommonConstants.THRESHHOLD_SIZE;
                if(endIndex >dataSize){
                    endIndex = dataSize;
                }
            }while(startIndex < endIndex);
        }
        if(errorMap.isEmpty()) {
            return CommonConstants.DATA_INSERTION_SUCCESSFUL;
        }else{
            return CommonConstants.DATA_INSERTION_PARTIALLY_SUCCESSFUL + errorMap;
        }
    }

    @Override
    public DatapointDataModel getDataById(String primaryKey) {
        DatapointData datapointData = this.datapointDataRepo.findById(primaryKey).orElse(null);
        if (datapointData != null) {
            return objectConverter.generateDatapointModelObj(datapointData);
        }
        return null;
    }

    @Override
    public void deleteDataById(String primaryKey) {
        datapointDataRepo.deleteById(primaryKey);
    }


}
