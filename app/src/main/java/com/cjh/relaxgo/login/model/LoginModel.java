package com.cjh.relaxgo.login.model;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cjh.relaxgo.entity.UmsMember;
import com.cjh.relaxgo.login.model.bean.LoginResult;
import com.cjh.relaxgo.util.MyOkHttp;
import com.cjh.relaxgo.util.MyURL;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginModel implements ILogin {

    String TAG = "LoginModel";

    @Override
    public void requestLoginByPsw(String email, String psw, LoginByPswListener loginListener) {
//        String url =   "http://192.168.10.100:8080/member/login";//邮箱加密码登录验证
        Log.i(TAG, "requestLoginByPsw: ");
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("authCode","null");
                hashMap.put("password",psw);
                hashMap.put("username",email);
                String s = JSON.toJSONString(hashMap);
                Log.i(TAG, "run: "+s);
                RequestBody body = FormBody.create(MediaType.parse("application/json; charset=utf-8"), s);
                Log.i(TAG, "run: "+body);
                Request request = new Request.Builder()
                        .post(body)
                        .url(MyURL.LOGIN_BY_EMAIL_PSW)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Log.i(TAG, "onFailure: 请求失败！");
                        loginListener.loginByAccountFails(false,"登录失败，请检查网络是否正常！");
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String content = response.body().string();
                        Log.i(TAG, "onResponse: 请求成功--------"+content);
                        response.code();
                        if (content != null){
                            LoginResult loginResult = JSON.parseObject(content, LoginResult.class);
                            String code = loginResult.getCode();
                            if (code.equals("200")){
                                loginListener.loginByAccountSuccess(true,loginResult);
                            }else if (code.equals("404")){
                                loginListener.loginByAccountFails(false,"验证码错误！");
                            }else if (code.equals("500")){
                                loginListener.loginByAccountFails(false,"账号不存在！");
                            }
                        }else {
                            Log.i(TAG, "onResponse: 请求信息为空！");
                        }
                    }
                });
            }
        }).start();
    }

    @Override
    public void requestLoginByAuthCode(String email, String authCode, LoginByAuthCodeListener loginListener) {
//        String url =   "http://192.168.10.100:8080/member/login";//邮箱加验证码登录验证
        Log.i(TAG, "requestLoginByAuthCode: ");
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("authCode",authCode);
                hashMap.put("password","null");
                hashMap.put("username",email);
                String s = JSON.toJSONString(hashMap);
                Log.i(TAG, "run: "+s);
                RequestBody body = FormBody.create(MediaType.parse("application/json; charset=utf-8"), s);
                Log.i(TAG, "run: "+body);
                Request request = new Request.Builder()
                        .url(MyURL.LOGIN_BY_EMAIL_CODE)
                        .post(body)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Log.i(TAG, "onFailure: "+"请求失败！");
                        loginListener.loginByEmailFails(false,"登录失败，请检查网络是否正常！");
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        Log.i(TAG, "onResponse: " + response);
                        String json = response.body().string();
                        Log.i(TAG, "requestLoginByAuthCode---onResponse: "+"请求成功"+json);

                        if (json!=null){
                            LoginResult loginResult = JSON.parseObject(json, LoginResult.class);
                            String code = loginResult.getCode();
                            Log.i(TAG, "onResponse: code:"+code+"------LoginResult:"+loginResult);
                            if (code.equals("200")){
                                loginListener.loginByEmailSuccess(true,loginResult);
                            }else if (code.equals("404")){
                                loginListener.loginByEmailFails(false,"验证码错误！");
                            }else if (code.equals("500")){
                                loginListener.loginByEmailFails(false,"账号不存在！");
                            }

                        }else {
                            Log.i(TAG, "onResponse: 获取请求信息为空！");
                        }
                    }
                });
            }
        }).start();
    }

    @Override
    public void requestRegister(String email, String psw, RegisterListener listener) {

//        String url = "http://192.168.10.103:8080/member/register";//请求注册
        Log.i(TAG, "requestRegister: ");
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("authCode","null");
                hashMap.put("password",psw);
                hashMap.put("username",email);
                String s = JSON.toJSONString(hashMap);
                Log.i(TAG, "run: "+s);
                RequestBody body = FormBody.create(MediaType.parse("application/json; charset=utf-8"), s);
                Log.i(TAG, "run: "+body);
                Request request = new Request.Builder()
                        .url(MyURL.REGISTER_ACCOUNT)
                        .post(body)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        listener.registerIsSuccess(false);
                        Log.i(TAG, "onFailure: 请求注册失败！");
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String content = response.body().string();
                        Log.i(TAG, "onResponse: 请求注册成功"+content);
                        if (content != null){
                            listener.registerIsSuccess(true);
                        }else {
                            Log.i(TAG, "onResponse: 请求注册返回结果为空");
                        }

                    }
                });
            }
        }).start();

    }

    @Override
    public void requestGetAuthCode(String email, getAuthCodeListener listener) {
        String url = MyURL.GET_EMAIL_CODE+email;//获取验证码
        Log.i(TAG, "requestGetAuthCode: " + url);

                MyOkHttp.getMyOkHttp().doGet(url, new MyOkHttp.NetworkCallback() {
                    @Override
                    public void onFailure(int code, String message) {
                        listener.getAuthCodeFails(false);
                        Log.i(TAG, "onFailure: 请求失败！");
                    }

                    @Override
                    public void onResponse(String json) {
                        listener.getAuthCodeSuccess(true);
                        Log.i(TAG, "onResponse: result"+json);
                    }
                });
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //创建okHttpClient对象
//                OkHttpClient mOkHttpClient = new OkHttpClient();
//
//                //创建一个Request
//                final Request request = new Request.Builder()
//                        .url(url)
//                        .build();
//                Log.i(TAG, "run: request");
//                Call call = mOkHttpClient.newCall(request);
//                call.enqueue(new Callback() {
//                    @Override
//                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                        Log.i(TAG, "onFailure: 请求失败！");
//                        listener.getAuthCodeFails(false);
//                    }
//
//                    @Override
//                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                        Log.i(TAG, "onResponse: 请求成功!"+response);
//                        String json = response.body().string();
//                        Log.i(TAG, "onResponse: result"+json);
//                        listener.getAuthCodeSuccess(true);
//
//                    }
//                });
//
//            }
//        }).start();
    }
}
