package com.cjh.relaxgo.home.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class MyFragmentStateAdapter extends FragmentStateAdapter {

    private List<Fragment> fragments;

    public MyFragmentStateAdapter(List<Fragment> fragment) {
        super((Fragment) fragment);
        this.fragments = fragment;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = fragments.get(position);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
