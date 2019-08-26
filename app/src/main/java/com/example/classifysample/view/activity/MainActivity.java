package com.example.classifysample.view.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.classifysample.R;
import com.example.classifysample.base.BaseActivity;
import com.example.classifysample.contract.IClassifyContract;
import com.example.classifysample.dao.DaoMaster;
import com.example.classifysample.dao.DaoSession;
import com.example.classifysample.model.bean.ClassifyBean;
import com.example.classifysample.model.bean.ClassifyChildBean;
import com.example.classifysample.model.dao.ClassifyBeanForm;
import com.example.classifysample.presenter.ClassifyPresenter;
import com.example.classifysample.uitils.RetrofitManager;
import com.example.classifysample.view.SearchView;
import com.example.classifysample.view.adapter.MyChildClassifyAdapter;
import com.example.classifysample.view.adapter.MyParentClassifyAdapter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<ClassifyPresenter> implements IClassifyContract.IView {

    @BindView(R.id.rv_parent_classify)
    XRecyclerView mRvParentClassify;
    @BindView(R.id.rv_child_classify)
    XRecyclerView mRvChildClassify;

    @BindView(R.id.searchView)
    SearchView searchView;

    private List<ClassifyBean.ResultBean> mParentData = new ArrayList<>();
    private List<ClassifyChildBean.ResultBean> mChildData = new ArrayList<>();

    private MyParentClassifyAdapter myParentClassifyAdapter;
    private MyChildClassifyAdapter mMyChildClassifyAdapter;
    private String mCurrentParentName;
    private TextView mHeadTextView;
    private TextView mHeadtextView;
    private String mCurrentId;
    private DaoSession mDaoSession;

    @Override
    protected ClassifyPresenter providePresenter() {
        return new ClassifyPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        mDaoSession = DaoMaster.newDevSession(this, "my.db");

        //5、设置监听
        searchView.setSearchClickListener(new SearchView.onSearchClickListener() {
            @Override
            public void onSearch(String searchContent) {
                Toast.makeText(MainActivity.this, searchContent, Toast.LENGTH_SHORT).show();
            }
        });


        myParentClassifyAdapter = new MyParentClassifyAdapter(mParentData);
        //设置item的点击监听
        myParentClassifyAdapter.setmOnParentClassifyRecyclerviewItemClickLitener(new MyParentClassifyAdapter.OnParentClassifyRecyclerviewItemClickLitener() {
            @Override
            public void onItemClick(int position) {
                mCurrentId = mParentData.get(position).getId();
                mCurrentParentName = mParentData.get(position).getName();
                //获取右侧child列表
                mPresenter.getChildClassify(mCurrentId);
                Toast.makeText(MainActivity.this, mCurrentId, Toast.LENGTH_SHORT).show();
            }
        });
        mRvParentClassify.setLayoutManager(new LinearLayoutManager(this));
        mRvParentClassify.setAdapter(myParentClassifyAdapter);


        if (RetrofitManager.hasNet(this)) {
            //有网联网请求
            mPresenter.getParentClassify();

        } else {
            //查询数据库
            List<ClassifyBeanForm> list = mDaoSession.getClassifyBeanFormDao()
                    .queryBuilder()
                    .build()
                    .list();
            if (list != null && list.size() > 0) {
                //取出存储的对象
                ClassifyBeanForm classifyBeanForm = list.get(0);
                //从对象中取出json
                String classifyBeanjson = classifyBeanForm.getClassifyBeanjson();
                //转换成对应的bean类
                ClassifyBean classifyBean = new Gson().fromJson(classifyBeanjson, ClassifyBean.class);

                //刷新适配器
                mParentData = classifyBean.getResult();
                mRvParentClassify.setLayoutManager(new LinearLayoutManager(this));
                myParentClassifyAdapter.setData(mParentData);
                Log.e("tag", "从数据库中读取缓存数据成功" + classifyBean.toString());
            }
        }

        mMyChildClassifyAdapter = new MyChildClassifyAdapter(mChildData);
        mMyChildClassifyAdapter.setmOnChildClassifyRecyclerviewItemClickLitener(new MyChildClassifyAdapter.OnChildClassifyRecyclerviewItemClickLitener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, mChildData.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        mRvChildClassify.setLayoutManager(new GridLayoutManager(this, 3));
        mRvChildClassify.setAdapter(mMyChildClassifyAdapter);
        mHeadtextView = new TextView(this);
        mRvChildClassify.addHeaderView(mHeadtextView);
    }

    @Override
    public void onParentClassifySuccess(ClassifyBean classifyBean) {

        //把联网请求回来的bean转换成json字符串
        String json = new Gson().toJson(classifyBean);
        //将json存到对应的bean类中
        ClassifyBeanForm classifyBeanForm = new ClassifyBeanForm(null, json);
        mDaoSession.clear();
        //将对应的bean类对象存储到数据库
        mDaoSession.getClassifyBeanFormDao().insert(classifyBeanForm);


        mParentData = classifyBean.getResult();
        mRvParentClassify.setLayoutManager(new LinearLayoutManager(this));
        myParentClassifyAdapter.setData(mParentData);
        Log.e("tag", "成功" + classifyBean.toString());
    }

    @Override
    public void onParentClassifyFailure(Throwable error) {
        Log.e("tag", "失败" + error.toString());
    }

    @Override
    public void onChildClassifySuccess(ClassifyChildBean classifyChildBean) {
        mChildData = classifyChildBean.getResult();
        mHeadtextView.setText(mCurrentParentName);
        mMyChildClassifyAdapter.setData(mChildData);
        Log.e("tag", "child成功" + classifyChildBean.toString());
    }

    @Override
    public void onChildClassifyFailure(Throwable error) {
        Log.e("tag", "child失败" + error.toString());
    }
}
