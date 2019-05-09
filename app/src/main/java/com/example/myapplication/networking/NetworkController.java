package com.example.myapplication.networking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NetworkController {

    private static NetworkController singleTonInstance = null;
    private Retrofit mRetrofit;
    private ApiMethods mApiMethods;

    private NetworkController() {
    }

    public static NetworkController getInstance() {
        if (singleTonInstance == null) {
            singleTonInstance = new NetworkController();
        }
        return singleTonInstance;
    }

    public Retrofit getRetrofit() {
        if (mRetrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(AppConstants.HTTP.BASE_API)
                    .client(getHttpClient())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return mRetrofit;
    }

    //Time out Issue to be Resolved
    private OkHttpClient getHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .build();
    }


    public ApiMethods getApiMethods() {
        if (mApiMethods == null) {
            mApiMethods = getRetrofit().create(ApiMethods.class);
        }
        return mApiMethods;
    }

    public <T> void GenericNetworkCall(final Call<T> call, final Callback<T> callback) {
        call.clone().enqueue(callback);
    }
}
