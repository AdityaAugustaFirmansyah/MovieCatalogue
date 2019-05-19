package com.example.aplikasimoviecatalouge.fragmentadapter;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.aplikasimoviecatalouge.R;
import com.example.aplikasimoviecatalouge.search.SearchActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    SearchView searchView;
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment;
            switch (menuItem.getItemId()){
                case R.id.list_movie_bottom:
                    fragment = new FragmentContainer();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_layout,fragment).commit();
                    return true;
                case R.id.favorite_movie_bottom:
                    fragment = new FavoriteFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_layout,fragment).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation_view);
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        if (savedInstanceState==null){
            navigationView.setSelectedItemId(R.id.list_movie_bottom);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menus, menu);
        MenuItem searchItem =menu.findItem(R.id.search_edt);
        searchView =(SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("query",query);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.chg_lg:
                Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

