package com.example.assessmentproject.networking;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.assessmentproject.model.FactsModel;
import com.example.assessmentproject.model.Row;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FactsRepository  {
    private ArrayList<Row> employees = new ArrayList<>();
    private  final MutableLiveData<List<Row>> mutableLiveData = new MutableLiveData<>();
   public Context con;


    public FactsRepository()
    {

    }

    public MutableLiveData<List<Row>> getMutableLiveData()
    {

        final FactsDataService userDataService = RetrofitClient.getService();
            Call<FactsModel> call = userDataService.getRows();
            call.enqueue(new Callback<FactsModel>() {
                @Override
                public void onResponse(@NonNull Call<FactsModel> call, @NonNull Response<FactsModel> response) {
                    FactsModel employeeDBResponse = response.body();
                    if (employeeDBResponse != null && employeeDBResponse.getRows() != null) {
                        employees = (ArrayList<Row>) employeeDBResponse.getRows();
                        mutableLiveData.setValue(employees);

                    }
                }

                @Override
                public void onFailure(@NonNull Call<FactsModel> call, @NonNull Throwable t) {
                }
            });


        return mutableLiveData;

    }
}
