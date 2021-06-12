package com.example.im.listener;

public interface HttpCallbackListener {

    void onSuccess(String response);

    void onFailure(Exception e);
}
