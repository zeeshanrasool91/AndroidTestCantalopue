package com.example.myapplication.mvp;

import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.base.BaseRepositoryInput;
import com.example.myapplication.model.MostPopularResponse;
import com.example.myapplication.networking.BaseResponse;
import com.example.myapplication.networking.FailureException;

import java.util.List;


public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Actions {


    private MainInteractor mainInteractor;

    public MainPresenter(MainContract.View view) {
        super(view);
        mainInteractor = new MainInteractor();
    }

    public void initScreen() {
        _view.setupViews();
        _view.setupRecyclerViewAdapter();
    }

    @Override
    public void getAccessCodeFromServer(String accessToken) {
        if (_view.getInternetConnectivityStatus()) {
            _view.showLoading();
            mainInteractor.callWebApi(1, new BaseRepositoryInput.OnRepositoryResponse<BaseResponse<List<MostPopularResponse>>>() {
                @Override
                public void onSuccess(BaseResponse<List<MostPopularResponse>> response) {
                    _view.hideLoading();
                    _view.showSuccess(response.getStatus());
                    _view.showData(response.getResults());
                }

                @Override
                public void onFailure(Throwable error) {
                    _view.hideLoading();
                    if (error instanceof FailureException) {
                        _view.showFailure(((FailureException) error).getmMessage());
                    } else {
                        _view.showFailure("Unknown Error Message");
                    }
                }
            });

        } else {
            _view.hideLoading();
            _view.showInternetError("Internet Connection Failed!");
        }
    }

    @Override
    public void onItemClick(int position, MostPopularResponse mostPopularResponse) {
            _view.handleClickResponse(position,mostPopularResponse);
    }
}
