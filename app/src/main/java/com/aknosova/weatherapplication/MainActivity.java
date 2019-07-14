package com.aknosova.weatherapplication;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "LifeCycle";

    SearchCityFragment searchCityFragment;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        writeLogs("onCreate()");

        searchCityFragment = new SearchCityFragment();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container, searchCityFragment).commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        writeLogs("onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        writeLogs("onResume()");
    }

    @Override
    protected void onPause() {
        writeLogs("onPause()");
        super.onPause();
    }

    @Override
    protected void onStop() {
        writeLogs("onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        writeLogs("onDestroy()");
        super.onDestroy();
    }

    private void writeLogs(String state) {
        Log.d(TAG, "Активити " + state);
    }
}
