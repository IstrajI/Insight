package com.example.insite.ui.all.adapters;

import androidx.core.app.Fragment;
import androidx.core.app.FragmentManager;
import androidx.core.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class GamepagesAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> fragmentList;
    private final List<String> titleList;

    public GamepagesAdapter(final FragmentManager manager) {
        super(manager);
        fragmentList = new ArrayList<>();
        titleList =  new ArrayList<>();
    }

    @Override
    public Fragment getItem(final int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragment(final Fragment fragment, final String title) {
        fragmentList.add(fragment);
        titleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(final int position) {
        return titleList.get(position);
    }
}
