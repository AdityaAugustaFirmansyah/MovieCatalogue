package com.example.aplikasimoviecatalouge.fragmentadapter;


import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.aplikasimoviecatalouge.R;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {



    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout_favorite);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.movie));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getText(R.string.tv_show)));
        final ViewPager viewPager = view.findViewById(R.id.view_pager_favorite);
        FavoriteFragmentAdapter favoriteFragmentAdapter = new FavoriteFragmentAdapter(getChildFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(favoriteFragmentAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Objects.requireNonNull(getActivity()).getMenuInflater().inflate(R.menu.menus, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.chg_lg:
                Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
