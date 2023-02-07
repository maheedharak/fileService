package com.dataservice.fileService.dao;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity

public class DatapointData implements Serializable {

    public DatapointData(String primaryKey, String name, String description, Date updatedTimeStamp, long datapointId) {
        this.primaryKey = primaryKey;
        this.name = name;
        this.description = description;
        this.dataPointId = datapointId;
        if(updatedTimeStamp != null) {
            this.updatedTimeStamp = LocalDateTime.from(updatedTimeStamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        }

    }

    public DatapointData(){

    }

    @Version
    private long version;
    @Id
    @Column(name = "PRIMARY_KEY")
    private String primaryKey;
    @Column(name = "NAME")
    private String name;

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getUpdatedTimestamp() {
        return this.updatedTimeStamp;
    }

    public void setUpdatedTimestamp(LocalDateTime updatedTimeStamp) {
        this.updatedTimeStamp = updatedTimeStamp;
    }

    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "UPDATED_TIMESTAMP")
    private LocalDateTime updatedTimeStamp;

    public long getDataPointId() {
        return dataPointId;
    }

    private long dataPointId;

}
