package com.dataservice.fileService.test.utility;

import com.dataservice.fileService.model.FileDetails;
import com.dataservice.fileService.utility.FileConverterUtility;
import com.dataservice.fileService.utility.FileParseUtility;
import com.dataservice.fileService.utility.ObjectConverter;
import com.dataservice.fileService.utility.impl.FileConverterUtilityImpl;
import com.dataservice.fileService.utility.impl.FileParseUtilityImpl;
import com.dataservice.fileService.utility.impl.ObjectConverterImpl;
import com.dataservice.fileService.validation.CSVFileConstraintsService;
import com.dataservice.fileService.validation.ValidationService;
import com.dataservice.fileService.validation.impl.CSVFileConstraintsServiceImpl;
import com.dataservice.fileService.validation.impl.ValidationServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.IOException;

import static com.dataservice.fileService.test.Constants.PATH;
@RunWith(SpringRunner.class)
@SpringBootTest
class ValidationServiceImplTest {


    private static CSVFileConstraintsService fileConstraintsService;

    private static FileParseUtility fileParseUtility;

    private static FileConverterUtility fileConverterUtility;

    private static FileInputStream inputFile;

    private static MockMultipartFile multipartFile;

    private static ObjectConverter objectConverter;

    private static FileDetails fileDetails;


    private static ValidationService validationService;

    @BeforeAll
    public static void generateFile() throws IOException {


        inputFile = new FileInputStream(PATH);
        multipartFile = new MockMultipartFile("uploadFile", inputFile);
        fileConstraintsService = new CSVFileConstraintsServiceImpl();
        fileParseUtility = new FileParseUtilityImpl(fileConstraintsService);
        fileConverterUtility = new FileConverterUtilityImpl();
        objectConverter = new ObjectConverterImpl(fileParseUtility,fileConverterUtility);
        fileDetails = objectConverter.generateFileDetailsObj(multipartFile);
        validationService = new ValidationServiceImpl();
    }
    @Test
    public void validateUploadedFile()  {
        fileDetails.setFileType("csv");
        fileDetails.setFileSize(10485759);
        fileDetails.setFileHeaders(new String[]{"PRIMARY_KEY","NAME","DESCRIPTION","UPDATED_TIMESTAMP"});
        Assert.assertEquals(true,validationService.validateUploadedFile(fileDetails));

    }
    @Test
    public void validateInvalidFileTypeUploadedFile() throws IOException {
        fileDetails.setFileType("xls");
        fileDetails.setFileSize(10485759);
        fileDetails.setFileHeaders(new String[]{"PRIMARY_KEY","NAME","DESCRIPTION","UPDATED_TIMESTAMP"});
        Assert.assertEquals(false,validationService.validateUploadedFile(fileDetails));

    }
    @Test
    public void validateInvalidFileSizeUploadedFile() throws IOException {
        fileDetails.setFileType("xls");
        fileDetails.setFileSize(10485761);
        fileDetails.setFileHeaders(new String[]{"PRIMARY_KEY","NAME","DESCRIPTION","UPDATED_TIMESTAMP"});
        Assert.assertEquals(false,validationService.validateUploadedFile(fileDetails));

    }
    @Test
    public void validateInvalidFileHeadersUploadedFile() throws IOException {
        fileDetails.setFileType("xls");
        fileDetails.setFileSize(10485761);
        fileDetails.setFileHeaders(new String[]{"PRIMARY_KEY","DESCRIPTION","NAME","UPDATED_TIMESTAMP"});
        Assert.assertEquals(false,validationService.validateUploadedFile(fileDetails));

    }

    @Test
    public void validateFileType() {
        fileDetails.setFileType("csv");
        Assert.assertEquals(true,validationService.validateFileType(fileDetails));
    }
    @Test
    public void validateInvalidFileType() {
        fileDetails.setFileType("xls");
        Assert.assertEquals(validationService.validateFileType(fileDetails),false);
    }

    @Test
   public void validateFileSize() {
        fileDetails.setFileSize(10485759);
        Assert.assertEquals(true,validationService.validateFileSize(fileDetails));
    }

    @Test
    public void validateInvalidFileSize() {
        fileDetails.setFileSize(10485761);
        Assert.assertEquals(false,validationService.validateFileSize(fileDetails));
    }

    @Test
    void validateFileHeaders() {
        fileDetails.setFileHeaders(new String[]{"PRIMARY_KEY","NAME","DESCRIPTION","UPDATED_TIMESTAMP"});
        Assert.assertEquals(true,validationService.validateFileHeaders(fileDetails));
    }
    @Test
    void validateInvalidFileHeaders() {
        fileDetails.setFileHeaders(new String[]{"PRIMARY_KEY","DESCRIPTION","NAME","UPDATED_TIMESTAMP"});
        Assert.assertEquals(false,validationService.validateFileHeaders(fileDetails));
    }

    @Test
    void validateFileContent() {
    }

    @Test
    void validateInputParameter() {
    }
}