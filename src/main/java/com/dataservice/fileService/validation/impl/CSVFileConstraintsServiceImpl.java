package com.dataservice.fileService.validation.impl;

import com.dataservice.fileService.constants.CommonConstants;
import com.dataservice.fileService.constants.FileHeaders;
import com.dataservice.fileService.validation.CSVFileConstraintsService;
import org.springframework.stereotype.Service;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.constraint.StrRegEx;
import org.supercsv.cellprocessor.ift.CellProcessor;

@Service
public class CSVFileConstraintsServiceImpl implements CSVFileConstraintsService {


    @Override
    public CellProcessor[] getProcessors() {
        StrRegEx.registerMessage(CommonConstants.KEY_CONSTRAINT, FileHeaders.PRIMARY_KEY+" should be a non empty String");
        return new CellProcessor[]{
                new StrRegEx(CommonConstants.KEY_CONSTRAINT),
                new Optional(),
                new Optional(),
                new Optional(new ParseDate(CommonConstants.DATE_FORMAT))

        };

    }
}
