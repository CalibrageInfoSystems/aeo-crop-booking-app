package com.cis.aeocropbookingapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClassTypeId {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("classTypeId")
    @Expose
    private Integer classTypeId;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("isActive")
    @Expose
    private Boolean isActive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClassTypeId() {
        return classTypeId;
    }

    public void setClassTypeId(Integer classTypeId) {
        this.classTypeId = classTypeId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

}