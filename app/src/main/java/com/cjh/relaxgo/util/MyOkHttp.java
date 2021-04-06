package com.cjh.relaxgo.util;


import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyOkHttp  {

    private OkHttpClient client;
    private static MyOkHttp myOkHttp;

    public MyOkHttp() {
        client = initOkHttpClent();
    }

    /**
     * 获取单例对象
     * @return
     */
    public static MyOkHttp getMyOkHttp(){
        if (myOkHttp == null){
            myOkHttp = new MyOkHttp();
        }
        return myOkHttp;
    }

    /**
     * 初始化OkHttp对象
     * @return
     */
    private OkHttpClient initOkHttpClent() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .connectTimeout(10000,TimeUnit.MILLISECONDS)
                .build();
        return client;

    }

    /**
     * get方式网络请求
     * @param url
     * @param callback
     */
    public void doGet(String url, final NetworkCallback callback){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url(url)
                        .get()
                        .build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        callback.onFailure(-1,e.getMessage());
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        callback.onResponse(response.body().string());
                    }
                });

            }
        }).start();

    }

    /**
     * post网络请求
     * @param url
     * @param requestBody
     * @param callback
     */
    public void doPost(String url, RequestBody requestBody,final NetworkCallback callback){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url(url)
                        .post(requestBody)
                        .build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        callback.onFailure(-1,e.getMessage());
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        callback.onResponse(response.body().string());
                    }
                });
            }
        }).start();

    }

    public interface NetworkCallback{
        /**
         * 请求失败返回的错误码以及错误消息
         * @param code
         * @param message
         */
        void onFailure(int code,String message);

        /**
         * 请求成功，并返回数据
         * @param json
         */
        void onResponse(String json);

    }
} 
