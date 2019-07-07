package com.aknosova.weatherapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.aknosova.weatherapplication.MainActivity.CITY;

public class SecondActivity extends AppCompatActivity {
    private TextView textViewCity;

    private String receivedDatas = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewCity = findViewById(R.id.city);

        if (getIntent() != null && getIntent().getExtras() != null) {
            receivedDatas = getIntent().getStringExtra(CITY);
        }
        textViewCity.setText(receivedDatas);
    }
}
