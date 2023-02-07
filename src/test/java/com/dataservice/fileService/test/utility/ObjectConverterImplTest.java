package com.dataservice.fileService.test.utility;


import com.dataservice.fileService.dao.DatapointData;
import com.dataservice.fileService.model.DatapointDataModel;
import com.dataservice.fileService.model.FileDetails;
import com.dataservice.fileService.utility.FileConverterUtility;
import com.dataservice.fileService.utility.FileParseUtility;
import com.dataservice.fileService.utility.ObjectConverter;
import com.dataservice.fileService.utility.impl.FileConverterUtilityImpl;
import com.dataservice.fileService.utility.impl.FileParseUtilityImpl;
import com.dataservice.fileService.utility.impl.ObjectConverterImpl;
import com.dataservice.fileService.validation.CSVFileConstraintsService;
import com.dataservice.fileService.validation.impl.CSVFileConstraintsServiceImpl;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
class ObjectConverterImplTest {

    private static CSVFileConstraintsService fileConstraintsService;

    private static FileParseUtility fileParseUtility;

    private static FileConverterUtility fileConverterUtility;


    private static ObjectConverter objectConverter;

   @BeforeAll
    public static void setUp(){
       fileConstraintsService = new CSVFileConstraintsServiceImpl();
       fileParseUtility = new FileParseUtilityImpl(fileConstraintsService);
       fileConverterUtility = new FileConverterUtilityImpl();
       objectConverter = new ObjectConverterImpl(fileParseUtility,fileConverterUtility);
    }

    @Test
    public void generateDatapointDataObjTest() {
        DatapointDataModel dataModel = new DatapointDataModel("abc", "Amar", "No descriptipn", LocalDateTime.now());
        DatapointData dataPointObj = objectConverter.generateDatapointDataObj(dataModel,1L);
        LocalDateTime excelTimeStamp = LocalDateTime.from(dataModel.getUPDATED_TIMESTAMP().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        Assert.assertEquals(dataPointObj.getName(), dataModel.getNAME());
        Assert.assertEquals(dataPointObj.getPrimaryKey(), dataModel.getPRIMARY_KEY());
        Assert.assertEquals(dataPointObj.getUpdatedTimestamp(), excelTimeStamp);
        Assert.assertEquals(dataPointObj.getDataPointId(), 1);
    }

    @Test
    public void generateDataDatapointTest(){
        DatapointData datapointData = new DatapointData("abc", "Amar", "No descriptipn", new Date(),1);
        DatapointDataModel dataModel = objectConverter.generateDatapointModelObj(datapointData);
        Assert.assertEquals(dataModel.getNAME(), datapointData.getName());
        Assert.assertEquals(dataModel.getPRIMARY_KEY(),datapointData.getPrimaryKey());
        Assert.assertEquals(dataModel.getUPDATED_TIMESTAMP(), Date.from(datapointData.getUpdatedTimestamp().atZone(ZoneId.systemDefault()).toInstant()));
        Assert.assertEquals(dataModel.getDescription(),datapointData.getDescription());
    }

    @Test
    public void generateFileDetailsObj() throws IOException {
        FileInputStream inputFile = new FileInputStream("C:\\Users\\mahee\\Downloads\\TestData\\SampleData.csv");
        MockMultipartFile multipartFile = new MockMultipartFile("SampleData.csv", "SampleData.csv", MediaType.MULTIPART_FORM_DATA_VALUE,inputFile);
        FileDetails fileDetailsObj = objectConverter.generateFileDetailsObj(multipartFile);
        Assert.assertEquals(multipartFile.getOriginalFilename(),fileDetailsObj.getFileName());
        Assert.assertEquals("csv",fileDetailsObj.getFileType());


    }
}