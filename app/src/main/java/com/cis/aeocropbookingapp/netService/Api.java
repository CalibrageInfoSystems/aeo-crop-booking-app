package com.cis.aeocropbookingapp.netService;

import com.cis.aeocropbookingapp.models.ClassTypeId;
import com.cis.aeocropbookingapp.models.CropVarietie;
import com.cis.aeocropbookingapp.models.DistModel;
import com.cis.aeocropbookingapp.models.Farmerpostmodel;
import com.cis.aeocropbookingapp.models.Hero;
import com.cis.aeocropbookingapp.models.MandalModel;
import com.cis.aeocropbookingapp.models.StateModel;
import com.cis.aeocropbookingapp.models.Villagemodel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
    String BASE_URL = "http://183.82.111.111/AEOCropBooking/API/";

    String LookUpCategory = "AddFarmerPlots";

    @GET("marvel")
    Call<List<Hero>> getHeroes();

    @GET("GetStates")
    Call<List<StateModel>> getStates();

    @GET("GetDistrictsByStateId/{id}")
    Call<List<DistModel>> getDistbyStateid(@Query("") int id);

    @GET("GetMandalsByDistrictId/{id}")
    Call<List<MandalModel>> getMandalsByDistrictId(@Query("") int id);

    @GET("GetVillagesByMandalId/{id}")
    Call<List<Villagemodel>> getVillagesByMandalId(@Query("") int id);

    @GET("GetAllTypeCdDmts/{id}")
    Call<List<ClassTypeId>> getAllTypeCdDmts(@Query("") int id);

    @GET("GetCropVarietiesByCropId/{id}")
    Call<List<CropVarietie>> GetCropVarietiesByCropId(@Query("") int id);

    @POST("AddFarmerPlots")
    Call<String> addFarmerPlots(@Body Farmerpostmodel farmerpostmodel);



}
