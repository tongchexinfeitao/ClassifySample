package com.example.classifysample.presenter;

import com.example.classifysample.base.BasePresenter;
import com.example.classifysample.contract.IClassifyContract;
import com.example.classifysample.model.ClassifyModel;
import com.example.classifysample.model.bean.ClassifyBean;
import com.example.classifysample.model.bean.ClassifyChildBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ClassifyPresenter extends BasePresenter<IClassifyContract.IView> implements IClassifyContract.IPresenter {

    @Override
    public void getParentClassify() {
        ClassifyModel classifyModel = new ClassifyModel();
        classifyModel.getParentClassify()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassifyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClassifyBean classifyBean) {
                        if (mView != null) {
                            mView.onParentClassifySuccess(classifyBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView != null) {
                            mView.onParentClassifyFailure(e);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void getChildClassify(String getChildClassify) {
        ClassifyModel classifyModel = new ClassifyModel();
        classifyModel.getChildClassify(getChildClassify)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassifyChildBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClassifyChildBean classifyChildBean) {
                        if (mView != null) {
                            mView.onChildClassifySuccess(classifyChildBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView != null) {
                            mView.onChildClassifyFailure(e);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
