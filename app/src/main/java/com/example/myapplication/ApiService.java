package com.example.myapplication;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService
{
    // User
    @FormUrlEncoded
    @POST("/jwt/create/")
    Call<ResponseBody> createToken(@Field("username") String username, @Field("password") String password);



    // Wallet
    @GET("/wallet/user/")
    Call <ResponseBody> getBalance(@Header("Authorization") String AccessToken);

    @FormUrlEncoded
    @POST("/wallet/add/")
    Call <ResponseBody> addMoney(@Header("Authorization") String AccessToken, @Field("kwota") double amount);



    // Expense
    @GET("/expense/user/")
    Call <ResponseBody> getExpenses(@Header("Authorization") String AccessToken);

    @FormUrlEncoded
    @POST("/expense/create/")
    Call <ResponseBody> addExpense(@Header("Authorization") String AccessToken, @Field("kwota") double amount, @Field("tytul") String title);

    @DELETE("/expense/delete")
    Call <ResponseBody> deleteExpense(@Header("Authorization") String AccessToken, @Header("id") int id);



}