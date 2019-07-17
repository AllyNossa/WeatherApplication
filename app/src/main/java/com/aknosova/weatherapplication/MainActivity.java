package com.aknosova.weatherapplication;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


public class MainActivity extends AppCompatActivity implements FragmentNavigator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (getSupportFragmentManager().findFragmentById(R.id.main_container) == null) {
            startFragment(null, null);
        }
    }

    @Override
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

        commitFragment(fragment, tag);
    }

    @Override
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
