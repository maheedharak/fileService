package com.dataservice.fileService.test.utility;

import com.dataservice.fileService.constants.CommonConstants;
import com.dataservice.fileService.utility.FileConverterUtility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileConverterUtilityTest {
    @MockBean
    FileConverterUtility fileConverterUtility;

    @Test
    public void testMultiPartToFIle() throws IOException {
        FileInputStream inputFile = new FileInputStream("C:\\Users\\mahee\\Downloads\\TestData\\SampleData.csv");
        MockMultipartFile multipartFile = new MockMultipartFile("uploadFile", inputFile);
        String fileName = multipartFile.getOriginalFilename() + "_temp";
        Mockito.when(fileConverterUtility.multipartToFile(multipartFile)).thenReturn(new File(System.getProperty(CommonConstants.TEMPFILEPATH) + "/" + fileName));
    }
    @Test
    public void testMultiPartToFileWithNullInput() throws IOException {
        Mockito.when(fileConverterUtility.multipartToFile(null)).thenReturn(null);
    }
}
