package com.dataservice.fileService.utility;

import com.dataservice.fileService.dao.DatapointData;
import com.dataservice.fileService.model.DatapointDataModel;
import com.dataservice.fileService.model.FileDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
public interface ObjectConverter {

    DatapointData generateDatapointDataObj(DatapointDataModel dataModel, long dataPointId);

    DatapointDataModel generateDatapointModelObj(DatapointData dataModel);

    FileDetails generateFileDetailsObj(MultipartFile multipartFile) throws IOException;
}
