package com.example.classifysample.view.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.classifysample.R;
import com.example.classifysample.base.BaseActivity;
import com.example.classifysample.contract.IClassifyContract;
import com.example.classifysample.model.bean.ClassifyBean;
import com.example.classifysample.model.bean.ClassifyChildBean;
import com.example.classifysample.presenter.ClassifyPresenter;
import com.example.classifysample.view.adapter.MyChildClassifyAdapter;
import com.example.classifysample.view.adapter.MyParentClassifyAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<ClassifyPresenter> implements IClassifyContract.IView {

    @BindView(R.id.rv_parent_classify)
    XRecyclerView mRvParentClassify;
    @BindView(R.id.rv_child_classify)
    XRecyclerView mRvChildClassify;

    private List<ClassifyBean.ResultBean> mParentData = new ArrayList<>();
    private List<ClassifyChildBean.ResultBean> mChildData = new ArrayList<>();

    private MyParentClassifyAdapter myParentClassifyAdapter;
    private MyChildClassifyAdapter mMyChildClassifyAdapter;
    private String mCurrentParentName;
    private TextView mHeadTextView;

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
        myParentClassifyAdapter = new MyParentClassifyAdapter(mParentData);
        //设置item的点击监听
        myParentClassifyAdapter.setmOnParentClassifyRecyclerviewItemClickLitener(new MyParentClassifyAdapter.OnParentClassifyRecyclerviewItemClickLitener() {
            @Override
            public void onItemClick(int position) {
                String id = mParentData.get(position).getId();
                mCurrentParentName = mParentData.get(position).getName();
                //获取右侧child列表
                mPresenter.getChildClassify(id);
                Toast.makeText(MainActivity.this, id, Toast.LENGTH_SHORT).show();
            }
        });
        mRvParentClassify.setLayoutManager(new LinearLayoutManager(this));
        mRvParentClassify.setAdapter(myParentClassifyAdapter);
        mPresenter.getParentClassify();


        mMyChildClassifyAdapter = new MyChildClassifyAdapter(mChildData);
        mMyChildClassifyAdapter.setmOnChildClassifyRecyclerviewItemClickLitener(new MyChildClassifyAdapter.OnChildClassifyRecyclerviewItemClickLitener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, mChildData.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        mRvChildClassify.setLayoutManager(new GridLayoutManager(this, 3));
        mRvChildClassify.setAdapter(mMyChildClassifyAdapter);
    }

    @Override
    public void onParentClassifySuccess(ClassifyBean classifyBean) {
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
        mMyChildClassifyAdapter.setData(mChildData);
        Log.e("tag", "child成功" + classifyChildBean.toString());
    }

    @Override
    public void onChildClassifyFailure(Throwable error) {
        Log.e("tag", "child失败" + error.toString());
    }
}
