package com.example.assessmentproject.networking;

import com.example.assessmentproject.model.FactsModel;

import retrofit2.Call;
import retrofit2.http.POST;

public interface FactsDataService
{
    @POST("facts")
    Call<FactsModel> getRows();
}
