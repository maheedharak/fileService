package com.dataservice.fileService.dao;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Datapoint implements Serializable {
   public Datapoint(String fileName, String userName, LocalDateTime createdDate){
        this.fileName = fileName;
        this.userName = userName;
        this.createdDate = createdDate;
    }
    public Datapoint(){

    }
    @Id
    @GeneratedValue
    @SequenceGenerator(name = "datapointId", initialValue = 1, allocationSize = 100)
    private long Id;

    @Version
    private long version;
    private String fileName;
    private LocalDateTime createdDate;
    private String userName;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
