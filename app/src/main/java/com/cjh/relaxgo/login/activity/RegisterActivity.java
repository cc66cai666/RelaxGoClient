package com.cjh.relaxgo.login.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.cjh.relaxgo.R;
import com.cjh.relaxgo.base.BaseActivity;
import com.cjh.relaxgo.login.fragment.PhoneVerificationFragment;

public class RegisterActivity extends BaseActivity {

    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        frameLayout = findViewById(R.id.register_frameLayout);

        openPhoneVerificationFragment();
    }

    private void openPhoneVerificationFragment() {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        PhoneVerificationFragment fragment = new PhoneVerificationFragment();
        fragmentTransaction.add(R.id.register_frameLayout,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}