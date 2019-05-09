package com.example.myapplication.networking;


import com.example.myapplication.model.MostPopularResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;



public interface ApiMethods {

    @GET("svc/mostpopular/v2/viewed/{period}.json")
    Call<BaseResponse<List<MostPopularResponse>>> getMostPopularNews(
            @Path("period") int period,
            @Query("api-key") String apiKey
    );

}
