package com.aknosova.weatherapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.aknosova.weatherapplication.MainActivity.STATE;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textViewCity = findViewById(R.id.city);
        TextView textViewhumidity = findViewById(R.id.humidity);
        if (getIntent() != null && getIntent().getExtras() != null) {
            LocalParcel parcel = (LocalParcel) getIntent().getExtras().getSerializable(STATE);
            textViewCity.setText(parcel.getText());

            if (parcel.isChecked()) {
                textViewhumidity.setVisibility(View.VISIBLE);
            } else textViewhumidity.setVisibility(View.INVISIBLE);
        }
    }
}
