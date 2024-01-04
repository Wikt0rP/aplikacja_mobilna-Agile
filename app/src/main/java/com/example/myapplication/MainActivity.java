// colors: https://colordesigner.io/#450003-5C0002-94090D-D40D12-FF1D23
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
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
        EditText userName = (EditText) findViewById(R.id.loginText);
        EditText password = (EditText) findViewById(R.id.passwordText);

        APIUser apiUser = new APIUser(userName.getText().toString(), password.getText().toString());
        apiUser.getTokens();

        Log.d("Access Token", apiUser.getAccessToken());

    }

    public void loginType(View view)
    {

    }

    public void passwordType(View view)
    {


    }
}