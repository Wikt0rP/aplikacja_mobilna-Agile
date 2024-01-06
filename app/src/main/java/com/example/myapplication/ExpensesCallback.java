package com.example.myapplication;

import java.util.List;

public interface ExpensesCallback
{
    void onExpenseRecieved(double expenses);
    void onExpenseError(Throwable t);
}
