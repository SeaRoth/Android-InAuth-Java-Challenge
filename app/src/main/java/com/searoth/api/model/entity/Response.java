package com.searoth.api.model.entity;

public class Response<T> {
    private String Message;
    private T Data;

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        this.Data = data;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
