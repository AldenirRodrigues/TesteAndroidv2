package com.example.testeandroidv2.server;

import com.example.testeandroidv2.model.Pagamentos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET("statements/{id}")
    Call<Pagamentos>setaExtrato(@Path("id")String id);
}
