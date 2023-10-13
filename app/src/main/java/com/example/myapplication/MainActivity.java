package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int pinClean = 0;

    String userName = "Unknown";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView welcomeText = (TextView) findViewById(R.id.WelcomeText);
        welcomeText.setText("Witaj " + userName);
        pinClean = 0;
    }

    public void PinClick(View view)
    {

    }

    public void buttonLoginClick(View view)
    {
        Intent intent = new Intent (this, AfterLogin.class);
        startActivity(intent);
    }
}