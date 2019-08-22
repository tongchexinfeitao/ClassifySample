package com.example.classifysample.base;

public class BasePresenter<V extends IBaseView> {
    protected V mView;

    public void attachView(V view) {
        this.mView = view;
    }

    public void dettachView() {
        this.mView = null;
    }
}
