package com.example.demo.controller;

import lombok.Data;

/**
 * 统一接口响应结果
 */
@Data
public class Result<T> {
    private int code; // 响应码：200-成功，500-失败
    private String msg; // 响应消息
    private T data; // 响应数据

    public Result(int i, String 操作成功, Object o) {
    }

    // 成功响应（无数据）
    public static <T> Result<T> success() {
        return new Result<>(200, "操作成功", null);
    }

    // 成功响应（有数据）
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data);
    }

    // 成功响应（自定义消息+数据）
    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(200, msg, data);
    }

    // 失败响应（自定义消息）
    public static <T> Result<T> error(String msg) {
        return new Result<>(500, msg, null);
    }

    // 失败响应（自定义响应码+消息）
    public static <T> Result<T> error(int code, String msg) {
        return new Result<>(code, msg, null);
    }
}