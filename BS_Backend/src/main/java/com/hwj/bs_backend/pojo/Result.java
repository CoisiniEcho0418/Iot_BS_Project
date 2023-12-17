package com.hwj.bs_backend.pojo;

import lombok.Data;

/**
 * 统一响应结果封装类
 * @param <T>
 */
@Data
public class Result<T> {
    private Integer code ;//1 成功 , 0 失败
    private String msg; //提示信息
    private T data; //数据 data

    public Result() {
    }
    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(1, "success", data);
    }

    public static <T> Result<T> success() {
        return new Result<>(1, "success", null);
    }

    public static <T> Result<T> error(String msg) {
        return new Result<>(0, msg, null);
    }


    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
