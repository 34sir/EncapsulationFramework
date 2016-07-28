package com.example.chukc.encapsulationframework.network.overfloor;

/**
 * Created by chukc on 2016/7/28.
 */
public class HttpRequest<T> {
    private static HttpRequestWrapper sInstance;

    public  HttpRequestWrapper getRequest(NetworkHelper<T> helper){
        if (sInstance == null) {
            synchronized (HttpRequest.class) {
                if (sInstance == null) {
                    sInstance = helper;
                }
            }
        }
        return sInstance;
    }
}
