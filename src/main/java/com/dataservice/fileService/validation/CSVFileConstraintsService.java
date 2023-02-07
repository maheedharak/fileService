package com.dataservice.fileService.validation;

import org.supercsv.cellprocessor.ift.CellProcessor;

public interface CSVFileConstraintsService {
    CellProcessor[] getProcessors();
}
