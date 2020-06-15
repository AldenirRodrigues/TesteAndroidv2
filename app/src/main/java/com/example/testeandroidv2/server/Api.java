package com.example.testeandroidv2.server;

import com.example.testeandroidv2.model.Extrato;
import com.example.testeandroidv2.model.Pagamentos;
import com.example.testeandroidv2.model.StatementList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {

    @GET("statements/{id}")
    Call<Pagamentos>setaExtrato(@Path("id")String id);

    @PUT("statements/{id}")
    Call<Pagamentos>adicionaItem(@Body StatementList statementList);



}
