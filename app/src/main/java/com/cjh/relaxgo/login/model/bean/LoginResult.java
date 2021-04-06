package com.cjh.relaxgo.login.model.bean;

import com.cjh.relaxgo.entity.UmsMember;

public class LoginResult {

    private String code;//请求返回码
    private String message;//请求返回描述
    private UmsMember data;//请求返回的数据

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UmsMember getData() {
        return data;
    }

    public void setData(UmsMember data) {
        this.data = data;
    }
}
