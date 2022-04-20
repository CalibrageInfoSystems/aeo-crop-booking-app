package com.cis.aeocropbookingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.audiofx.BassBoost;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cis.aeocropbookingapp.adapter.PlotAdapter;
import com.cis.aeocropbookingapp.models.BasicData;
import com.cis.aeocropbookingapp.models.ClassTypeId;
import com.cis.aeocropbookingapp.models.CropData;
import com.cis.aeocropbookingapp.models.DistModel;
import com.cis.aeocropbookingapp.models.Farmerpostmodel;
import com.cis.aeocropbookingapp.models.Hero;
import com.cis.aeocropbookingapp.models.MandalModel;
import com.cis.aeocropbookingapp.models.StateModel;
import com.cis.aeocropbookingapp.models.TypeItem;
import com.cis.aeocropbookingapp.models.Villagemodel;
import com.cis.aeocropbookingapp.netService.RetrofitClient;

import java.util.ArrayList;
import java.util.List;


import belka.us.androidtoggleswitch.widgets.ToggleSwitch;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    ArrayAdapter districtspinnerArrayAdapter, mandalspinnerArrayAdapter, villagespinnerArrayAdapter,sourceofirrigationArrayAdapter;
    Spinner districtSpinner, mandalspinner, villagespinner, sourceofirrigationSpinner;
    private ArrayList<TypeItem> districtTypeArray, mandalTypeArray, villageTypeArray,sourceofirrigationTypeArray;
    EditText ppbnumber, farmername, fathername, mobilenumber, surverynumber, plotsize;
    Button nextBtn;
    ToggleSwitch toggle_drip;

    private List<ClassTypeId> sourceofirrigationList = new ArrayList<>();
    private List<DistModel> distList = new ArrayList<>();
    private List<MandalModel> mandalList = new ArrayList<>();
    private List<Villagemodel> villageList = new ArrayList<>();

    private ClassTypeId selectedsource;
    private DistModel selectedDist;
    private MandalModel selectedMandal;
    private Villagemodel selectedVillage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        districtSpinner = findViewById(R.id.districtSpinner);
        mandalspinner = findViewById(R.id.mandalspinner);
        mandalspinner.setPrompt("Please Select");
        villagespinner = findViewById(R.id.villagespinner);

        ppbnumber = findViewById(R.id.ppbnumber_et);
        farmername = findViewById(R.id.farmername);
        fathername = findViewById(R.id.fathername);
        mobilenumber = findViewById(R.id.mobilenumber);

        sourceofirrigationSpinner = findViewById(R.id.sourceofirrigation);
        toggle_drip = findViewById(R.id.toggle_drip);

        surverynumber = findViewById(R.id.plotsurverynumber);
        plotsize = findViewById(R.id.plotsizeynumber);

        nextBtn = findViewById(R.id.nextButton);


        getStates();
        getDistByStateid(1);
        GetAllTypeCdDmts(1);

