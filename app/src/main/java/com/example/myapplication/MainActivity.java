// colors: https://colordesigner.io/#450003-5C0002-94090D-D40D12-FF1D23
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
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


        pinClean = 0;
    }

    public void buttonLoginClick(View view)
    {


        Intent intent = new Intent (this, AfterLogin.class);
        startActivity(intent);
    }

    public void loginType(View view)
    {

    }

    public void passwordType(View view)
    {


    }



}