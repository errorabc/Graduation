package com.example.demo.Graduation.entity;

public class Result<T> {
    private int code;  //状态码   1是成功   0是失败
    private String content;//内容
    private T data;

    public Result() {
    }

    public Result(int code, String content) {
        this.code = code;
        this.content = content;
    }

    public Result(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public Result(int code, String content, T data) {
        this.code = code;
        this.content = content;
        this.data = data;
    }

    public static Result success(int code, String content) {
        return new Result(code, content);
    }

    public static Result error(int code, String content) {
        return new Result(code, content);
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