//        postFarmerdetails();
//        dialog.show();
        // TODO Add Progress Dialogue


        sourceofirrigationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {

                    selectedsource = sourceofirrigationList.get(i - 1);
                    Log.d("SelectedItem", "is :" + selectedsource.getDesc());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validations()) {

                    // TODO Send Data and clear here

//                    Intent i = new Intent(this,)

                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("PPBNumber", ppbnumber.getText().toString());
//                    intent.putExtra("StateId",1);
                    intent.putExtra("DistrictId", selectedDist.getId());
                    intent.putExtra("MandalId", selectedMandal.getId());
                    intent.putExtra("VillageId", selectedVillage.getId());
                    intent.putExtra("FarmerName", farmername.getText().toString());
                    intent.putExtra("FatherName", fathername.getText().toString());
                    intent.putExtra("MobileNumber", mobilenumber.getText().toString());
                    intent.putExtra("SurveryNumber", surverynumber.getText().toString());
                    intent.putExtra("PlotSize", plotsize.getText().toString());
                    intent.putExtra("SourceOfIrrId", selectedsource.getId());
                    if (toggle_drip.getCheckedTogglePosition() == 0) {
                        intent.putExtra("toogleDripID", 7);
                    } else {
                        intent.putExtra("toogleDripID", 8);
                    }

                    startActivity(intent);

                    cleardata();

                }
            }
        });


        districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position != 0) {

                    selectedDist = distList.get(position - 1);
                    Log.d("SelectedItem", "is :" + selectedDist.getName());
                }
                districtSpinner.getSelectedItemId();


              IntilizeDistSpinner();

                if (districtSpinner.getSelectedItemId() != 0) {

                    GetMandalsByDistrictId((int) districtSpinner.getSelectedItemId());
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mandalspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                mandalspinner.getSelectedItemId();
                if (position != 0) {
                    selectedMandal = mandalList.get(position - 1);
                    Log.d("SelectedItem", "is :" + selectedMandal.getName());
                }


                if (mandalspinner.getSelectedItemId() != 0) {

                    GetVillagesByMandalId((int) mandalspinner.getSelectedItemId());
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        villagespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    selectedVillage = villageList.get(i - 1);
                    Log.d("SelectedItem", "is :" + selectedVillage.getName());
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    public void cleardata() {
        surverynumber.setText("");
        plotsize.setText("");
        sourceofirrigationSpinner.setSelection(0);
        toggle_drip.setCheckedTogglePosition(0);
        ppbnumber.setText("");
        farmername.setText("");
        fathername.setText("");
        mobilenumber.setText("");

        districtSpinner.setSelection(0);
        mandalspinner.setSelection(0);
        villagespinner.setSelection(0);


    }

    public void getStates() {

        Call<List<StateModel>> call = RetrofitClient.getInstance().getMyApi().getStates();

        //to perform the API call we need to call the method enqueue()
        //We need to pass a Callback with enqueue method
        //And Inside the callback functions we will handle success or failure of
        //the result that we got after the API call
        call.enqueue(new Callback<List<StateModel>>() {
            @Override
            public void onResponse(Call<List<StateModel>> call, Response<List<StateModel>> response) {

                //In this point we got our hero list
                //thats damn easy right ;)
                Log.d(LOG_TAG, "==> Analysis ==> Responce :" + response.body().get(0).getName());
                List<StateModel> stateModels = response.body();


                //now we can do whatever we want with this list
            }

            @Override
            public void onFailure(Call<List<StateModel>> call, Throwable t) {
                //handle error or failure cases here
                Log.d(LOG_TAG, "==> Analysis ==> ERROR :" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void IntilizeDistSpinner() {
        mandalTypeArray = new ArrayList<>();
        mandalTypeArray.add(new TypeItem(0, "Please Select"));
        mandalspinnerArrayAdapter = new SpinnerTypeArrayAdapter(MainActivity.this, mandalTypeArray);
        mandalspinner.setAdapter(mandalspinnerArrayAdapter);
        mandalspinner.setSelection(0);
        intilizevillagespinner();
    }

    public void intilizevillagespinner() {
        villageTypeArray = new ArrayList<>();
        villageTypeArray.add(new TypeItem(0, "Please Select"));
        villagespinnerArrayAdapter = new SpinnerTypeArrayAdapter(MainActivity.this, villageTypeArray);
        villagespinner.setAdapter(villagespinnerArrayAdapter);
        villagespinner.setSelection(0);
    }


    public void getDistByStateid(int id) {

        Call<List<DistModel>> call = RetrofitClient.getInstance().getMyApi().getDistbyStateid(id);

        call.enqueue(new Callback<List<DistModel>>() {
            @Override
            public void onResponse(Call<List<DistModel>> call, Response<List<DistModel>> response) {

                distList = response.body();
                districtTypeArray = new ArrayList<>();
                districtTypeArray.add(new TypeItem(0, "Please Select"));
                for (DistModel data : response.body()
                ) {
                    districtTypeArray.add(new TypeItem(data.getId(), data.getName()));
                }

                districtspinnerArrayAdapter = new SpinnerTypeArrayAdapter(MainActivity.this, districtTypeArray);
                districtspinnerArrayAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
                districtSpinner.setAdapter(districtspinnerArrayAdapter);

                IntilizeDistSpinner();
            }

            @Override
            public void onFailure(Call<List<DistModel>> call, Throwable t) {
                //handle error or failure cases here
                Log.d(LOG_TAG, "==> Analysis ==> ERROR :" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void GetMandalsByDistrictId(int id) {

        Call<List<MandalModel>> call = RetrofitClient.getInstance().getMyApi().getMandalsByDistrictId(id);
        call.enqueue(new Callback<List<MandalModel>>() {
            @Override
            public void onResponse(Call<List<MandalModel>> call, Response<List<MandalModel>> response) {

                Log.d(LOG_TAG, "==> Analysis ==> Responce :" + response.body().get(0).getName());
                mandalList = response.body();

                mandalTypeArray = new ArrayList<>();
                mandalTypeArray.add(new TypeItem(0, "Please Select"));
                for (MandalModel data : response.body()
                ) {
                    mandalTypeArray.add(new TypeItem(data.getId(), data.getName()));
                }

                mandalspinnerArrayAdapter = new SpinnerTypeArrayAdapter(MainActivity.this, mandalTypeArray);
                mandalspinnerArrayAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
                mandalspinner.setAdapter(mandalspinnerArrayAdapter);
                //now we can do whatever we want with this list
            }

            @Override
            public void onFailure(Call<List<MandalModel>> call, Throwable t) {
                //handle error or failure cases here
                Log.d(LOG_TAG, "==> Analysis ==> ERROR :" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void GetVillagesByMandalId(int id) {
        Call<List<Villagemodel>> call = RetrofitClient.getInstance().getMyApi().getVillagesByMandalId(id);
        call.enqueue(new Callback<List<Villagemodel>>() {
            @Override
            public void onResponse(Call<List<Villagemodel>> call, Response<List<Villagemodel>> response) {


                Log.d(LOG_TAG, "==> Analysis ==> Responce :" + response.body().get(0).getName());
                villageList = response.body();

                villageTypeArray = new ArrayList<>();
                villageTypeArray.add(new TypeItem(0, "Please Select"));
                for (Villagemodel data : response.body()
                ) {
                    villageTypeArray.add(new TypeItem(data.getId(), data.getName()));
                }

                villagespinnerArrayAdapter = new SpinnerTypeArrayAdapter(MainActivity.this, villageTypeArray);
                villagespinnerArrayAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
                villagespinner.setAdapter(villagespinnerArrayAdapter);

                //now we can do whatever we want with this list
            }

            @Override
            public void onFailure(Call<List<Villagemodel>> call, Throwable t) {
                //handle error or failure cases here
                Log.d(LOG_TAG, "==> Analysis ==> ERROR :" + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void GetAllTypeCdDmts(int id) {

        Call<List<ClassTypeId>> call = RetrofitClient.getInstance().getMyApi().getAllTypeCdDmts(id);


        call.enqueue(new Callback<List<ClassTypeId>>() {
            @Override
            public void onResponse(Call<List<ClassTypeId>> call, Response<List<ClassTypeId>> response) {


                //In this point we got our hero list
                //thats damn easy right ;)
                Log.d(LOG_TAG, "==> Analysis ==> Responce :" + response.body().get(0).getDesc());
                List<ClassTypeId> stateModels = response.body();

                sourceofirrigationList = response.body();
                sourceofirrigationTypeArray = new ArrayList<>();
                sourceofirrigationTypeArray.add(new TypeItem(0, "Please Select"));
                for (ClassTypeId data : response.body()
                ) {
                    sourceofirrigationTypeArray.add(new TypeItem(data.getId(), data.getDesc()));
                }

                sourceofirrigationArrayAdapter = new SpinnerTypeArrayAdapter(MainActivity.this, sourceofirrigationTypeArray);
                sourceofirrigationArrayAdapter.setDropDownViewResource(R.layout.simple_spinnerdropdown_item);
                sourceofirrigationSpinner.setAdapter(sourceofirrigationArrayAdapter);

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



    public void postFarmerdetails() {

        Farmerpostmodel farmerpostmodel = new Farmerpostmodel();

        List<CropData> croplist = new ArrayList<>();

        BasicData basicData = new BasicData();
        basicData.setId(1);
        basicData.setPPBNumber("PNB Number");
        basicData.setStateId(1);
        basicData.setDistrictId(1);
        basicData.setMandalId(1);
        basicData.setVillageId(1);
        basicData.setFarmerName("Mahesh");
        basicData.setFatherName("Gangadhar rao");
        basicData.setMobileNumber("7032214460");
        basicData.setSurveyNumber("T02555");
        basicData.setPlotArea(10.0);
        basicData.setIrrigationSourceId(1);
        basicData.setIrrigationTypeId(1);
        basicData.setStateId(1);


        CropData crop = new CropData();
        crop.setId(1);
        crop.setFarmerPlotId(1);
        crop.setCropTypeId(1);
        crop.setSownAcreage(10.3);
        crop.setIsOrganic(true);
        crop.setIsUnderPolyHouse(true);
        crop.setBundPlantName("BundPlant name");
        crop.setPlantsUnderBund(1);
        crop.setIsSVP(true);
        crop.setSeedCompanyName("Seed");

        farmerpostmodel.setBasicData(basicData);
        croplist.add(crop);

        farmerpostmodel.setCropData(croplist);


        Call<String> call = RetrofitClient.getInstance().getMyApi().addFarmerPlots(farmerpostmodel);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d(LOG_TAG, "==> Analysis ==> Farmer :" + response);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(LOG_TAG, "==> Analysis Farmer ==> ERROR :" + t.getMessage());
            }
        });
    }

    public boolean validations() {
        if (TextUtils.isEmpty(ppbnumber.getText().toString())) {
            Toast.makeText(this, "Please Enter PPB Number", Toast.LENGTH_SHORT).show();
            return false;
        } else if (districtSpinner.getSelectedItemPosition() == 0) {

            Toast.makeText(this, "Please Select District", Toast.LENGTH_SHORT).show();
            return false;
        } else if (mandalspinner.getSelectedItemPosition() == 0) {

            Toast.makeText(this, "Please Select Mandal", Toast.LENGTH_SHORT).show();
            return false;
        } else if (villagespinner.getSelectedItemPosition() == 0) {

            Toast.makeText(this, "Please Select Village", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(farmername.getText().toString())) {
            Toast.makeText(this, "Please Enter Farmer Name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(fathername.getText().toString())) {
            Toast.makeText(this, "Please Enter Father Name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(mobilenumber.getText().toString())) {
            Toast.makeText(this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(surverynumber.getText().toString())) {
            Toast.makeText(this, "Please Enter Plot Survey Number", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(plotsize.getText().toString())) {
            Toast.makeText(this, "Please Enter Plot Size", Toast.LENGTH_SHORT).show();
            return false;
        } else if (sourceofirrigationSpinner.getSelectedItemPosition() == 0) {

            Toast.makeText(this, "Please Select Source of Irrigation", Toast.LENGTH_SHORT).show();
            return false;
        } else {

            return true;
        }
    }



}