package com.example.aplikasimoviecatalouge.fragmentadapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.aplikasimoviecatalouge.tvshow.TvShowFragment;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    private int fragment;

    FragmentAdapter(FragmentManager fm, int fragment) {
        super(fm);
        this.fragment = fragment;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new MovieFragment();
            case 1:
                return new TvShowFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return fragment;
    }
}
