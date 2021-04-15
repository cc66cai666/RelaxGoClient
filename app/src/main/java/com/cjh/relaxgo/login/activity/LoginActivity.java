package com.cjh.relaxgo.login.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cjh.relaxgo.R;
import com.cjh.relaxgo.base.BaseActivity;
import com.cjh.relaxgo.entity.UmsMember;
import com.cjh.relaxgo.login.model.bean.LoginResult;
import com.cjh.relaxgo.login.presenter.LoginPresenter;
import com.cjh.relaxgo.login.view.ILoginView;
import com.cjh.relaxgo.util.AccountSPUtil;

import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class LoginActivity extends BaseActivity implements ILoginView,View.OnClickListener {

    private String TAG = "LoginActivity";
    private ImageView ivClose;
    private CheckBox checkbox;
    private Button btnLogin;
    private TextView tvLoginType;
    private TextView tvRegister;

    private LoginPresenter loginPresenter;

    //账号密码登录
    private LinearLayout llPhone_number;
    private TextView tvForgetPsw;
    private ImageView hideAndShow;
    private EditText etUserName,etPassword;
    private LinearLayout llAccount_psw;

    //手机号登录
    private EditText etPhoneNumber,et_VerificationCode;
    private Button btnGetCode;

    private int login_status = 1;//账号密码登录为“2”，邮箱登录为“1”
    private boolean isShowOrHide = false; //“false”表示不显示密码，“true”显示密码

    private int time;
    TimerTask task;

    private String registerEamil;
    private String registerPsw;

    private static final String SP_USER_NAME = "userName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent = getIntent();
        registerEamil = intent.getStringExtra("registerEamil");
        registerPsw = intent.getStringExtra("registerPsw");

        initView();
    }

    private void initView() {
        loginPresenter = new LoginPresenter(this);

        llPhone_number = findViewById(R.id.ll_phoneNumber);
        llAccount_psw = findViewById(R.id.ll_accountPsw);
        ivClose = (ImageView) findViewById(R.id.iv_close);
        ivClose.setOnClickListener(this);
        checkbox = (CheckBox) findViewById(R.id.checkbox);
        checkbox.setOnCheckedChangeListener(changeListener);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        tvLoginType = (TextView) findViewById(R.id.tv_login_type);
        tvLoginType.setOnClickListener(this);
        tvRegister = (TextView) findViewById(R.id.tv_register);
        tvRegister.setOnClickListener(this);
        etUserName = findViewById(R.id.et_inputUserName);
        etPassword = findViewById(R.id.et_inputPassword);
        etPassword.addTextChangedListener(phoneTextWatcherPsw);
        etPhoneNumber = findViewById(R.id.et_inputPhone);
        etPhoneNumber.addTextChangedListener(phoneTextWatcher);
        tvForgetPsw = findViewById(R.id.tv_forgetPsw);
        tvForgetPsw.setOnClickListener(this);
        hideAndShow = findViewById(R.id.iv_hideAndShow);
        hideAndShow.setOnClickListener(this);
        et_VerificationCode = findViewById(R.id.et_inputVerificationCode);
        et_VerificationCode.addTextChangedListener(VerificationCodeTextWatcher);
        btnGetCode = findViewById(R.id.btn_getCode);
        btnGetCode.setOnClickListener(this);

        loginType(login_status);
        btnGetCode.setEnabled(false);
        checkbox.setChecked(false);
        btnLogin.setEnabled(false);

        if (registerEamil != null && registerPsw != null){
            loginType(2);
            etUserName.setText(registerEamil);
            etPassword.setText(registerPsw);
            btnLogin.setEnabled(true);
        }

    }

    /**
     * 登录类型布局
     * @param login_status
     */
    private void loginType(int login_status) {

        if (login_status == 1){
            llAccount_psw.setVisibility(View.GONE);
            llPhone_number.setVisibility(View.VISIBLE);
        }else if (login_status == 2){
            llPhone_number.setVisibility(View.GONE);
            llAccount_psw.setVisibility(View.VISIBLE);
        }

    }

    /**
     * 是否显示密码
     * @param showOrHidePsw
     */
    private void setShowOrHidePsw(boolean showOrHidePsw){

        int start = etPassword.getSelectionStart();
        if (showOrHidePsw){
            setLog("setShowOrHidePsw: 隐藏");
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }else {
            setLog("setShowOrHidePsw: 显示");
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        etPassword.setSelection(start);

    }


    @Override
    public void LoginSuccess(boolean success, LoginResult user) {
        setLog("LoginSuccess: ---------"+user);
        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
        String username = user.getData().getUsername();
        Log.i(TAG, "LoginSuccess: 登录者："+username);
        AccountSPUtil.setString(this,SP_USER_NAME,username);
        this.finish();
    }

    @Override
    public void LoginFails(boolean fails,String tip) {
        setLog("LoginFails: ");
        Toast.makeText(LoginActivity.this, tip, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getAuthCode(boolean isSuccess) {
        setLog("getAuthCode: ");
        Toast.makeText(LoginActivity.this, "获取结果为："+isSuccess, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

                //登录请求处理
            case R.id.btn_login:
                if (btnLogin.isEnabled()){
                    Log.i(TAG, "onClick: "+login_status);
                    if (login_status == 1){
                        String email = etPhoneNumber.getText().toString().trim();
                        String code = et_VerificationCode.getText().toString().trim();
                        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(code)){
                            setToast(getString(R.string.login_email_code_null));
                        }else{
                            loginPresenter.requestLoginByCode(email,code);
                        }
                    }else if (login_status == 2){
                        String userName = etUserName.getText().toString().trim();
                        String password = etPassword.getText().toString().trim();
                        if (TextUtils.isEmpty(userName) && TextUtils.isEmpty(password)) {
                            setToast(getString(R.string.login_account_psw_null));
                        } else {
                            loginPresenter.requestLoginByPsw(userName,password);
                        }
                    }
                }
                break;

                //改变登录方式
            case R.id.tv_login_type:
                if (login_status == 1){
                    tvLoginType.setText(R.string.login_text_phone);
                    loginType(2);
                    login_status = 2;
                }else if (login_status == 2){
                    tvLoginType.setText(R.string.login_text_account);
                    loginType(1);
                    login_status = 1;
                }
                break;

                //注册
            case R.id.tv_register:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;

                //忘记密码
            case R.id.tv_forgetPsw:
                setToast("忘记密码");
                break;

                //是否显示密码
            case R.id.iv_hideAndShow:
                if (isShowOrHide){
                    setShowOrHidePsw(false);
                    hideAndShow.setImageResource(R.mipmap.icon_login_hide);
                    isShowOrHide = false;
                }else {
                    setShowOrHidePsw(true);
                    hideAndShow.setImageResource(R.mipmap.icon_login_show);
                    isShowOrHide = true;
                }
                break;

                //关闭页面
            case R.id.iv_close:
                finish();
                break;

                //获取验证码
            case R.id.btn_getCode:
                String emile = etPhoneNumber.getText().toString().trim();
                if (isEmail(emile)){
                    loginPresenter.requestGetAuthCode(emile);
                    toCountDown();
                }else {
                    setToast(getString(R.string.login_phone_error));
                }
                break;

        }

    }

    /**
     * 获取验证码倒计时
     */
    private void toCountDown() {
        btnGetCode.setEnabled(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            btnGetCode.setTextColor(getColor(R.color.theme_gray));
        }
        btnGetCode.setBackgroundResource(R.drawable.button_verification_code_false);
        Timer timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (time <= 0){
                            btnGetCode.setText(R.string.login_regain_code);
                            btnGetCode.setEnabled(true);
                            task.cancel();
                        }else {
                            btnGetCode.setText(time + getString(R.string.login_regain_code_after));
                        }
                        time--;
                    }
                });
            }
        };
        time = 60;
        timer.schedule(task,0,1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (task != null){
            task.cancel();
        }

    }

    /**
     * 检查验证码和是否勾选协议
     */
    private void examineCodeAndAgreement(){
        if (!isEmail(etPhoneNumber.getText().toString()) || et_VerificationCode.length() != 6 || !checkbox.isChecked()){
            btnLogin.setEnabled(false);
            btnLogin.setBackgroundResource(R.drawable.button_bg_false);
            return;
        }
        btnLogin.setEnabled(true);
        btnLogin.setBackgroundResource(R.drawable.button_bg_true);
    }

    private TextWatcher phoneTextWatcherPsw = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

            String email = etUserName.getText().toString();
            if (isEmail(email)){
                btnLogin.setEnabled(true);
            }else {
                Toast.makeText(LoginActivity.this,"请输入正确的邮箱地址！",Toast.LENGTH_SHORT).show();
            }


        }
    };

    /**
     * 邮箱编辑框的监听处理
     */
    private TextWatcher phoneTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (isEmail(editable.toString())){
                btnGetCode.setEnabled(true);
                btnGetCode.setBackgroundResource(R.drawable.button_verification_code_true);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    btnGetCode.setTextColor(getColor(R.color.theme_blank));
                }
                examineCodeAndAgreement();
            }else {
                btnGetCode.setEnabled(false);
                btnGetCode.setBackgroundResource(R.drawable.button_verification_code_false);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    btnGetCode.setTextColor(getColor(R.color.theme_gray));
                }
                examineCodeAndAgreement();
            }
        }
    };

    /**
     * 检查验证变化处理
     */
    private TextWatcher VerificationCodeTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            examineCodeAndAgreement();
        }
    };

    /**
     * 是否同意隐私政策的情况下
     */
    private CompoundButton.OnCheckedChangeListener changeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            switch (compoundButton.getId()){
                case R.id.checkbox:
//                    if (b){
//                        btnLogin.setEnabled(true);
//                        btnLogin.setBackgroundResource(R.drawable.button_bg_true);
//                    }else {
//                        btnLogin.setEnabled(false);
//                        btnLogin.setBackgroundResource(R.drawable.button_bg_false);
//                    }
                    examineCodeAndAgreement();
                    break;
            }
        }
    };


    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 145,147,149
     * 15+除4的任意数(不要写^4，这样的话字母也会被认为是正确的)
     * 166
     * 17+3,5,6,7,8
     * 18+任意数
     * 198,199
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        // ^ 匹配输入字符串开始的位置
        // \d 匹配一个或多个数字，其中 \ 要转义，所以是 \\d
        // $ 匹配输入字符串结尾的位置
        String regExp = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(166)|(17[3,5,6,7,8])" +
                "|(18[0-9])|(19[8,9]))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 邮箱验证
     * @param strEmail
     * @return
     */
    public static boolean isEmail(String strEmail) {
        String strPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        if (TextUtils.isEmpty(strPattern)) {
            return false;
        } else {
            return strEmail.matches(strPattern);
        }
    }
}