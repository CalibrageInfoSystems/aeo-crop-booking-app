package com.cis.aeocropbookingapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CropVarietie {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cropId")
    @Expose
    private Integer cropId;
    @SerializedName("varietyName")
    @Expose
    private String varietyName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCropId() {
        return cropId;
    }

    public void setCropId(Integer cropId) {
        this.cropId = cropId;
    }

    public String getVarietyName() {
        return varietyName;
    }

    public void setVarietyName(String varietyName) {
        this.varietyName = varietyName;
    }

}
