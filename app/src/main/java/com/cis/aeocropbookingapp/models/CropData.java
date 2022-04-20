package com.cis.aeocropbookingapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CropData {
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("FarmerPlotId")
    @Expose
    private Integer farmerPlotId;
    @SerializedName("CropTypeId")
    @Expose
    private Integer cropTypeId;
    @SerializedName("CropNameId")
    @Expose
    private Integer cropNameId;
    @SerializedName("CropVarietyId")
    @Expose
    private Integer cropVarietyId;
    @SerializedName("SownAcreage")
    @Expose
    private Double sownAcreage;
    @SerializedName("IsOrganic")
    @Expose
    private Boolean isOrganic;
    @SerializedName("IsUnderPolyHouse")
    @Expose
    private Boolean isUnderPolyHouse;
    @SerializedName("BundPlantName")
    @Expose
    private String bundPlantName;
    @SerializedName("PlantsUnderBund")
    @Expose
    private Integer plantsUnderBund;
    @SerializedName("IsSVP")
    @Expose
    private Boolean isSVP;
    @SerializedName("SeedCompanyName")
    @Expose
    private String seedCompanyName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFarmerPlotId() {
        return farmerPlotId;
    }

    public void setFarmerPlotId(Integer farmerPlotId) {
        this.farmerPlotId = farmerPlotId;
    }

    public Integer getCropTypeId() {
        return cropTypeId;
    }

    public void setCropTypeId(Integer cropTypeId) {
        this.cropTypeId = cropTypeId;
    }

    public Integer getCropNameId() {
        return cropNameId;
    }

    public void setCropNameId(Integer cropNameId) {
        this.cropNameId = cropNameId;
    }

    public Integer getCropVarietyId() {
        return cropVarietyId;
    }

    public void setCropVarietyId(Integer cropVarietyId) {
        this.cropVarietyId = cropVarietyId;
    }

    public Double getSownAcreage() {
        return sownAcreage;
    }

    public void setSownAcreage(Double sownAcreage) {
        this.sownAcreage = sownAcreage;
    }

    public Boolean getIsOrganic() {
        return isOrganic;
    }

    public void setIsOrganic(Boolean isOrganic) {
        this.isOrganic = isOrganic;
    }

    public Boolean getIsUnderPolyHouse() {
        return isUnderPolyHouse;
    }

    public void setIsUnderPolyHouse(Boolean isUnderPolyHouse) {
        this.isUnderPolyHouse = isUnderPolyHouse;
    }

    public String getBundPlantName() {
        return bundPlantName;
    }

    public void setBundPlantName(String bundPlantName) {
        this.bundPlantName = bundPlantName;
    }

    public Integer getPlantsUnderBund() {
        return plantsUnderBund;
    }

    public void setPlantsUnderBund(Integer plantsUnderBund) {
        this.plantsUnderBund = plantsUnderBund;
    }

    public Boolean getIsSVP() {
        return isSVP;
    }

    public void setIsSVP(Boolean isSVP) {
        this.isSVP = isSVP;
    }

    public String getSeedCompanyName() {
        return seedCompanyName;
    }

    public void setSeedCompanyName(String seedCompanyName) {
        this.seedCompanyName = seedCompanyName;
    }

}