package com.cis.aeocropbookingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cis.aeocropbookingapp.adapter.PlotAdapter;
import com.cis.aeocropbookingapp.models.BasicData;
import com.cis.aeocropbookingapp.models.ClassTypeId;
import com.cis.aeocropbookingapp.models.CropData;
import com.cis.aeocropbookingapp.models.CropVarietie;
import com.cis.aeocropbookingapp.models.DistModel;
import com.cis.aeocropbookingapp.models.Farmerpostmodel;
import com.cis.aeocropbookingapp.models.MandalModel;
import com.cis.aeocropbookingapp.models.TypeItem;
import com.cis.aeocropbookingapp.models.Villagemodel;
import com.cis.aeocropbookingapp.netService.RetrofitClient;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import belka.us.androidtoggleswitch.widgets.ToggleSwitch;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {

    private static final String LOG_TAG = SecondActivity.class.getSimpleName();

    ArrayAdapter cropNameArrayAdapter, cropvarietyArrayAdapter;
    Spinner cropNamespinner, cropvarietyspinner;
    private ArrayList<TypeItem> cropNameTypeArray, cropvarietyTypeArray;

    private List<ClassTypeId> cropNameList = new ArrayList<>();
    private List<CropVarietie> cropvarietyList = new ArrayList<>();

    private ClassTypeId selectedcropName;
    private CropVarietie selectedcropVariety;
    String PPBNumber, FarmerName, FatherName, MobileNumber, PlotSuveryNumber, PlotSizee;
    Integer DistrictId, MandalId, VillageId, ToogleDripId, SourceofIrrigationId;

    EditText sownacerage, bundplantname, plantsunderbund, seedcompany;
    TextView txtplotsCout;
    Button submitBtn, previousBtn, btnAddPlot;
    ToggleSwitch toggleCroptype, toggleOrganic, togglePolyhouse, toggleSvp;
    LinearLayout plotlyt;


    Farmerpostmodel farmerpostmodel = new Farmerpostmodel();
    List<CropData> croplist = new ArrayList<>();
    SpotsDialog dialog;

    RecyclerView rcvPlots;

    private PlotAdapter plotAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle extras = getIntent().getExtras();


        if (extras != null) {
            PPBNumber = extras.getString("PPBNumber");
            FarmerName = extras.getString("FarmerName");
            FatherName = extras.getString("FatherName");
            MobileNumber = extras.getString("MobileNumber");
            DistrictId = extras.getInt("DistrictId");
            MandalId = extras.getInt("MandalId");
            VillageId = extras.getInt("VillageId");

            PlotSuveryNumber = extras.getString("SurveryNumber");
            PlotSizee = extras.getString("PlotSize");
            SourceofIrrigationId = extras.getInt("SourceOfIrrId");
            ToogleDripId = extras.getInt("toogleDripID");
            // and get whatever type user account id is
        }
        dialog = new SpotsDialog(SecondActivity.this, "Please wait", R.style.Custom);

        toggleCroptype = findViewById(R.id.toggleCroptype);
        toggleOrganic = findViewById(R.id.toggleOrganic);
        togglePolyhouse = findViewById(R.id.togglePolyhouse);
        toggleSvp = findViewById(R.id.toggleSvp);
        cropNamespinner = findViewById(R.id.cropname);
        cropvarietyspinner = findViewById(R.id.cropvariety);



        sownacerage = findViewById(R.id.sownacreage);

        bundplantname = findViewById(R.id.bundplantname);
        plantsunderbund = findViewById(R.id.plantsunderbund);
        seedcompany = findViewById(R.id.seedcompany);

        submitBtn = findViewById(R.id.subitBtn);
        previousBtn = findViewById(R.id.previousBtn);
        btnAddPlot = findViewById(R.id.btnAddPlot);
        plotlyt = findViewById(R.id.plotlyt);
        txtplotsCout = findViewById(R.id.txtplotsCout);


        rcvPlots = findViewById(R.id.rcvPlots);
        rcvPlots.setLayoutManager(new LinearLayoutManager(this));
        plotAdapter = new PlotAdapter(SecondActivity.this, croplist);
        rcvPlots.setAdapter(plotAdapter);


        GetAllTypeCdDmtsforCropName(4);

        setCropVeritiesSpinner();


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validations()) {
                    dialog.show();
                    CropData crop = new CropData();
                    crop.setId(1);
                    crop.setFarmerPlotId(1);


                    if (toggleCroptype.getCheckedTogglePosition() == 0) {
                        crop.setCropTypeId(7);
                    } else {
                        crop.setCropTypeId(8);
                    }
                    crop.setSownAcreage(Double.parseDouble(sownacerage.getText().toString()));

                    if (toggleOrganic.getCheckedTogglePosition() == 0) {
                        crop.setIsOrganic(true);
                    } else {
                        crop.setIsOrganic(false);
                    }

                    if (togglePolyhouse.getCheckedTogglePosition() == 0) {
                        crop.setIsUnderPolyHouse(true);
                    } else {
                        crop.setIsUnderPolyHouse(false);
                    }

                    crop.setBundPlantName(bundplantname.getText().toString());
                    crop.setPlantsUnderBund(Integer.parseInt(plantsunderbund.getText().toString()));

                    if (toggleSvp.getCheckedTogglePosition() == 0) {
                        crop.setIsSVP(true);
                    } else {
                        crop.setIsSVP(false);
                    }
                    crop.setSeedCompanyName(seedcompany.getText().toString());

                    crop.setCropVarietyId(selectedcropVariety.getId());
                    crop.setCropNameId(selectedcropName.getId());
                    croplist.add(crop);

                    updatePlots();
                    PostFarmerDetails();

                }
            }
        });

        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validations()) {


                    CropData crop = new CropData();
                    crop.setId(1);
                    crop.setFarmerPlotId(1);


                    if (toggleCroptype.getCheckedTogglePosition() == 0) {
                        crop.setCropTypeId(7);
                    } else {
                        crop.setCropTypeId(8);
                    }
                    crop.setSownAcreage(Double.parseDouble(sownacerage.getText().toString()));

                    if (toggleOrganic.getCheckedTogglePosition() == 0) {
                        crop.setIsOrganic(true);
                    } else {
                        crop.setIsOrganic(false);
                    }

                    if (togglePolyhouse.getCheckedTogglePosition() == 0) {
                        crop.setIsUnderPolyHouse(true);
                    } else {
                        crop.setIsUnderPolyHouse(false);
                    }

                    crop.setBundPlantName(bundplantname.getText().toString());
                    crop.setPlantsUnderBund(Integer.parseInt(plantsunderbund.getText().toString()));

                    if (toggleSvp.getCheckedTogglePosition() == 0) {
                        crop.setIsSVP(true);
                    } else {
                        crop.setIsSVP(false);
                    }
                    crop.setSeedCompanyName(seedcompany.getText().toString());
                    crop.setCropVarietyId(selectedcropVariety.getId());

                    crop.setCropNameId(selectedcropName.getId());
                    croplist.add(crop);

                    updatePlots();
                    // Now Clear Data

                    cleardata();

                }


            }
        });

        cropNamespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position != 0) {

                    selectedcropName = cropNameList.get(position - 1);
                    Log.d("SelectedItem", "is :" + selectedcropName.getDesc());
                    getCropVarietie(selectedcropName.getId());
                }
                cropNamespinner.getSelectedItemId();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        cropvarietyspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {

                    selectedcropVariety = cropvarietyList.get(i - 1);
//                    Log.d("SelectedItem", "is :" + selectedsource.getDesc());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void cleardata() {

        cropNamespinner.setSelection(0);
        cropvarietyspinner.setSelection(0);



        toggleCroptype.setCheckedTogglePosition(0);
        toggleOrganic.setCheckedTogglePosition(0);
        togglePolyhouse.setCheckedTogglePosition(0);
        toggleSvp.setCheckedTogglePosition(0);
        bundplantname.setText("");
        plantsunderbund.setText("");
        seedcompany.setText("");
        sownacerage.setText("");

        updatePlots();

    }


    public void GetAllTypeCdDmtsforCropName(int id) {

        Call<List<ClassTypeId>> call = RetrofitClient.getInstance().getMyApi().getAllTypeCdDmts(id);

        //to perform the API call we need to call the method enqueue()
        //We need to pass a Callback with enqueue method
        //And Inside the callback functions we will handle success or failure of
        //the result that we got after the API call
        call.enqueue(new Callback<List<ClassTypeId>>() {
            @Override
            public void onResponse(Call<List<ClassTypeId>> call, Response<List<ClassTypeId>> response) {


                //In this point we got our hero list
                //thats damn easy right ;)
                Log.d(LOG_TAG, "==> Analysis ==> Responce :" + response.body().get(0).getDesc());
                List<ClassTypeId> stateModels = response.body();

                cropNameList = response.body();
                cropNameTypeArray = new ArrayList<>();
                cropNameTypeArray.add(new TypeItem(0, "Please Select"));
                for (ClassTypeId data : response.body()
                ) {
                    cropNameTypeArray.add(new TypeItem(data.getId(), data.getDesc()));
                }

                cropNameArrayAdapter = new SpinnerTypeArrayAdapter(SecondActivity.this, cropNameTypeArray);
                cropNameArrayAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
                cropNamespinner.setAdapter(cropNameArrayAdapter);

                //now we can do whatever we want with this list
            }

            @Override
            public void onFailure(Call<List<ClassTypeId>> call, Throwable t) {
                //handle error or failure cases here
                Log.d(LOG_TAG, "==> Analysis ==> ERROR :" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setCropVeritiesSpinner() {
        cropvarietyTypeArray = new ArrayList<>();
        cropvarietyTypeArray.add(new TypeItem(0, "Please Select"));
        cropvarietyArrayAdapter = new SpinnerTypeArrayAdapter(SecondActivity.this, cropvarietyTypeArray);
        cropvarietyspinner.setAdapter(cropvarietyArrayAdapter);
        cropvarietyspinner.setSelection(0);
    }

    public void getCropVarietie(int id) {
        Call<List<CropVarietie>> call = RetrofitClient.getInstance().getMyApi().GetCropVarietiesByCropId(id);
        call.enqueue(new Callback<List<CropVarietie>>() {
            @Override
            public void onResponse(Call<List<CropVarietie>> call, Response<List<CropVarietie>> response) {
                cropvarietyList = response.body();
                cropvarietyTypeArray = new ArrayList<>();
                cropvarietyTypeArray.add(new TypeItem(0, "Please Select"));
                for (CropVarietie data : response.body()
                ) {
                    cropvarietyTypeArray.add(new TypeItem(data.getId(), data.getVarietyName()));
                }

                cropvarietyArrayAdapter = new SpinnerTypeArrayAdapter(SecondActivity.this, cropvarietyTypeArray);
                cropvarietyArrayAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
                cropvarietyspinner.setAdapter(cropvarietyArrayAdapter);
            }

            @Override
            public void onFailure(Call<List<CropVarietie>> call, Throwable t) {

            }
        });

    }
    public void popupMessage(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Would you Like to Add More Farmer and Crop details");
        alertDialogBuilder.setIcon(R.drawable.tslogooo);
        alertDialogBuilder.setTitle("Posted Successfully ");
        alertDialogBuilder.setNegativeButton("ok", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("internet","Ok btn pressed");
                // add these two lines, if you wish to close the app:
                finish();

            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    private void updatePlots() {
        if (croplist != null & croplist.size() > 0) {
            txtplotsCout.setText("Added Plots ( "+croplist.size()+" ) ");
            plotlyt.setVisibility(View.VISIBLE);
        } else
            plotlyt.setVisibility(View.GONE);


        rcvPlots.setLayoutManager(new LinearLayoutManager(this));
        plotAdapter = new PlotAdapter(SecondActivity.this, croplist);
        rcvPlots.setAdapter(plotAdapter);
    }

    public boolean validations() {
         if (cropNamespinner.getSelectedItemPosition() == 0) {

            Toast.makeText(this, "Please Select Crop Name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (cropvarietyspinner.getSelectedItemPosition() == 0) {

            Toast.makeText(this, "Please Select Crop Variety", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(sownacerage.getText().toString())) {
            Toast.makeText(this, "Please Enter Sown acreage", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(bundplantname.getText().toString())) {
            Toast.makeText(this, "Please Enter Bund Plant Name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(plantsunderbund.getText().toString())) {
            Toast.makeText(this, "Please Enter Plants Under Bund", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(seedcompany.getText().toString())) {
            Toast.makeText(this, "Please Enter Seed Company", Toast.LENGTH_SHORT).show();
            return false;
        } else {

            return true;
        }
    }


    public void PostFarmerDetails() {
        BasicData basicData = new BasicData();
        basicData.setId(1);
        basicData.setPPBNumber(PPBNumber);
        basicData.setStateId(1);
        basicData.setDistrictId(DistrictId);
        basicData.setMandalId(MandalId);
        basicData.setVillageId(VillageId);
        basicData.setFarmerName(FarmerName);
        basicData.setFatherName(FatherName);
        basicData.setMobileNumber(MobileNumber);
        basicData.setSurveyNumber(PlotSuveryNumber);
        basicData.setPlotArea(Double.parseDouble(PlotSizee));
        basicData.setIrrigationSourceId(SourceofIrrigationId);
        basicData.setStateId(1);
        basicData.setIrrigationTypeId(ToogleDripId);



        farmerpostmodel.setBasicData(basicData);
        farmerpostmodel.setCropData(croplist);

        Gson gson = new Gson();
        String json = gson.toJson(farmerpostmodel);
        Log.d("POST", "JSON =>" + json);
        Call<String> call = RetrofitClient.getInstance().getMyApi().addFarmerPlots(farmerpostmodel);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                dialog.cancel();
                if (response.code() == 200) {
                    Log.d(LOG_TAG, "==> Analysis ==> Farmer :" + response);
//                    Toast.makeText(SecondActivity.this, "Your Details Submited Successfully", Toast.LENGTH_SHORT).show();
//                    finish();
                    Intent intent = new Intent(SecondActivity.this, SuccessActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(SecondActivity.this, "Unable to submit data", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(LOG_TAG, "==> Analysis Farmer ==> ERROR :" + t.getMessage());
                dialog.cancel();
            }
        });
    }
}