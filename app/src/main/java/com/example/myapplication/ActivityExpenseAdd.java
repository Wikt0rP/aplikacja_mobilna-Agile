package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class ActivityExpenseAdd extends AppCompatActivity
{
    EditText etTitle;
    EditText etExpense;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_expense_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etTitle = (EditText) findViewById(R.id.etEditTitle);
        etExpense = (EditText) findViewById(R.id.etExpenseAmount);
    }

    public void buttonAddExpense(View view)
    {
        String title = etTitle.getText().toString();
        String expenseString = etExpense.getText().toString();

        double expense = Double.parseDouble(expenseString);
        Log.d("addExpense", "Tytuł wydatku: " + title + ", Kwota wydatku: " + expense);
    }
}