package com.example.testeandroidv2.server;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataServer {

    private static final String BASE_URL = "https://bank-app-test.herokuapp.com/api/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public Api api = retrofit.create(Api.class);
}
