package com.example.myapplication;

import okhttp3.ResponseBody;

public interface BalanceAddCallback
{
    void onBalaceAdd(double balance);
    void onSuccess(ResponseBody response);
    void onError(String errorMessage);
}
