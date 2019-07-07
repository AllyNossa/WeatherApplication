package com.aknosova.weatherapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.aknosova.weatherapplication.MainActivity.CHECKBOX;
import static com.aknosova.weatherapplication.MainActivity.CITY;

public class SecondActivity extends AppCompatActivity {

    private String receivedDatas = null;
    private boolean receivedDatasCheckbox = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textViewCity = findViewById(R.id.city);
        TextView textViewhumidity = findViewById(R.id.humidity);

        if (getIntent() != null && getIntent().getExtras() != null) {
            receivedDatas = getIntent().getStringExtra(CITY);
            receivedDatasCheckbox = getIntent().getBooleanExtra(CHECKBOX, false);
        }
        textViewCity.setText(receivedDatas);
        if (receivedDatasCheckbox) {
            textViewhumidity.setVisibility(View.VISIBLE);
        } else textViewhumidity.setVisibility(View.INVISIBLE);
    }
}
