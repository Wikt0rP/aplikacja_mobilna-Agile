package com.example.myapplication;
import android.app.AlertDialog;
import android.util.Log;

import androidx.annotation.NonNull;

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

public class APIBalance
{
    private String accessToken;
    private String refreshToken;
    private String baseURL ="http://192.168.1.31:8000/";
    private double balance;

    public APIBalance(String accessToken, String refreshToken)
    {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public void getBalance(TokenBalanceCallback balanceCallback)
    {
        Log.d("API Balance", accessToken);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<ResponseBody> call = apiService.getBalance("Bearer " + accessToken);

        call.enqueue(new Callback<ResponseBody>()
        {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response)
            {
                if (response.body() != null)
                {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        double balance = Double.parseDouble(jsonObject.getString("kwota"));
                        balanceCallback.onTokenBalanceReceived(balance);
                    }
                    catch (JSONException | IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                else {
                    Log.d("API Balance", "Response body is null" + response.code());
                    balanceCallback.onBalanceError(new NullPointerException("Response body is null"));
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t)
            {
                Log.d("API Balance", "Failed to get balance");
                balanceCallback.onBalanceError(t);
            }
        });

    }

    public void addMoney(double amount, BalanceAddCallback balanceCallback)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<ResponseBody> call = apiService.addMoney("Bearer " + accessToken, amount);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful())
                {
                    balanceCallback.onSuccess(response.body());
                } else {
                    balanceCallback.onError("Błąd serwera: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t)
            {
                balanceCallback.onError("Błąd sieciowy: " + t.getMessage());
            }
        });

    }

}
