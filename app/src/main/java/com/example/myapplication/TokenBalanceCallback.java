package com.example.myapplication;

import okhttp3.ResponseBody;

public interface TokenBalanceCallback
{
    void onTokenBalanceReceived(double balance);
    void onBalanceError(Throwable t);
}
