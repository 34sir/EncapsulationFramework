package com.example.chukc.encapsulationframework.network.overfloor;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.chukc.encapsulationframework.network.AntsApplication;
import com.example.chukc.encapsulationframework.network.NetworkRequest;
import com.example.chukc.encapsulationframework.network.UIDataListener;

import org.json.JSONObject;

/**
 * Created by chukc on 2016/7/28.
 */
public abstract class NetworkHelper<T> implements Response.Listener<JSONObject>, Response.ErrorListener,HttpRequestWrapper {
    private Context context;

    public NetworkHelper(Context context)
    {
        this.context = context;
    }

    protected Context getContext()
    {
        return context;
    }

    protected NetworkRequest getRequestForPost(String url, JSONObject params)
    {
        return new NetworkRequest(Request.Method.POST, url, params, this, this);
    }
    public void sendPostRequest(String url,  JSONObject params)
    {
        AntsApplication.getInstance().
                getRequestQueue().add(getRequestForPost(url, params),context);
    }

    @Override
    public void postRequest(String url, JSONObject params) {
        sendPostRequest(url,params);
    }

    @Override
    public void onErrorResponse(VolleyError volleyError, Object o) {
        disposeVolleyError(volleyError);
    }
    protected abstract void disposeVolleyError(VolleyError error);

    @Override
    public void onResponse(JSONObject response)
    {
        Log.d("Amuro", response.toString());
        disposeResponse(response);
    }
    protected abstract void disposeResponse(JSONObject response);

    private UIDataListener<T> uiDataListener;

    public void setUiDataListener(UIDataListener<T> uiDataListener)
    {
        this.uiDataListener = uiDataListener;
    }

    protected void notifyDataChanged(T data)
    {
        if(uiDataListener != null)
        {
            uiDataListener.onDataChanged(data);
        }
    }
    protected void notifyErrorHappened(String errorCode, String errorMessage)
    {
        if(uiDataListener != null)
        {
            uiDataListener.onErrorHappened(errorCode, errorMessage);
        }
    }
}
