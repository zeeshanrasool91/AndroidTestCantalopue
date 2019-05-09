package com.example.myapplication.mvp;


import com.example.myapplication.base.BaseRepositoryInput;
import com.example.myapplication.model.MostPopularResponse;
import com.example.myapplication.networking.AppConstants;
import com.example.myapplication.networking.BaseResponse;
import com.example.myapplication.networking.CustomRetrofitCallback;
import com.example.myapplication.networking.CustomRetrofitCallbackInterface;
import com.example.myapplication.networking.NetworkController;

import java.util.List;


public class MainInteractor implements BaseRepositoryInput<Integer, BaseResponse<List<MostPopularResponse>>> {

    @Override
    public void callWebApi(Integer input,final BaseRepositoryInput.OnRepositoryResponse<BaseResponse<List<MostPopularResponse>>> repositoryResponse) {

      NetworkController.getInstance().GenericNetworkCall(NetworkController.getInstance().getApiMethods().getMostPopularNews(input, AppConstants.HTTP.API_KEY), new CustomRetrofitCallback<>(new CustomRetrofitCallbackInterface<BaseResponse<List<MostPopularResponse>>>() {

            @Override
            public void onSuccess(BaseResponse<List<MostPopularResponse>> response) {
                repositoryResponse.onSuccess(response);
            }

            @Override
            public void onError(Throwable throwable) {
                repositoryResponse.onFailure(throwable);
            }
        }));
    }
}
