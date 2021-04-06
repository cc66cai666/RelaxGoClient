package com.cjh.relaxgo.login.fragment;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;

import com.cjh.relaxgo.R;
import com.cjh.relaxgo.base.BaseFragment;
import com.cjh.relaxgo.login.activity.LoginActivity;
import com.cjh.relaxgo.login.presenter.RegisterPresenter;
import com.cjh.relaxgo.login.view.IRegisterView;

public class WriteUserInfoFragment extends BaseFragment implements View.OnClickListener, IRegisterView {

    private String TAG = "WriteUserInfoFragment";

    private ImageView ivReturn,iv_hideAndShow,iv_sureHideAndShow;
    private EditText etPasswrod;
    private EditText etSurePassword;
    private Button btnRegister;

    private boolean isShowOrHide = false; //“false”表示不显示密码，“true”显示密码
    private boolean isSureShowOrHide = false; //“false”表示不显示密码，“true”显示密码
    private RegisterPresenter presenter;

    private String email = null;

    public void setEmail(String email){
        this.email = email;
    }

    @Override
    protected int initView() {
        return R.layout.fragment_write_user_info;
    }

    @Override
    protected void initFindViewById(View view) {

        presenter = new RegisterPresenter(this);

        ivReturn = (ImageView) view.findViewById(R.id.iv_return);
        etPasswrod = (EditText) view.findViewById(R.id.et_passwrod);
        etSurePassword = (EditText) view.findViewById(R.id.et_surePassword);
        btnRegister = (Button) view.findViewById(R.id.btn_register);
        iv_hideAndShow = view.findViewById(R.id.iv_hideAndShow);
        iv_sureHideAndShow = view.findViewById(R.id.iv_sureHideAndShow);

        ivReturn.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        iv_hideAndShow.setOnClickListener(this);
        iv_sureHideAndShow.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.iv_return:
                getParentFragmentManager().popBackStack();
                break;

            case R.id.btn_register:
                String psw = etPasswrod.getText().toString();
                String surePsw = etSurePassword.getText().toString();
                checkPsw(psw,surePsw);
                break;

            case R.id.iv_hideAndShow:
                if (isShowOrHide){
                    setShowOrHidePsw(false,1);
                    iv_hideAndShow.setImageResource(R.mipmap.icon_login_hide);
                    isShowOrHide = false;
                }else {
                    setShowOrHidePsw(true,1);
                    iv_hideAndShow.setImageResource(R.mipmap.icon_login_show);
                    isShowOrHide = true;
                }
                break;

            case R.id.iv_sureHideAndShow:
                if (isSureShowOrHide){
                    setShowOrHidePsw(false,2);
                    iv_sureHideAndShow.setImageResource(R.mipmap.icon_login_hide);
                    isSureShowOrHide = false;
                }else {
                    setShowOrHidePsw(true,2);
                    iv_sureHideAndShow.setImageResource(R.mipmap.icon_login_show);
                    isSureShowOrHide = true;
                }
                break;

        }
    }



    /**
     * 是否显示密码
     * @param showOrHidePsw
     */
    private void setShowOrHidePsw(boolean showOrHidePsw,int tag){

        int start = etPasswrod.getSelectionStart();
        int startSure = etSurePassword.getSelectionStart();

        if (tag == 1){
            if (showOrHidePsw){
                Log.i(TAG, "setShowOrHidePsw: 隐藏");
                etPasswrod.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else {
                Log.i(TAG, "setShowOrHidePsw: 显示");
                etPasswrod.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            etPasswrod.setSelection(start);
        }else if (tag == 2){
            if (showOrHidePsw){
                Log.i(TAG, "setShowOrHidePsw: 隐藏");
                etSurePassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else {
                Log.i(TAG, "setShowOrHidePsw: 显示");
                etSurePassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            etSurePassword.setSelection(startSure);
        }


    }

    /**
     * 检查两次输入的密码
     * @param psw
     * @param surePsw
     */
    private void checkPsw(String psw,String surePsw){
        Log.i(TAG, "checkPsw------------Email:"+email);
        if (psw.equals(surePsw)){
            if (psw.length() < 8 || psw.length() > 15){
                Toast.makeText(getActivity(),R.string.login_password_check,Toast.LENGTH_LONG).show();
            }else {
                presenter.requestRegister(email,surePsw);
            }
        }else {
            Toast.makeText(getActivity(),R.string.login_password_Inconsistent,Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void getAuthCode(boolean isSuccess) {

    }

    @Override
    public void registerResult(boolean isSuccess) {
        if (isSuccess){
            String s = etSurePassword.getText().toString();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//关掉所要到的界面中间的activity
            intent.putExtra("registerEamil",email);
            intent.putExtra("registerPsw",s);
            Log.i(TAG, "registerResult: "+isSuccess+"--------email:"+email+"-----psw:"+s.length());
            startActivity(intent);
            getActivity().finish();
        }else {
            Toast.makeText(getActivity(),"注册失败，请重新注册！",Toast.LENGTH_SHORT).show();
        }
    }
}
