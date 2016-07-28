package com.example.chukc.encapsulationframework.network.overfloor;

import org.json.JSONObject;

/**
 * Created by chukc on 2016/7/28.
 */
public interface HttpRequestWrapper {
    public void  postRequest(String url, JSONObject params);
}
