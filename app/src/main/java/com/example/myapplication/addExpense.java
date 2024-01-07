package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.util.Log;

public class addExpense extends Activity
{
    EditText editTitle;
    EditText editExpense;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        editTitle = (EditText) findViewById(R.id.editTitle);
        editExpense = (EditText) findViewById(R.id.editExpense);

    }

    public void buttonAddExpense(View view)
    {
        String title = editTitle.getText().toString();
        String expenseString = editExpense.getText().toString();

        double expense = Double.parseDouble(expenseString);
        Log.d("addExpense", "Tytuł wydatku: " + title + ", Kwota wydatku: " + expense);
    }
}
