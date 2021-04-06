package com.cjh.relaxgo.classify;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cjh.relaxgo.R;
import com.cjh.relaxgo.base.BaseFragment;

public class ClassifyFragment extends BaseFragment {

    @Override
    protected int initView() {
        return R.layout.classify_fragment;
    }

    @Override
    protected void initFindViewById(View view) {
        view.setFitsSystemWindows(true);
    }
}
