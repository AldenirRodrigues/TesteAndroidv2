package com.example.testeandroidv2.server;

import com.example.testeandroidv2.model.ListExtract;
import com.example.testeandroidv2.model.Login;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    @GET("statements/{id}")
    Call<ListExtract>setaExtrato(@Path("id")String id);


    @FormUrlEncoded
    @POST("login")
    Observable<Login> logaUsuario(
            @Field("user")String usuer,
            @Field("password")String password);

}
