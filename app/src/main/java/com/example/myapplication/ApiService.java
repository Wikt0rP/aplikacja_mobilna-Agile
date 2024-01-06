package com.example.myapplication;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService
{
    @FormUrlEncoded
    @POST("/jwt/create")
    Call<ResponseBody> createToken(@Field("username") String username, @Field("password") String password);

    @GET("/wallet/user")
    Call <ResponseBody> getBalance(@Header("Authorization") String AccessToken);

    @GET("/expense/user")
    Call <ResponseBody> getExpenses(@Header("Authorization") String AccessToken);
}