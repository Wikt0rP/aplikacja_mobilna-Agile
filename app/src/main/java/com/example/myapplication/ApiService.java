package com.example.myapplication;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService
{
    @FormUrlEncoded
    @POST("/jwt/create")
    Call<ResponseBody> createToken(@Field("username") String username, @Field("password") String password);
}
