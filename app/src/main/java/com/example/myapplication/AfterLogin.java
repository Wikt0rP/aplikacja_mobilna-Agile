package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
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

public class AfterLogin extends AppCompatActivity {
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        GeneratePieChart();
        ListView ListView = (ListView) findViewById(R.id.ListViewBudget);
        arrayList = new ArrayList<String>();
        arrayList.add("Lorem ipsum dolor sit amet");
        arrayList.add("Lorem ipsum dolor sit amet");
        arrayList.add("Lorem ipsum dolor sit amet");
        arrayList.add("Lorem ipsum dolor sit amet");
        arrayList.add("Lorem ipsum dolor sit amet");
        arrayList.add("Lorem ipsum dolor sit amet");
        arrayList.add("Lorem ipsum dolor sit amet");
        arrayList.add("Lorem ipsum dolor sit amet");
        arrayList.add("Lorem ipsum dolor sit amet");
        arrayList.add("Lorem ipsum dolor sit amet");
        arrayList.add("Lorem ipsum dolor sit amet");
        arrayList.add("Lorem ipsum dolor sit amet");
        arrayList.add("Lorem ipsum dolor sit amet");
        arrayList.add("Lorem ipsum dolor sit amet");
        arrayList.add("Lorem ipsum dolor sit amet");
        arrayList.add("Lorem ipsum dolor sit amet");
        arrayList.add("Lorem ipsum dolor sit amet");
        arrayList.add("Lorem ipsum dolor sit amet");
        arrayList.add("Lorem ipsum dolor sit amet");
        arrayList.add("Lorem ipsum dolor sit amet");
        arrayList.add("Lorem ipsum dolor sit amet");
        arrayList.add("Lorem ipsum dolor sit amet");
        arrayList.add("Lorem ipsum dolor sit amet");
        arrayList.add("Lorem ipsum dolor sit amet");

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);
        ListView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }

    public void GeneratePieChart()
    {
        int holeColor = Color.parseColor("#3d3d3d");
        int chartColor1 = Color.parseColor("#2FD7C4");

        int chartColor2 = Color.parseColor("#4881DF");

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