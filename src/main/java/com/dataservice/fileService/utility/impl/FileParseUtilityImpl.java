package com.dataservice.fileService.utility.impl;

import com.dataservice.fileService.model.DatapointDataModel;
import com.dataservice.fileService.utility.FileParseUtility;
import com.dataservice.fileService.validation.CSVFileConstraintsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FileParseUtilityImpl implements FileParseUtility {

    private CSVFileConstraintsService csvFileConstraintService;

    private ICsvBeanReader csvBeanReader = null;

    @Autowired
    public FileParseUtilityImpl(CSVFileConstraintsService csvFileConstraintService) {
        this.csvFileConstraintService = csvFileConstraintService;

    }

    @Override
    public List<DatapointDataModel> parseCsvFile(File file, Map<Integer, String> exceptionDetails) throws IOException {
        csvBeanReader = new CsvBeanReader(new FileReader(file), CsvPreference.STANDARD_PREFERENCE);
        List<DatapointDataModel> datapointModelLst = new ArrayList<>();
        try {
            final String[] header = csvBeanReader.getHeader(true);

            final CellProcessor[] processors = csvFileConstraintService.getProcessors();


            DatapointDataModel dataPointModel;
            try{
                    while((dataPointModel = csvBeanReader.read(DatapointDataModel.class, header, processors)) != null){
                      datapointModelLst.add(dataPointModel);
                    }

            }catch (SuperCsvCellProcessorException exception) {
                    System.out.println("exception is "+exception);
                    exceptionDetails.put(csvBeanReader.getLineNumber(),"Exception in row "+exception.getCsvContext().getRowNumber()+" column "+exception.getCsvContext().getColumnNumber()+" with data "+ exception.getCsvContext().getRowSource());
                }


        } finally {
            if (csvBeanReader != null) {
                csvBeanReader.close();
            }
        }
        return datapointModelLst;
    }

    @Override
    public String[] parseCsvFileForHeaders(File file) throws IOException {
        System.out.println("into parse File");
        if(file != null) {
            csvBeanReader = new CsvBeanReader(new FileReader(file), CsvPreference.STANDARD_PREFERENCE);
            try {
                return csvBeanReader.getHeader(true);
            } finally {
                if (csvBeanReader != null) {
                    csvBeanReader.close();
                }
            }
        }else{
            return null;
        }

    }

    @Override
    public int parseCsvFileForRows(File file) throws IOException {
        System.out.println("into parse File");
        csvBeanReader = new CsvBeanReader(new FileReader(file), CsvPreference.STANDARD_PREFERENCE);
        try {
            return csvBeanReader.length();
        } finally {
            if (csvBeanReader != null) {
                csvBeanReader.close();
            }
        }

    }
}
