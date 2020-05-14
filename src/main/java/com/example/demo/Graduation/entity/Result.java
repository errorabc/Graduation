package com.example.demo.Graduation.entity;

public class Result<T> {
    private int code;  //状态码   1是成功   0是失败
    private String content;//内容
    private T data;

    public Result() {
    }

    //状态+内容
    public Result(int code, String content) {
        this.code = code;
        this.content = content;
    }

    //状态+数据
    public Result(int code, T data) {
        this.code = code;
        this.data = data;
    }

    //状态+内容+数据
    public Result(int code, String content, T data) {
        this.code = code;
        this.content = content;
        this.data = data;
    }

    //成功
    public static Result success(int code, String content) {
        return new Result(code, content);
    }

    //失败
    public static Result error(int code, String content) {
        return new Result(code, content);
    }

    public static <T> Result<T> listsuccess(int code, String content, T data) {
        return new Result(code, content, data);
    }

    public static <T> Result<T> listerror(int code, String content, T data) {
        return new Result(code, content, data);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
