package com.aknosova.weatherapplication;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            startFragment(SearchCityFragment.TAG, null);
        }

        if (getSupportFragmentManager().findFragmentById(R.id.main_container) == null) {
            startFragment(null, null);
        }
    }

    public void startFragment(@Nullable String tag, @Nullable Bundle bundle) {
        Fragment fragment;

        if (tag == null) {
            tag = SearchCityFragment.TAG;
        }

        switch (tag) {
            default:
            case SearchCityFragment.TAG:
                fragment = new SearchCityFragment();
                break;
            case DataDisplayFragment.TAG:
                fragment = new DataDisplayFragment();
                break;
        }

        if (bundle != null) {
            fragment.setArguments(bundle);
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, fragment)
                .addToBackStack(tag)
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (getSupportFragmentManager().findFragmentById(R.id.main_container) == null) {
            finish();
        }
    }
}
