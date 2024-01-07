package com.example.myapplication;

public interface DeleteExpenseCallback
{
    void onSuccess();
    void onError(String errorMessage);
}
