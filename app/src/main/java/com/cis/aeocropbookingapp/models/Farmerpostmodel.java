package com.cis.aeocropbookingapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Farmerpostmodel {
    @SerializedName("basicData")
    @Expose
    private BasicData basicData;
    @SerializedName("cropData")
    @Expose
    private List<CropData> cropData = null;

    public BasicData getBasicData() {
        return basicData;
    }

    public void setBasicData(BasicData basicData) {
        this.basicData = basicData;
    }

    public List<CropData> getCropData() {
        return cropData;
    }

    public void setCropData(List<CropData> cropData) {
        this.cropData = cropData;
    }

}