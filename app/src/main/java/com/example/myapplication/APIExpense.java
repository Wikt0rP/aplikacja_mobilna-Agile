package com.example.myapplication;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class APIExpense
{
    private String accessToken;
    private String refreshToken;
    private String baseURL ="http://192.168.1.31:8000/";
    private double amount;
    private String title;

    APIExpense(String accessToken, String refreshToken)
    {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public void getExpenses(ExpensesCallback expensesCallback)
    {
        Log.d("API Expense", accessToken);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getExpenses("Bearer " + accessToken).enqueue(new Callback<ResponseBody>()
        {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response)
            {
                if (response.body() != null)
                {
                    try {
                        JSONArray jsonArray = new JSONArray(response.body().string());
                        Gson gson = new Gson();
                        List<Expense> expenses = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++)
                        {
                            Expense expense = gson.fromJson(jsonArray.getJSONObject(i).toString(), Expense.class);
                            expenses.add(expense);
                            Log.d("API Expense", "Expense title: " + expense.getTitle() + ", amount: " + expense.getAmount());
                        }

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
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t)
            {
                Log.d("API Expense", "Failed to get expenses");
                expensesCallback.onExpenseError(t);
            }
        });
    }

    public void createExpense(CreateExpenseCallback createExpenseCallback, double amount, String title)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.addExpense("Bearer " + accessToken, amount, title).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    createExpenseCallback.onSuccess(response.body());
                } else {
                    createExpenseCallback.onError("Błąd serwera: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Log.d("API Expense", "Failed to create expense");
                createExpenseCallback.onError(t.getMessage());
            }
        });
    }

    public void deleteExpense(DeleteExpenseCallback deleteExpenseCallback, int id)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<ResponseBody> call = apiService.deleteExpense("Bearer " + accessToken, id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    deleteExpenseCallback.onSuccess();
                } else {
                    deleteExpenseCallback.onError("Failed to delete expense: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                deleteExpenseCallback.onError(t.getMessage());
            }
        });
    }


}
