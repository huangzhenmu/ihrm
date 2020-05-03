package com.ihrm.common.entity;

/**
 * 返回码定义
 */
public enum  ResultCode {
    SUCCESS(true,10000,"操作成功"),
    FAIL(false,10001,"操作失败"),
    UNAUTHENTICATED(false,10002,"未登录"),
    UNAUTHORISE(false,10003,"权限不足"),
    SERVER_ERROR(false,99999,"服务器繁忙"),
    MOBILEORPASSWORDERROR(false,10004,"手机或密码错误");
    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    ResultCode(boolean success,int code,String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public boolean success(){
        return success;
    }

    public int code(){
        return code;
    }

    public String message(){
        return message;
    }
}
