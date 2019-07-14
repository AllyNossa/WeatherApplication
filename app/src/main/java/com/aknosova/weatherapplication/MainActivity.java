package com.aknosova.weatherapplication;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "LifeCycle";

    private SearchCityFragment searchCityFragment;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        writeLogs("onCreate()");

        searchCityFragment = (SearchCityFragment) getSupportFragmentManager()
                .findFragmentByTag("SEARCH_FRAGMENT");

        if (searchCityFragment == null) {
            searchCityFragment = new SearchCityFragment();
        }

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction
                .replace(R.id.main_container, searchCityFragment, "SEARCH_FRAGMENT")
                .commit();
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
