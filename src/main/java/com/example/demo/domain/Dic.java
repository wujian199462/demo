package com.example.demo.domain;

public class Dic {
    private String enTableName;
    private String cnTableName;
    private String enColName;
    private String cnColName;
    private String colIndex;

    public String getEnTableName() {
        return enTableName;
    }

    public void setEnTableName(String enTableName) {
        this.enTableName = enTableName;
    }

    public String getCnTableName() {
        return cnTableName;
    }

    public void setCnTableName(String cnTableName) {
        this.cnTableName = cnTableName;
    }

    public String getEnColName() {
        return enColName;
    }

    public void setEnColName(String enColName) {
        this.enColName = enColName;
    }

    public String getCnColName() {
        return cnColName;
    }

    public void setCnColName(String cnColName) {
        this.cnColName = cnColName;
    }

    public String getColIndex() {
        return colIndex;
    }

    public void setColIndex(String colIndex) {
        this.colIndex = colIndex;
    }
}
