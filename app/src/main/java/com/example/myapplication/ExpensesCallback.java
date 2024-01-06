package com.example.myapplication;

import java.util.List;

public interface ExpensesCallback
{
    void onExpenseRecieved(List<Expense> expenses);
    void onExpenseError(Throwable t);
}
