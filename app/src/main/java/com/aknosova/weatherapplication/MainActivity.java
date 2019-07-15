package com.aknosova.weatherapplication;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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

        startFragmentWithoutBackStack(searchCityFragment, "SEARCH_FRAGMENT");
    }


    public void startFragmentWithBackStack(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.main_container, fragment, tag)
                .addToBackStack(null)
                .commit();
    }

    public void startFragmentWithoutBackStack(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.main_container, fragment, tag)
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
