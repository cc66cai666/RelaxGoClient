package com.cjh.relaxgo.login.fragment;

import android.os.Build;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;

import com.cjh.relaxgo.R;
import com.cjh.relaxgo.base.BaseFragment;
import com.cjh.relaxgo.login.presenter.LoginPresenter;
import com.cjh.relaxgo.login.presenter.RegisterPresenter;
import com.cjh.relaxgo.login.view.IRegisterView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class PhoneVerificationFragment extends BaseFragment implements View.OnClickListener, IRegisterView {
    private ImageView ivReturn;
    private CheckBox checkbox;
    private Button btnNext,btnGetCode;
    private EditText et_inputPhone,et_inputVerificationCode;
    private LinearLayout ll_phoneNumber;

    private int time;
    private TimerTask task;

    private RegisterPresenter presenter;

    @Override
    protected int initView() {
        return R.layout.fragment_phone_verification;
    }

    @Override
    protected void initFindViewById(View view) {

        presenter = new RegisterPresenter(this);

        ivReturn = (ImageView) view.findViewById(R.id.iv_return);
        checkbox = (CheckBox) view.findViewById(R.id.checkbox);
        btnNext = (Button) view.findViewById(R.id.btn_next);
        btnGetCode = view.findViewById(R.id.btn_getCode);
        et_inputPhone = view.findViewById(R.id.et_inputPhone);
        et_inputVerificationCode = view.findViewById(R.id.et_inputVerificationCode);
        ll_phoneNumber = view.findViewById(R.id.ll_phoneNumber);
        ll_phoneNumber.setVisibility(View.VISIBLE);

        ivReturn.setOnClickListener(this);
        btnGetCode.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        checkbox.setOnCheckedChangeListener(changeListener);
        btnNext.setEnabled(false);
        btnGetCode.setEnabled(false);
        et_inputVerificationCode.addTextChangedListener(VerificationCodeTextWatcher);
        et_inputPhone.addTextChangedListener(PhoneTextWatcher);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btn_getCode:
                String emile = et_inputPhone.getText().toString().trim();
                if (isEmail(emile)){
                    presenter.requestGetAuthCode(emile);
                    toCountDown();
                }else {
                    Toast.makeText(getContext(),R.string.login_phone_error,Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.iv_return:
                getActivity().finish();
                break;

            case R.id.btn_next:
                check();
                break;

        }

    }

    /**
     * 点击下一步之前要做的检查
     */
    private void check() {
        openWriteUserInfoFragment();
    }

    /**
     * 打开填写密码页面
     */
    private void openWriteUserInfoFragment() {
        String email = et_inputPhone.getText().toString();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        WriteUserInfoFragment fragment = new WriteUserInfoFragment();
        fragment.setEmail(email);
        fragmentTransaction.replace(R.id.register_frameLayout,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 检查验证码和是否勾选协议
     */
    private void examineCodeAndAgreement(){
        if (!isEmail(et_inputPhone.getText().toString()) || et_inputVerificationCode.length() != 6 || !checkbox.isChecked()){
            btnNext.setEnabled(false);
            btnNext.setBackgroundResource(R.drawable.button_bg_false);
            return;
        }

        btnNext.setEnabled(true);
        btnNext.setBackgroundResource(R.drawable.button_bg_true);

    }

    private CompoundButton.OnCheckedChangeListener changeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            examineCodeAndAgreement();
        }
    };


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
     * 手机编辑框内容变化处理
     */
    private TextWatcher PhoneTextWatcher = new TextWatcher() {
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
                    btnGetCode.setTextColor(getActivity().getColor(R.color.theme_blank));
                }
                examineCodeAndAgreement();
            }else {
                btnGetCode.setEnabled(false);
                btnGetCode.setBackgroundResource(R.drawable.button_verification_code_false);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    btnGetCode.setTextColor(getActivity().getColor(R.color.theme_gray));
                }
                examineCodeAndAgreement();
            }

        }
    };


    /**
     * 获取验证码倒计时
     */
    private void toCountDown() {
        btnGetCode.setEnabled(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            btnGetCode.setTextColor(getActivity().getColor(R.color.theme_gray));
        }
        btnGetCode.setBackgroundResource(R.drawable.button_verification_code_false);
        Timer timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
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
    public void onDestroy() {
        super.onDestroy();
        if (task!=null){
            task.cancel();
        }
    }

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

    @Override
    public void getAuthCode(boolean isSuccess) {
        Toast.makeText(getActivity(),"获取验证码"+isSuccess,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registerResult(boolean isSuccess) {
        Toast.makeText(getActivity(),"注册"+isSuccess,Toast.LENGTH_SHORT).show();
    }
}
