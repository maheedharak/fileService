package com.dataservice.fileService.model;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
@Component
public class DatapointDataModel {
    public DatapointDataModel(String PRIMARY_KEY, String NAME, String DESCRIPTION, LocalDateTime updatedTimeStamp) {
        this.PRIMARY_KEY = PRIMARY_KEY;
        this.NAME = NAME;
        this.DESCRIPTION = DESCRIPTION;
        if(updatedTimeStamp != null) {
            this.UPDATED_TIMESTAMP = Date.from(updatedTimeStamp.atZone(ZoneId.systemDefault()).toInstant());
        }
    }
    public DatapointDataModel(){

    }
    private String PRIMARY_KEY;
    private String NAME;

    public String getPRIMARY_KEY() {
        return PRIMARY_KEY;
    }

    public void setPRIMARY_KEY(String PRIMARY_KEY) {
        this.PRIMARY_KEY = PRIMARY_KEY;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getDescription() {
        return DESCRIPTION;
    }

    public void setDescription(String description) {
        DESCRIPTION = description;
    }

    public Date getUPDATED_TIMESTAMP() {
        return UPDATED_TIMESTAMP;
    }

    public void setUPDATED_TIMESTAMP(Date UPDATED_TIMESTAMP) {
        this.UPDATED_TIMESTAMP = UPDATED_TIMESTAMP;
    }

    private String DESCRIPTION;
    private Date UPDATED_TIMESTAMP;

    @Override
    public String toString() {
        return "Primary_Key is " + this.PRIMARY_KEY + " Name is "
                + this.NAME + " Description is "
                + this.DESCRIPTION
                + " Timestamp is " + this.UPDATED_TIMESTAMP;
    }
}
