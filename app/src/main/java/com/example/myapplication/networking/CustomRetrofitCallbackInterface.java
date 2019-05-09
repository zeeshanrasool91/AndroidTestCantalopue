package com.example.myapplication.networking;



public interface CustomRetrofitCallbackInterface<T> {
    void onSuccess(T response);
    void onError(Throwable throwable);
}
