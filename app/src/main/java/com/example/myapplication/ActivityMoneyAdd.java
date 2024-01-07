package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import okhttp3.ResponseBody;

public class ActivityMoneyAdd extends AppCompatActivity
{
    EditText editMoney;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_money_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editMoney = (EditText) findViewById(R.id.editMoney);

    }

    public void buttonAddMoney(View view)
    {
        String moneyString = editMoney.getText().toString();
        double money = Double.parseDouble(moneyString);
        Log.d("addMoney", "Kwota do dodania: " + money);

        APIBalance apiBalance = new APIBalance(getIntent().getStringExtra("accessToken"), getIntent().getStringExtra("refreshToken"));
        apiBalance.addMoney(money, new BalanceAddCallback() {
            @Override
            public void onBalaceAdd(double balance)
            {
                Log.d("addMoney", "Stan konta: " + balance);
            }

            @Override
            public void onSuccess(ResponseBody response)
            {
                Log.d("addMoney", "Odpowiedź serwera: " + response);
                Intent intent = new Intent(ActivityMoneyAdd.this, AfterLogin.class);
                intent.putExtra("accessToken", getIntent().getStringExtra("accessToken"));
                intent.putExtra("refreshToken", getIntent().getStringExtra("refreshToken"));
                startActivity(intent);
            }

            @Override
            public void onError(String errorMessage)
            {
                Log.d("addMoney", "Błąd: " + errorMessage);
            }
        });

    }
}