package com.example.chukc.encapsulationframework.network;

import android.annotation.SuppressLint;


import java.util.ArrayList;
import java.util.List;

/**
 * Application类
 *
 * @author lizy
 */
@SuppressLint("NewApi")
public class AntsApplication extends BaseApplication {
    private static AntsApplication mInstance = null;

    public static AntsApplication getInstance() {
        return mInstance;
    }



    /*检测首页是否检测过要更新，在welcome初始化false*/
    public static boolean isMainCheckUp = false;



    /**
     * 存储传递list，注意用完之后remove
     */
    public static List<String> mySelectlist = new ArrayList<String>();


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

}
