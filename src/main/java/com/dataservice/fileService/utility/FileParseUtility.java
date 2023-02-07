package com.dataservice.fileService.utility;

import com.dataservice.fileService.model.DatapointDataModel;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FileParseUtility {

    List<DatapointDataModel> parseCsvFile(File file, Map<Integer, String> exceptionDetails) throws IOException;

    String[] parseCsvFileForHeaders(File file) throws IOException;

    int parseCsvFileForRows(File file) throws IOException;
}
