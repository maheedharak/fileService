package com.dataservice.fileService.constants;

public enum FileType {
    CSV("csv");
    private String type;

    FileType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

}
