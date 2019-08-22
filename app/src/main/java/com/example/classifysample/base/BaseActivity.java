package com.example.classifysample.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {
    protected P mPresenter;
    private Unbinder mUnbind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideLayoutId());
        mUnbind = ButterKnife.bind(this);
        mPresenter = providePresenter();
        //绑定view
        mPresenter.attachView(this);
        initView();
        initData();
    }

    protected void initData() {

    }

    protected void initView() {

    }

    protected abstract P providePresenter();

    protected abstract int provideLayoutId();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放
        mPresenter.dettachView();
        //释放
        mUnbind.unbind();
    }
}
