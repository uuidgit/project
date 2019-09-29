package com.qq.wukong.demo.utils;

/**
 * @Auther: 天雪
 * @Date: 2019/9/27 18:52
 * @Description: restful请求响应结果对象信息
 */
public class ResultVo<T> {
    // code码
    private int code;
    // 错误信息
    private String message;
    // 相应结果数据
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
