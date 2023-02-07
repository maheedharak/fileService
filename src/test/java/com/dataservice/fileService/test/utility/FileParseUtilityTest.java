package com.dataservice.fileService.test.utility;

import com.dataservice.fileService.model.DatapointDataModel;
import com.dataservice.fileService.utility.FileParseUtility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FileParseUtilityTest {

    @MockBean
    FileParseUtility fileParseUtility;



    @Test
    public void parseCsvFile() throws IOException {
        Map<Integer, String> exceptionDetails = new HashMap<>();
        List<DatapointDataModel> modelList = new ArrayList<>();
        Mockito.when(fileParseUtility.parseCsvFile(new File("C:\\Users\\mahee\\Downloads\\TestData\\SampleData.csv"),exceptionDetails))
                .thenReturn(modelList);
        Assert.isTrue(modelList.size() > 0,"ModelList cannot be empty");
    }


}
