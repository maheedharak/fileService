package com.dataservice.fileService.constants;

import org.springframework.beans.factory.annotation.Value;

public class CommonConstants {

    public static final String KEY_CONSTRAINT = "^[a-zA-Z0-9_]+";
    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final String DATA_INSERTION_SUCCESSFUL = "Data uploaded successfully";

    public static final String DATA_INSERTION_FAILED = "Data upload failed";

    public static final String DATA_DELETION_FAILED = "Data deleted successfully";

    public static final String DATA_INSERTION_PARTIALLY_SUCCESSFUL = "Data uploaded partially";

    public static final String NORECORDSFOUND = "No records found";

    public static final String TEMPFILEPATH ="java.io.tmpdir";

    public static final int THRESHHOLD_SIZE = 10000;


    public static long MAXFILESIZE = 10485760;
}
