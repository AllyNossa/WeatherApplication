package com.aknosova.weatherapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity {
//    private Button searchButton;
//    private EditText editTextCity;
//    public static final String STATE = "STATE";
//    private String inputTextCity;
//    private CheckBox humidityParam;

    SearchCityFragment searchCityFragment;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchCityFragment = new SearchCityFragment();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container, searchCityFragment).addToBackStack(null).commit();


    }

//        searchButton = findViewById(R.id.search_button);
//        editTextCity = findViewById(R.id.search_input);
//        humidityParam = findViewById(R.id.cb_humidity);
//
//        searchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (editTextCity.getText().length() == 0) {
//                    Toast.makeText(MainActivity.this, getString(R.string.error_empty_value), Toast.LENGTH_SHORT).show();
//                } else {
//                    LocalParcel parcel = new LocalParcel();
//                    parcel.setText(editTextCity.getText().toString());
//                    parcel.setChecked(isCheckedCheckbox());
//                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//                    intent.putExtra(STATE, parcel);
//                    startActivity(intent);
//                }
//            }
//        });
//    }
//
//    private boolean isCheckedCheckbox() {
//        return humidityParam.isChecked();
//    }
//
//    @Override
//    public void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        inputTextCity = editTextCity.getText().toString();
//        outState.putString(STATE, inputTextCity);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        inputTextCity = savedInstanceState.getString(STATE);
//        editTextCity.setText(inputTextCity);
//    }
}
