package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class AfterLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        GeneratePieChart();

    }

    public void GeneratePieChart()
    {
        int holeColor = Color.parseColor("#3d3d3d");
        int chartColor1 = Color.parseColor("#099490");
        int chartColor2 = Color.parseColor("#D40D12");

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
}