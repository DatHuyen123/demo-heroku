package com.demo.vnpt.dto;

import java.sql.Timestamp;

public abstract class BaseDTO {

    private Long id;

    private Timestamp createdDate;

    private Timestamp modifiedDate;

    private String createdBy;

    private String modifiedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

}
