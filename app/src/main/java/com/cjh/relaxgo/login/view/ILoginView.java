package com.cjh.relaxgo.login.view;

import com.cjh.relaxgo.entity.UmsMember;
import com.cjh.relaxgo.login.model.bean.LoginResult;

public interface ILoginView {

    //登录返回结果
    void LoginSuccess(boolean success, LoginResult user);
    void LoginFails(boolean fails,String tip);

    //获取验证码返回结果
    void getAuthCode(boolean isSuccess);



}
