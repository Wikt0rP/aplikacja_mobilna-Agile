package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.github.mikephil.charting.charts.PieChart;
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
        PieChart pieChart = new PieChart(this);

        //Data for chart
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(30f, "Wartość 1"));
        entries.add(new PieEntry(40f, "Wartość 2"));
        entries.add(new PieEntry(50f, "Wartość 3"));

        //Adding data
        PieDataSet dataSet = new PieDataSet(entries, "Opis wykresu");

        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);


        PieData pieData = new PieData(dataSet);
        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleRadius(30f);
        pieChart.invalidate();
    }
}