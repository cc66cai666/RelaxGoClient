package com.cjh.relaxgo.login.presenter;

import android.os.Handler;

import com.cjh.relaxgo.login.model.ILogin;
import com.cjh.relaxgo.login.model.LoginModel;
import com.cjh.relaxgo.login.view.IRegisterView;

public class RegisterPresenter {

    private IRegisterView registerView;
    private Handler handler = new Handler();
    private LoginModel loginModel;

    public RegisterPresenter(IRegisterView registerView) {
        this.registerView = registerView;
        loginModel = new LoginModel();
    }


    public void requestRegister(String email,String psw){

        loginModel.requestRegister(email, psw, new ILogin.RegisterListener() {
            @Override
            public void registerIsSuccess(boolean isSuccess) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        registerView.registerResult(isSuccess);
                    }
                },1000);
            }
        });

    }


    /**
     * 请求获取验证码
     * @param email
     */
    public void requestGetAuthCode(String email){
        loginModel.requestGetAuthCode(email, new ILogin.getAuthCodeListener() {
            @Override
            public void getAuthCodeSuccess(boolean success) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        registerView.getAuthCode(success);
                    }
                },2000);
            }

            @Override
            public void getAuthCodeFails(boolean fails) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        registerView.getAuthCode(fails);
                    }
                },2000);
            }
        });
    }


}
