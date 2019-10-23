package com.aknosova.weatherapplication;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements FragmentNavigator, NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toobar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open_drawer, R.string.nav_close_drawer);

        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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

    public void commitFragmentNav(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, fragment)
                .commit();
    }

    public void startFragmentOnNav(@NonNull Fragment fragment) {
        commitFragmentNav(fragment);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (getSupportFragmentManager().findFragmentById(R.id.main_container) == null) {
            finish();
        }

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_context, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_menu:
                Toast.makeText(this, "Заглушка для поиска города", Toast.LENGTH_LONG).show();
                return true;
            case R.id.update_menu:
                Toast.makeText(this, "Заглушка для обновления информации", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;

        switch (id) {
            case R.id.nav_feedback:
                fragment = new FeedbackFragment();
                break;
            case R.id.nav_about:
                fragment = new AboutFragment();
                break;
            default:
                fragment = new SearchCityFragment();
        }

        if (fragment != null) {
            startFragmentOnNav(fragment);
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}
