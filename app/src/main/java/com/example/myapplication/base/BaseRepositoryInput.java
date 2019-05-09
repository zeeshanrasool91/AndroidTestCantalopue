package com.example.myapplication.base;

public  interface BaseRepositoryInput<E, T> {
    interface OnRepositoryResponse<T> {
        void onSuccess(T response);

        void onFailure(Throwable error);
    }

    void callWebApi(E input, OnRepositoryResponse<T> repositoryResponse);
}