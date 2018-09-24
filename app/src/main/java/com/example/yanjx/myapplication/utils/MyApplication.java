package com.example.yanjx.myapplication.utils;

import android.app.Application;
import android.util.Log;

import org.xutils.BuildConfig;
import org.xutils.x;

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("test","MyApplication onCrate........");
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
