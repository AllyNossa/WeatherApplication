package com.aknosova.weatherapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private Button searchButton;
    private EditText editTextCity;
    public static final String STATE = "STATE";
    private String inputTextCity = null;
    private CheckBox checkbox1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        searchButton = findViewById(R.id.search_button);
        editTextCity = findViewById(R.id.search_input);
        checkbox1 = findViewById(R.id.checkbox1);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalParcel parcel = new LocalParcel();
                parcel.setText(editTextCity.getText().toString());
                parcel.setChecked(isCheckedCheckbox());
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(STATE, parcel);
                startActivity(intent);
            }
        });
    }

    private boolean isCheckedCheckbox() {
        if (checkbox1.isChecked()) {
            return true;
        }
        return false;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        inputTextCity = editTextCity.getText().toString();
        outState.putString(STATE, inputTextCity);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        inputTextCity = savedInstanceState.getString(STATE);
        editTextCity.setText(inputTextCity);
    }
}
