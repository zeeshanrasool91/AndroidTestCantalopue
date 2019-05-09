package com.example.myapplication;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.example.myapplication.utils.ConnectivityReceiver;




public class MyApplication extends Application {

    private static MyApplication mInstance;



    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }




    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}

