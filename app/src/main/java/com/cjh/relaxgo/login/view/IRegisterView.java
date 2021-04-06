package com.cjh.relaxgo.login.view;

public interface IRegisterView {

    //获取验证码返回结果
    void getAuthCode(boolean isSuccess);

    void registerResult(boolean isSuccess);

}
