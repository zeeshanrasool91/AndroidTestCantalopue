package com.example.myapplication.base;


import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.myapplication.MyApplication;
import com.example.myapplication.utils.ConnectivityReceiver;

import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;


public abstract class BaseActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {

    protected boolean isNetworkAvailable;
    protected Activity currentActivityContext;
    protected IntentFilter intentFilter;
    protected ConnectivityReceiver receiver = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkConnection();
        currentActivityContext = this;
        intentFilter = new IntentFilter();
        intentFilter.addAction(CONNECTIVITY_ACTION);
        receiver = new ConnectivityReceiver();
    }



    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }



    public boolean checkConnection() {
        return isNetworkAvailable = ConnectivityReceiver.isConnected();
    }


    public boolean checkInternet() {
        return ConnectivityReceiver.isConnected() && ConnectivityReceiver.isInternetOn();
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(receiver, intentFilter);
        MyApplication.getInstance().setConnectivityListener(this);
    }


    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        isNetworkAvailable = isConnected;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
