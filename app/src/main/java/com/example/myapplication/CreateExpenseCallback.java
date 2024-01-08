package com.example.myapplication;

import okhttp3.ResponseBody;

public interface CreateExpenseCallback
{
    void onSuccess(ResponseBody response);
    void onError(String errorMessage);
}
