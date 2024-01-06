package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class ExpenseAdapter extends ArrayAdapter<Expense>
{
    private final Context context;
    private final List<Expense> expenses;

    public ExpenseAdapter(Context context, List<Expense> expenses) {
        super(context, R.layout.list_item, expenses);
        this.context = context;
        this.expenses = expenses;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, parent, false);

        TextView titleView = (TextView) rowView.findViewById(R.id.title);
        TextView amountView = (TextView) rowView.findViewById(R.id.amount);

        Expense expense = expenses.get(position);
        titleView.setText(expense.getTitle());
        amountView.setText(String.valueOf(expense.getAmount()));

        // Ustawianie kolorów tekstu
        titleView.setTextColor(Color.parseColor("#2FD7C4"));
        amountView.setTextColor(Color.parseColor("#4881DF"));

        return rowView;
    }
}
