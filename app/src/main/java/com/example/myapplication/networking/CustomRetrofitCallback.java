package com.example.myapplication.networking;

import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CustomRetrofitCallback<T> implements Callback<T> {

    private CustomRetrofitCallbackInterface<T> customRetrofitCallbackInterface;

    public CustomRetrofitCallback(CustomRetrofitCallbackInterface<T> customRetrofitCallbackInterface) {
        this.customRetrofitCallbackInterface = customRetrofitCallbackInterface;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.code() >= 400 && response.code() < 599)
            customRetrofitCallbackInterface.onError(new FailureException(response.code(), response.message(), response));
        else
            customRetrofitCallbackInterface.onSuccess(response.body());
    }

    @Override
    public void onFailure(final Call<T> call, Throwable t) {
        if (t instanceof UnknownHostException) {
            customRetrofitCallbackInterface.onError(t);
        } else {
            customRetrofitCallbackInterface.onError(t);
        }
    }
}
