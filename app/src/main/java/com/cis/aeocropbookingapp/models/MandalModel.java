package com.cis.aeocropbookingapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MandalModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("districtId")
    @Expose
    private Integer districtId;
    @SerializedName("code")
    @Expose
    private Object code;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("isActive")
    @Expose
    private Boolean isActive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

}
