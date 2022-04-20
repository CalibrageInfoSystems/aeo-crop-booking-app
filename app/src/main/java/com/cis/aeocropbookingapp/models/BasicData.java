package com.cis.aeocropbookingapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BasicData {
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("PPBNumber")
    @Expose
    private String pPBNumber;
    @SerializedName("StateId")
    @Expose
    private Integer stateId;
    @SerializedName("DistrictId")
    @Expose
    private Integer districtId;
    @SerializedName("MandalId")
    @Expose
    private Integer mandalId;
    @SerializedName("VillageId")
    @Expose
    private Integer villageId;
    @SerializedName("FarmerName")
    @Expose
    private String farmerName;
    @SerializedName("FatherName")
    @Expose
    private String fatherName;
    @SerializedName("MobileNumber")
    @Expose
    private String mobileNumber;
    @SerializedName("SurveyNumber")
    @Expose
    private String surveyNumber;
    @SerializedName("PlotArea")
    @Expose
    private Double plotArea;
    @SerializedName("IrrigationSourceId")
    @Expose
    private Integer irrigationSourceId;
    @SerializedName("IrrigationTypeId")
    @Expose
    private Integer irrigationTypeId;
    @SerializedName("StatusTypeId")
    @Expose
    private Integer statusTypeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPPBNumber() {
        return pPBNumber;
    }

    public void setPPBNumber(String pPBNumber) {
        this.pPBNumber = pPBNumber;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer getMandalId() {
        return mandalId;
    }

    public void setMandalId(Integer mandalId) {
        this.mandalId = mandalId;
    }

    public Integer getVillageId() {
        return villageId;
    }

    public void setVillageId(Integer villageId) {
        this.villageId = villageId;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getSurveyNumber() {
        return surveyNumber;
    }

    public void setSurveyNumber(String surveyNumber) {
        this.surveyNumber = surveyNumber;
    }

    public Double getPlotArea() {
        return plotArea;
    }

    public void setPlotArea(Double plotArea) {
        this.plotArea = plotArea;
    }

    public Integer getIrrigationSourceId() {
        return irrigationSourceId;
    }

    public void setIrrigationSourceId(Integer irrigationSourceId) {
        this.irrigationSourceId = irrigationSourceId;
    }

    public Integer getIrrigationTypeId() {
        return irrigationTypeId;
    }

    public void setIrrigationTypeId(Integer irrigationTypeId) {
        this.irrigationTypeId = irrigationTypeId;
    }

    public Integer getStatusTypeId() {
        return statusTypeId;
    }

    public void setStatusTypeId(Integer statusTypeId) {
        this.statusTypeId = statusTypeId;
    }

}
