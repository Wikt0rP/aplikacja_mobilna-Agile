package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AfterLogin extends AppCompatActivity
{
    //private ArrayList<String> arrayList;
    //private ArrayAdapter<String> adapter;
    private Float budgetSum = 0f;
    private Float expenseSum =  0f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        TextView textViewBudget = findViewById(R.id.TextViewBudget);

        String aToken = getIntent().getStringExtra("accessToken");
        String rToken = getIntent().getStringExtra("refreshToken");

        getBudget(aToken, rToken, textViewBudget);
        GenerateListView();
        GeneratePieChart(0, 0);



    }

    public void GeneratePieChart(float budget, float expenses)
    {
        if (budget <= 0 && expenses == 0)
        {
            budget = 0f;
            expenses = 100f;
        }
        int holeColor = Color.parseColor("#3d3d3d");
        int chartColor1 = Color.parseColor("#4881DF");

        int chartColor2 = Color.parseColor("#2FD7C4");

        PieChart pieChart = findViewById(R.id.pieChart);
        Legend legend = pieChart.getLegend();

        //Data for chart
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(budget, "Budżet"));
        entries.add(new PieEntry(expenses, "Wydatki"));


        //Adding data
        PieDataSet dataSet = new PieDataSet(entries, "Opis wykresu");

        dataSet.setColors(chartColor1, chartColor2);


        PieData pieData = new PieData(dataSet);
        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setHoleRadius(60f);
        pieChart.setHoleColor(holeColor);
        pieChart.setTransparentCircleRadius(10f);
        pieChart.setEntryLabelTextSize(20f);
        pieChart.invalidate();
        legend.setEnabled(false);
        pieChart.setUsePercentValues(false);
        Log.d("Wykres", "Budget: " + budget + " Expenses: " + expenses);


    }
    public void GenerateListView()
    {

       APIExpense apiExpense = new APIExpense(getIntent().getStringExtra("accessToken"), getIntent().getStringExtra("refreshToken"));
       apiExpense.getExpenses(new ExpensesCallback()
       {
           double sum = 0.0;
           @Override
           public void onExpenseRecieved(List<Expense> expenses)
           {
               List<String> expenseList = new ArrayList<>();
               for (Expense expense : expenses)
               {
                   expenseList.add(expense.getTitle() + ": " + expense.getAmount() + "zł");
                   sum += expense.getAmount();
                   Log.d("Expense", "razem: " + expense.getAmount());
               }

               ExpenseAdapter adapter = new ExpenseAdapter(AfterLogin.this, expenses);
               ListView listView = findViewById(R.id.ListViewBudget);
               listView.setAdapter(adapter);
               TextView textViewSum = findViewById(R.id.TVSum);
               textViewSum.setText(String.valueOf(sum));
               expenseSum = (float)sum;

               GeneratePieChart(budgetSum-(float)sum, (float) sum);
               Log.d("Wykres", "Budget: " + budgetSum + " Expenses: " + sum);

           }

           @Override
           public void onExpenseError(Throwable t)
           {
                Log.d("API Expense", "Failed to get expenses");
           }
       });

    }
    public void getBudget(String aToken, String rToken, TextView textViewBudget)
    {
        APIBalance apiBalance = new APIBalance(aToken, rToken);
        apiBalance.getBalance(new TokenBalanceCallback() {
            @Override
            public void onTokenBalanceReceived(double balance)
            {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textViewBudget.setText(String.valueOf(balance));
                    }

                });
                budgetSum = (float) balance;
                GeneratePieChart((float)balance-expenseSum, expenseSum);
            }

            @Override
            public void onBalanceError(Throwable t) {
                Log.d("API Balance", "Failed to get balance");
            }
        });
    }

    public void buttonDodaj(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Wybierz opcję");
        String[] options = {"Dodaj/usuń pieniądze", "Wydatek"};
        builder.setItems(options, (dialog, which) ->
        {
            switch (which) {
                case 0:
                    Intent intent = new Intent(AfterLogin.this, ActivityMoneyAdd.class);
                    intent.putExtra("accessToken", getIntent().getStringExtra("accessToken"));
                    intent.putExtra("refreshToken", getIntent().getStringExtra("refreshToken"));
                    startActivity(intent);
                    Log.d("AfterLogin", "Dodaj/usuń pieniądze");
                    break;
                case 1:
                    Intent intent2 = new Intent(AfterLogin.this, ActivityExpenseAdd.class);
                    intent2.putExtra("accessToken", getIntent().getStringExtra("accessToken"));
                    intent2.putExtra("refreshToken", getIntent().getStringExtra("refreshToken"));
                    startActivity(intent2);
                    Log.d("AfterLogin", "Wydatek");
                    break;
            }
        });
        builder.show();
    }
}