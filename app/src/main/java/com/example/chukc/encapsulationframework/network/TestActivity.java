package com.example.chukc.encapsulationframework.network;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.chukc.encapsulationframework.network.overfloor.HttpRequest;
import com.example.chukc.encapsulationframework.network.overfloor.NetworkHelper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by chukc on 2016/7/28.
 */
public class TestActivity extends Activity {
    private NetworkHelper<BackResult> networkHelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Regist1();
    }

    private void  Regist1(){
        JSONObject params=new JSONObject();
        networkHelper = new ReverseRegisterNetworkHelper(this);
        networkHelper.setUiDataListener(new UIDataListener<BackResult>(){
            @Override
            public void onDataChanged(BackResult data) {
                Toast.makeText(TestActivity.this,data.getMsg(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onErrorHappened(String errorCode, String errorMessage) {
                Toast.makeText(TestActivity.this,errorMessage,Toast.LENGTH_LONG).show();
            }
        });
        try {
            params.put("mobile","15371150270");
            params.put("isAutoReg",true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new HttpRequest().getRequest(networkHelper).postRequest("www.baidu.com", params);
    }

    private void  Regit(){
        networkHelper = new ReverseRegisterNetworkHelper(this);
        networkHelper.setUiDataListener(new UIDataListener<BackResult>(){
            @Override
            public void onDataChanged(BackResult data) {
                Toast.makeText(TestActivity.this,data.getMsg(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onErrorHappened(String errorCode, String errorMessage) {
                Toast.makeText(TestActivity.this,errorMessage,Toast.LENGTH_LONG).show();
            }
        });
        JSONObject params=new JSONObject();
        try {
            params.put("mobile","15371150270");
            params.put("isAutoReg",true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        networkHelper.sendPostRequest("www.baidu.com", params);
    }
}
