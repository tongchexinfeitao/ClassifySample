package com.example.classifysample.model.api;

import com.example.classifysample.model.bean.ClassifyBean;
import com.example.classifysample.model.bean.ClassifyChildBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IApi {
    @GET("small/commodity/v1/findFirstCategory")
    Observable<ClassifyBean> getParentClassify();

    @GET("small/commodity/v1/findSecondCategory")
    Observable<ClassifyChildBean> getChildClassify(@Query("firstCategoryId") String firstCategoryId);
}
