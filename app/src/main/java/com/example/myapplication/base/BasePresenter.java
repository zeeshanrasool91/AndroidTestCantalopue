package com.example.myapplication.base;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;


public abstract class BasePresenter<V> {

    protected V _view;

    protected BasePresenter() {

    }

    protected BasePresenter(V view) {
        attachView(view);
    }

    /**
     * Call this method in {Activity#onCreate}
     * or {Fragment#onAttach(Context)} to attach the MVP View object
     */
    @CallSuper
    private void attachView(@NonNull V view) {
        _view = view;
    }

    /**
     * Call this method in {Activity#onDestroy()}
     * or {Fragment#onDetach()} to detach the MVP View object
     */
    @CallSuper
    public void detachView() {
        _view = null;
    }

    /**
     * Check if the view is attached.
     * This checking is only necessary when returning from an asynchronous call
     */
    protected final boolean isViewAttached() {
        return _view != null;
    }

}
