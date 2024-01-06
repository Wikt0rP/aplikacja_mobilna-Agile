package com.example.myapplication;

import java.util.List;

public interface ExpensesCallback
{
    void onExpenseRecieved(List<Double> expenses);
    void onExpenseError(Throwable t);
}
