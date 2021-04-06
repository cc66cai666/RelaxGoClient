package com.cjh.relaxgo.login.model;

import com.cjh.relaxgo.entity.UmsMember;
import com.cjh.relaxgo.login.model.bean.LoginResult;
import com.cjh.relaxgo.login.model.bean.User;

public interface ILogin {

    /**
     * 通过邮箱加密码进行登录
     * @param email
     * @param psw
     * @param loginListener
     */
    void requestLoginByPsw(String email,String psw, LoginByPswListener loginListener);

    /**
     * 通过邮箱加验证码登录
     * @param email
     * @param authCode
     * @param loginListener
     */
    void requestLoginByAuthCode(String email,String authCode,LoginByAuthCodeListener loginListener);

    /**
     * 注册
     * @param email
     * @param psw
     * @param listener
     */
    void requestRegister(String email,String psw,RegisterListener listener);

    /**
     * 获取验证码
     * @param email
     * @param listener
     */
    void requestGetAuthCode(String email,getAuthCodeListener listener);

    interface getAuthCodeListener{
        void getAuthCodeSuccess(boolean success);
        void getAuthCodeFails(boolean fails);
    }

    interface LoginByPswListener{
        void loginByAccountSuccess(boolean success, LoginResult result);
        void loginByAccountFails(boolean fails,String tip);
    }

    interface LoginByAuthCodeListener{
        void loginByEmailSuccess(boolean success, LoginResult result);
        void loginByEmailFails(boolean fails,String tip);
    }

    interface RegisterListener{
        void registerIsSuccess(boolean isSuccess);
    }
}
