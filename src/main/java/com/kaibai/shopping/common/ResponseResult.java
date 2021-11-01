package com.kaibai.shopping.common;

import java.io.Serializable;

//传给前端的响应结构
public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
    private String message;
    private long count;
    private T data;

    public ResponseResult() {
    }

    public ResponseResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseResult(String code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseResult(String code,long count, T data) {
        this.code = code;
        this.count = count;
        this.data = data;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    public ResponseResult setData(T data) {
        this.data = data;
        return this;
    }
}
