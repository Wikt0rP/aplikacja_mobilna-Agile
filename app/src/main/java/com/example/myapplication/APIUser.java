package com.example.myapplication;

import android.app.AlertDialog;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import java.io.Serializable;

public class APIUser
{
    private String userName;
    private String password;
    private String accessToken;
    private String refreshToken;
    private String baseURL = "http://192.168.1.31:8000/";

    public APIUser(String userName, String password)
    {
        this.userName = userName;
        this.password = password;
    }

    public void getTokens(TokenCallback callback)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<ResponseBody> call = apiService.createToken(userName, password);

        call.enqueue(new Callback<ResponseBody>()
        {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {
                if (response.isSuccessful())
                {
                    try
                    {
                        JSONObject json = new JSONObject(response.body().string());
                        accessToken = json.getString("access");
                        refreshToken = json.getString("refresh");
                        callback.onTokenReceived(accessToken, refreshToken);

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Log.d("APIUser", "Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t)
            {
                Log.d("APIUser", "Failure: " + t.getMessage());
            }
        });


    }
    public void getTokens()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<ResponseBody> call = apiService.createToken(userName, password);

        call.enqueue(new Callback<ResponseBody>()
        {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {
                if (response.isSuccessful())
                {
                    try
                    {
                        JSONObject json = new JSONObject(response.body().string());
                        accessToken = json.getString("access");
                        refreshToken = json.getString("refresh");

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Log.d("APIUser", "Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t)
            {
                Log.d("APIUser", "Failure: " + t.getMessage());
            }
        });


    }
    public String getAccessToken()
    {
       if (this.accessToken != null)
       {
           return this.accessToken;
       }
       else
       {
           getTokens();
           return this.accessToken;
       }
    }
    public String getRefreshToken()
    {
        if (this.refreshToken != null)
        {
            return this.refreshToken;
        }
        else
        {
            getTokens();
            return this.refreshToken;
        }
    }
    @Override
    public String toString()
    {
        return "APIUser{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", baseURL='" + baseURL + '\'' +
                '}';
    }
}
