package com.example.aplikasimoviecatalouge.fragmentadapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class FavoriteFragmentAdapter extends FragmentStatePagerAdapter{
    private int fragment;
    FavoriteFragmentAdapter(FragmentManager fm, int fragment) {
        super(fm);
        this.fragment = fragment;
    }


    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new FavoriteMovieFragment();
            case 1:
                return new FavoriteTvFragment();
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return fragment;
    }
}
