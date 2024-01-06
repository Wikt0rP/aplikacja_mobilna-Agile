package com.example.myapplication;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class APIExpense
{
    private String accessToken;
    private String refreshToken;
    private String baseURL ="http://192.168.1.31:8000/";
    private double amount;

    APIExpense(String accessToken, String refreshToken)
    {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public void getExpenses(ExpensesCallback expensesCallback)
    {

        Log.d("API Expenses", accessToken);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<ResponseBody> call = apiService.getBalance("Bearer " + accessToken);

        call.enqueue(new Callback<ResponseBody>()
        {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {
                if (response.isSuccessful())
                {
                    try {
                        JSONArray jsonArray = new JSONArray(response.body().string());
                        Type listType = new TypeToken<List<Expense>>() {}.getType();
                        List<Expense> expenses = new Gson().fromJson(String.valueOf(jsonArray), listType);
                        Log.d("API Expenses", "Number of expenses received: " + expenses.size());
                        expensesCallback.onExpenseRecieved(expenses);
                    }
                    catch (JSONException | IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                else {
                    Log.d("API Balance", "Response body is null" + response.code());
                    expensesCallback.onExpenseError(new NullPointerException("Response body is null"));
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t)
            {
                Log.d("API Balance", "Failed to get balance");
                expensesCallback.onExpenseError(t);
            }
        });

    }
}
