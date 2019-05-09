package com.example.myapplication.mvp;


import com.example.myapplication.model.MostPopularResponse;

import java.util.List;

/**
 * Created by Administrator on 3/20/2018.
 */
public interface MainContract {

    /*
   View - this defines the methods that the pure views like Activity or Fragments etc will implement.
    */
    interface View {
        void setupViews();
        void showLoading();
        void hideLoading();
        void setupRecyclerViewAdapter();
        void showData(List<MostPopularResponse> dataResponse);
        void showSuccess(String message);
        void showFailure(String message);
        void showInternetError(String showInternetError);
        boolean getInternetConnectivityStatus();
        void handleClickResponse(int position,MostPopularResponse mostPopularResponse);
    }

    /*
    Actions - this defines the methods the pure Presenter class will implement. Also known as user actions,
    this is where the app logic is defined.
     */
    interface Actions {
        void initScreen();
        void getAccessCodeFromServer(String accessToken);
        void onItemClick(int position,MostPopularResponse mostPopularResponse);
    }

    /*
    this defines the methods that pure model or persistence class like database or server data will implement.
     */
    interface Repository {
        interface OnServiceResponse {
            void onSuccess();
            void onFailure();
        }
        void callWebApi(OnServiceResponse onServiceResponse);
    }

}
