package com.example.myapplication;

public interface TokenBalanceCallback
{
    void onTokenBalanceReceived(double balance);
    void onBalanceError(Throwable t);
}
