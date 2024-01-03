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

public class APIUser
{
    private String userName;
    private String password;
    private String accessToken;
    private String refreshToken;

    public APIUser(String userName, String password)
    {
        this.userName = userName;
        this.password = password;
    }

    public void getTokens()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.3.2:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<ResponseBody> call = apiService.createToken(this.userName, this.password);

        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {

                try
                {
                    JSONObject json = new JSONObject(response.body().string());
                    accessToken = json.getString("access_token");
                    refreshToken = json.getString("refresh_token");

                }
                catch (JSONException | IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
        ;
    }

    public String getAccessToken()
    {
        return this.accessToken;
    }
}
