package com.cjh.relaxgo.util;

public class MyURL {

    private static String address = "192.168.10.103:8080";

    public static final String LOGIN_BY_EMAIL_CODE = "http://"+address+"/member/login";//邮箱加验证码登录验证
    public static String LOGIN_BY_EMAIL_PSW = "http://"+address+"/member/login";//邮箱加密码登录验证
    public static final String REGISTER_ACCOUNT = "http://"+address+"/member/register";//注册账号
    public static final String GET_EMAIL_CODE = "http://"+address+"/member/authCode?email=";//获取邮箱验证码

} 
