package com.example.classifysample.model;

import com.example.classifysample.contract.IClassifyContract;
import com.example.classifysample.model.api.IApi;
import com.example.classifysample.model.bean.ClassifyBean;
import com.example.classifysample.model.bean.ClassifyChildBean;
import com.example.classifysample.uitils.RetrofitManager;

import io.reactivex.Observable;

public class ClassifyModel implements IClassifyContract.IModel {
    @Override
    public Observable<ClassifyBean> getParentClassify() {
        return RetrofitManager.getInstance()
                .create(IApi.class)
                .getParentClassify();
    }

    @Override
    public Observable<ClassifyChildBean> getChildClassify(String getChildClassify) {
        return RetrofitManager.getInstance()
                .create(IApi.class)
                .getChildClassify(getChildClassify);
    }


}
