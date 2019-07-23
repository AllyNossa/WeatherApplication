package com.aknosova.weatherapplication;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


public class MainActivity extends AppCompatActivity implements FragmentNavigator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.yellow)));
        SearchCityFragment searchCityFragment = new SearchCityFragment();

        if (getSupportFragmentManager().findFragmentById(R.id.main_container) == null) {
            startFirstFragment(SearchCityFragment.TAG, searchCityFragment);
        }
    }

    @Override
    public void startFirstFragment(@NonNull String tag, @NonNull Fragment fragment) {
        commitFragment(fragment, tag);
    }

    @Override
    public void startSecondFragment(@NonNull String tag, @NonNull Bundle bundle, @NonNull Fragment fragment) {

        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        commitFragment(fragment, tag);
    }

    public void commitFragment(Fragment fragment, String tag) {
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
