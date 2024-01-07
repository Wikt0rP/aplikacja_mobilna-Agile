package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class addMoney extends Activity
{
    EditText editMoney;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_add_money);

        editMoney = (EditText) findViewById(R.id.editMoney);

    }
    public void buttonAddMoney(View view)
    {
        String moneyString = editMoney.getText().toString();

        double money = Double.parseDouble(moneyString);
        Log.d("addMoney", "Kwota do dodania: " + money);


    }
}
