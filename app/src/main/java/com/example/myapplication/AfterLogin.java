package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import java.util.List;

public class AfterLogin extends AppCompatActivity
{
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        TextView textViewBudget = findViewById(R.id.TextViewBudget);

        String aToken = getIntent().getStringExtra("accessToken");
        String rToken = getIntent().getStringExtra("refreshToken");

        getBudget(aToken, rToken, textViewBudget);
        GeneratePieChart();
        GenerateListView();

    }

    public void GeneratePieChart()
    {
        int holeColor = Color.parseColor("#3d3d3d");
        int chartColor1 = Color.parseColor("#4881DF");

        int chartColor2 = Color.parseColor("#2FD7C4");

        PieChart pieChart = findViewById(R.id.pieChart);
        Legend legend = pieChart.getLegend();

        //Data for chart
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(30f, "Budżet"));
        entries.add(new PieEntry(40f, "Wydatki"));


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


    }
    public void GenerateListView()
    {
       APIExpense apiExpense = new APIExpense(getIntent().getStringExtra("accessToken"), getIntent().getStringExtra("refreshToken"));
       apiExpense.getExpenses(new ExpensesCallback()
       {
           @Override
           public void onExpenseRecieved(double expenses)
           {
               runOnUiThread(new Runnable() {
                   @Override
                   public void run()
                   {
                       Log.d("API Expense", "Kwota: " + expenses);

                   }
               });
           }

           @Override
           public void onExpenseError(Throwable t) {

           }
       });

    }
    public void getBudget(String aToken, String rToken, TextView textViewBudget)
    {
        APIBalance apiBalance = new APIBalance(aToken, rToken);
        apiBalance.getBalance(new TokenBalanceCallback() {
            @Override
            public void onTokenBalanceReceived(double balance) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textViewBudget.setText(String.valueOf(balance));
                    }
                });
            }

            @Override
            public void onBalanceError(Throwable t) {
                Log.d("API Balance", "Failed to get balance");
            }
        });
    }
}