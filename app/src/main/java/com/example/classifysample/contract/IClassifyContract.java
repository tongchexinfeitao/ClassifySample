package com.example.classifysample.contract;

import com.example.classifysample.base.IBaseView;
import com.example.classifysample.model.bean.ClassifyBean;
import com.example.classifysample.model.bean.ClassifyChildBean;

import io.reactivex.Observable;

public interface IClassifyContract {


    interface IView extends IBaseView {
        void onParentClassifySuccess(ClassifyBean classifyBean);

        void onParentClassifyFailure(Throwable error);

        void onChildClassifySuccess(ClassifyChildBean classifyChildBean);

        void onChildClassifyFailure(Throwable error);
    }


    interface IPresenter {
        void getParentClassify();

        void getChildClassify(String getChildClassify);
    }

    interface IModel {
        Observable<ClassifyBean> getParentClassify();

        Observable<ClassifyChildBean> getChildClassify(String getChildClassify);
    }
}
