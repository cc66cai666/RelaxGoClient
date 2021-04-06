package com.cjh.relaxgo.cart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjh.relaxgo.R;
import com.cjh.relaxgo.base.BaseFragment;

public class CartFragment extends BaseFragment {

    @Override
    protected int initView() {
        return R.layout.fragment_cart;
    }

    @Override
    protected void initFindViewById(View view) {
        view.setFitsSystemWindows(true);
    }
}
