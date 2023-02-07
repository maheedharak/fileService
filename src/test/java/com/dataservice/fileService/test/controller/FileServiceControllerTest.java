package com.dataservice.fileService.test.controller;

import com.dataservice.fileService.constants.CommonConstants;
import com.dataservice.fileService.constants.URLMappings;
import com.dataservice.fileService.controller.FileServiceController;
import com.dataservice.fileService.service.DatapointService;
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
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.io.FileInputStream;

import static com.dataservice.fileService.test.Constants.PATH;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FileServiceController.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FileServiceControllerTest {

    @TestConfiguration
    static class FileServiceControllerTestContext {
        @Bean
        public ValidationServiceImpl validationServiceImpl() {
            return new ValidationServiceImpl();
        }
        @Bean
        public CSVFileConstraintsServiceImpl csvFileConstraintService() {
            return new CSVFileConstraintsServiceImpl();
        }
        @Bean
        public FileParseUtility fileParseConverterUtility() {
            return  new FileParseUtilityImpl(csvFileConstraintService());
        }
        @Bean
        public FileConverterUtilityImpl fileConverterUtility() {
            return  new FileConverterUtilityImpl();
        }
        @Bean
        public ObjectConverterImpl objectConverter() {
            return new ObjectConverterImpl(fileParseConverterUtility(),fileConverterUtility());
        }
    }
    @Autowired
    MockMvc mockMVC;

    @Autowired
    CSVFileConstraintsService csvFileConstraintService;

   @Autowired
    FileParseUtility fileParseUtility;

    @Autowired
    FileConverterUtility fileConverterUtility;

    @Autowired
    ValidationService validationService;

    @MockBean
    ObjectConverter objectConverter;

   @MockBean
    DatapointService datapointService;

    private static final String PRIMARYKEY = "Luci";
@Test
    public void testFileForNull() throws Exception {
         FileInputStream inputFile = new FileInputStream(PATH);
        MockMultipartFile multipartFile = new MockMultipartFile("uploadFile", inputFile);

        String response = mockMVC.perform(multipart(URLMappings.FILE_CONTROLLER_URL + URLMappings.UPLOAD_CSV)
                        .file(multipartFile).contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                               .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
    Assert.assertEquals(CommonConstants.DATA_INSERTION_SUCCESSFUL,response);

    }
    @Test
    public void getDataByPrimaryKey() throws Exception {
       String response = mockMVC.perform(get(URLMappings.FILE_CONTROLLER_URL + URLMappings.GET_DATA)
                .param("primaryKey",PRIMARYKEY)
               .contentType(MediaType.TEXT_PLAIN_VALUE))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        Assert.assertEquals(response,CommonConstants.NORECORDSFOUND);

    }

    @Test
    public void deleteDataByPrimaryKey() throws Exception {
        String response = mockMVC.perform(delete(URLMappings.FILE_CONTROLLER_URL + URLMappings.DELETE_DATA)
                        .param("primaryKey",PRIMARYKEY)
                        .contentType(MediaType.TEXT_PLAIN_VALUE))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        Assert.assertEquals(response,CommonConstants.NORECORDSFOUND);
    }
}


