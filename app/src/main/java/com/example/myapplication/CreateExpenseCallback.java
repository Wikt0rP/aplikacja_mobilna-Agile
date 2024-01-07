package com.example.myapplication;

import okhttp3.ResponseBody;

public interface CreateExpenseCallback
{
    void onSuccess(double balance);
    void onError(Throwable t);
}
