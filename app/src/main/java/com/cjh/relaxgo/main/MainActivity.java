package com.cjh.relaxgo.main;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import androidx.fragment.app.FragmentTransaction;

import com.cjh.relaxgo.R;
import com.cjh.relaxgo.base.BaseActivity;
import com.cjh.relaxgo.base.BaseTool;
import com.cjh.relaxgo.cart.CartFragment;
import com.cjh.relaxgo.classify.ClassifyFragment;
import com.cjh.relaxgo.home.HomeFragment;
import com.cjh.relaxgo.personal.PersonalFragment;
import com.gyf.immersionbar.ImmersionBar;


public class MainActivity extends BaseActivity {

    private FrameLayout mainFrameLayout;
    private RadioGroup mainRadioGroup;

    private HomeFragment homeFragment;
    private CartFragment cartFragment;
    private ClassifyFragment classifyFragment;
    private PersonalFragment personalFragment;
    private BaseTool tool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_main, null, false);
        setContentView(view);
        tool = new BaseTool(this,getClass().getSimpleName());
        initView();
        initData();

    }


    protected void initData() {

    }


    protected void initView() {
        mainFrameLayout = findViewById(R.id.main_frameLayout);
        mainRadioGroup = findViewById(R.id.main_radioGroup);
        mainRadioGroup.setOnCheckedChangeListener(changeListener);
        mainRadioGroup.check(R.id.rbtn_home);
    }


    private RadioGroup.OnCheckedChangeListener changeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            hideAllFragment(transaction);

            switch (i){
                case R.id.rbtn_home:
                    if (homeFragment == null){
                        tool.setLog("homeFragment");
                        homeFragment = new HomeFragment();
                        transaction.add(R.id.main_frameLayout,homeFragment);
                    }else {
                        transaction.show(homeFragment);
                    }
                    break;
                case R.id.rbtn_classify:
                    if (classifyFragment == null){
                        tool.setLog("classifyFragment");
                        classifyFragment = new ClassifyFragment();
                        transaction.add(R.id.main_frameLayout,classifyFragment);
                    }else {
                        transaction.show(classifyFragment);
                    }
                    break;
                case R.id.rbtn_cart:
                    if (cartFragment == null){
                        tool.setLog("cartFragment");
                        cartFragment = new CartFragment();
                        transaction.add(R.id.main_frameLayout,cartFragment);
                    }else {
                        transaction.show(cartFragment);
                    }
                    break;
                case R.id.rbtn_me:
                    if (personalFragment == null){
                        tool.setLog("personalFragment");
                        personalFragment = new PersonalFragment();
                        transaction.add(R.id.main_frameLayout,personalFragment);
                    }else {
                        transaction.show(personalFragment);
                    }
                    break;
            }
            transaction.commit();

        }
    };


    private void hideAllFragment(FragmentTransaction transaction){

        if (homeFragment != null){
            transaction.hide(homeFragment);
        }
        if (classifyFragment != null){
            transaction.hide(classifyFragment);
        }
        if (cartFragment != null){
            transaction.hide(cartFragment);
        }
        if (personalFragment != null){
            transaction.hide(personalFragment);
        }

    }

}
