package com.example.classifysample.view.adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.classifysample.R;
import com.example.classifysample.model.bean.ClassifyBean;
import com.example.classifysample.model.bean.ClassifyChildBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyChildClassifyAdapter extends XRecyclerView.Adapter<MyChildClassifyAdapter.MyChildViewHolder> {

    private List<ClassifyChildBean.ResultBean> mData = new ArrayList<>();

    public MyChildClassifyAdapter(List<ClassifyChildBean.ResultBean> result) {
        this.mData = result;
    }

    @NonNull
    @Override
    public MyChildViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_child_classify_layout, viewGroup, false);
        return new MyChildViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyChildViewHolder viewHolder, final int i) {
        viewHolder.mTvChildClassifyTitle.setText(mData.get(i).getName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnChildClassifyRecyclerviewItemClickLitener != null) {
                    mOnChildClassifyRecyclerviewItemClickLitener.onItemClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setData(List<ClassifyChildBean.ResultBean> result) {
        mData.clear();
        mData.addAll(result);
        notifyDataSetChanged();
    }

    class MyChildViewHolder extends XRecyclerView.ViewHolder {
        @BindView(R.id.tv_child_classify_title)
        TextView mTvChildClassifyTitle;

        public MyChildViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    OnChildClassifyRecyclerviewItemClickLitener mOnChildClassifyRecyclerviewItemClickLitener;

    public void setmOnChildClassifyRecyclerviewItemClickLitener(OnChildClassifyRecyclerviewItemClickLitener mOnChildClassifyRecyclerviewItemClickLitener) {
        this.mOnChildClassifyRecyclerviewItemClickLitener = mOnChildClassifyRecyclerviewItemClickLitener;
    }

    public interface OnChildClassifyRecyclerviewItemClickLitener {
        void onItemClick(int position);
    }
}
