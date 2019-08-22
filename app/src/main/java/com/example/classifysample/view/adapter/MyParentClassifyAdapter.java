package com.example.classifysample.view.adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.classifysample.R;
import com.example.classifysample.model.bean.ClassifyBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyParentClassifyAdapter extends XRecyclerView.Adapter<MyParentClassifyAdapter.MyParentViewHolder> {

    private int mCurrentSelectedPostion = 0;
    private List<ClassifyBean.ResultBean> mData = new ArrayList<>();

    public MyParentClassifyAdapter(List<ClassifyBean.ResultBean> result) {
        this.mData = result;
    }

    @NonNull
    @Override
    public MyParentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_parent_classify_layout, viewGroup, false);
        return new MyParentViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyParentViewHolder viewHolder, final int i) {
        viewHolder.mTvParentClassifyTitle.setText(mData.get(i).getName());

        if(mCurrentSelectedPostion == i){
            viewHolder. itemView.setBackgroundResource(R.color.colorAccent);
        }else{
            viewHolder. itemView.setBackgroundResource(0);
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnParentClassifyRecyclerviewItemClickLitener != null) {
                    mOnParentClassifyRecyclerviewItemClickLitener.onItemClick(i);
                }
                //当前点击的position要变色
                mCurrentSelectedPostion =i;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setData(List<ClassifyBean.ResultBean> result) {
        mData.clear();
        mData.addAll(result);
        notifyDataSetChanged();
    }

    class MyParentViewHolder extends XRecyclerView.ViewHolder {
        @BindView(R.id.tv_parent_classify_title)
        TextView mTvParentClassifyTitle;

        public MyParentViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    OnParentClassifyRecyclerviewItemClickLitener mOnParentClassifyRecyclerviewItemClickLitener;

    public void setmOnParentClassifyRecyclerviewItemClickLitener(OnParentClassifyRecyclerviewItemClickLitener mOnParentClassifyRecyclerviewItemClickLitener) {
        this.mOnParentClassifyRecyclerviewItemClickLitener = mOnParentClassifyRecyclerviewItemClickLitener;
    }

    public interface OnParentClassifyRecyclerviewItemClickLitener {
        void onItemClick(int position);
    }
}
