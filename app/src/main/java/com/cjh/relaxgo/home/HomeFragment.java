package com.cjh.relaxgo.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.cjh.relaxgo.R;
import com.cjh.relaxgo.base.BaseFragment;
import com.cjh.relaxgo.home.fragment.ChildHomeFragment;
import com.cjh.relaxgo.home.fragment.ElectricApplianceHomeFragment;
import com.cjh.relaxgo.home.fragment.ExerciseHomeFragment;
import com.cjh.relaxgo.home.fragment.FoodHomeFragment;
import com.cjh.relaxgo.home.fragment.PhoneHomeFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;

    private List<String> titles;
    private List<Fragment> fragments;

    @Override
    protected int initView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initFindViewById(View view) {

        view.setFitsSystemWindows(true);

        tabLayout = view.findViewById(R.id.home_tabLayout);
        viewPager2 = view.findViewById(R.id.home_viewPager);

        initTitleAndFragment();

        viewPager2.setAdapter(new FragmentStateAdapter(getActivity()) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return fragments.get(position);
            }

            @Override
            public int getItemCount() {
                return fragments.size();
            }
        });
        viewPager2.setOffscreenPageLimit(5);
        new TabLayoutMediator(tabLayout, viewPager2, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titles.get(position));
            }
        }).attach();

    }

    private void initTitleAndFragment() {

        titles = new ArrayList<>();
        titles.add("首页");
        titles.add("手机");
        titles.add("运动");
        titles.add("食品");
        titles.add("家电");

        fragments = new ArrayList<>();
        fragments.add(new ChildHomeFragment());
        fragments.add(new PhoneHomeFragment());
        fragments.add(new ExerciseHomeFragment());
        fragments.add(new FoodHomeFragment());
        fragments.add(new ElectricApplianceHomeFragment());

    }


}
