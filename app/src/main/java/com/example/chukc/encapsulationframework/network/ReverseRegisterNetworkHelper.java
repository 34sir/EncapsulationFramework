package com.example.chukc.encapsulationframework.network;

import android.content.Context;

import com.android.volley.VolleyError;
import com.example.chukc.encapsulationframework.network.overfloor.NetworkHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

/**
 * Created by chukc on 2016/7/28.
 */
public class ReverseRegisterNetworkHelper extends NetworkHelper<BackResult> {


    public ReverseRegisterNetworkHelper(Context context) {
        super(context);
    }

    @Override
    protected void disposeVolleyError(VolleyError error) {
        notifyErrorHappened(
                "",
                error == null ? "NULL" : error.getMessage());
    }

    @Override
    protected void disposeResponse(JSONObject response) {
        if (response != null) {
            try {
                BackResult BResult = new Gson().fromJson(response.toString(), new TypeToken<BackResult>() {
                }.getType());
                if (BResult.getResult() == 1)
                    notifyDataChanged(BResult);
                else
                    notifyErrorHappened("", BResult.getMsg());
            } catch (Exception e) {
                notifyErrorHappened("", "Response format error");
            }
        } else {
            notifyErrorHappened("", "Response is null!");
        }

    }


}
