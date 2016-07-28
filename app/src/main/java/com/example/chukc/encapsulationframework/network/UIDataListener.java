package com.example.chukc.encapsulationframework.network;

/**
 * Created by chukc on 2016/7/28.
 */
public interface UIDataListener<T> {
    public void onDataChanged(T data);
    public void onErrorHappened(String errorCode, String errorMessage);
}
