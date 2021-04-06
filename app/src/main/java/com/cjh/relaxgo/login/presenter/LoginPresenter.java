package com.cjh.relaxgo.login.presenter;

import android.os.Handler;
import android.provider.ContactsContract;

import com.cjh.relaxgo.entity.UmsMember;
import com.cjh.relaxgo.login.model.ILogin;
import com.cjh.relaxgo.login.model.LoginModel;
import com.cjh.relaxgo.login.model.bean.LoginResult;
import com.cjh.relaxgo.login.view.ILoginView;
import com.cjh.relaxgo.login.view.IRegisterView;

public class LoginPresenter {

    private LoginModel loginModel;
    private ILoginView iLoginView;
    private Handler handler = new Handler();

    public LoginPresenter(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        loginModel = new LoginModel();
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
                            iLoginView.getAuthCode(success);
                    }
                },2000);
            }

            @Override
            public void getAuthCodeFails(boolean fails) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        iLoginView.getAuthCode(fails);
                    }
                },2000);
            }
        });
    }

    /**
     * 通过邮箱加密码进行登录
     * @param email
     * @param psw
     */
    public void requestLoginByPsw(String email,String psw){

        loginModel.requestLoginByPsw(email, psw, new ILogin.LoginByPswListener() {
            @Override
            public void loginByAccountSuccess(boolean success, LoginResult result) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        iLoginView.LoginSuccess(true,result);
                    }
                },1000);
            }

            @Override
            public void loginByAccountFails(boolean fails,String tip) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        iLoginView.LoginFails(false,tip);
                    }
                },1000);
            }
        });
    }

    /**
     * 通过邮箱加验证码进行登录
     * @param email
     * @param code
     */
    public void requestLoginByCode(String email,String code){
        loginModel.requestLoginByAuthCode(email, code, new ILogin.LoginByAuthCodeListener() {
            @Override
            public void loginByEmailSuccess(boolean success, LoginResult result) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        iLoginView.LoginSuccess(success,result);
                    }
                },1000);
            }

            @Override
            public void loginByEmailFails(boolean fails,String tip) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        iLoginView.LoginFails(fails,tip);
                    }
                },1000);
            }
        });
    }


//    /**
//     * 向LoginModel发送登录请求
//     * @param user
//     */
//    public void requestLogin(User user){
//        loginModel.request(user, new ILogin.LoginListener() {
//            @Override
//            public void loginSuccess(String success) {
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        iLoginView.LoginSuccess(success);
//                    }
//                },2000);
//            }
//
//            @Override
//            public void loginFails(String fails) {
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        iLoginView.LoginFails(fails);
//                    }
//                },2000);
//            }
//        });
//
//    }

}
