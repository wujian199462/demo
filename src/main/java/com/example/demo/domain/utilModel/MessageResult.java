package com.example.demo.domain.utilModel;

import java.io.Serializable;


public class MessageResult<T> implements Serializable {
    private boolean status;
    private String message;
    private T data;

    public MessageResult() {
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
