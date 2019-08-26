package com.example.classifysample.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.classifysample.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//1、继承LinearLayout
public class SearchView extends LinearLayout {
    @BindView(R.id.edt_search_content)
    EditText mEdtSearchContent;
    @BindView(R.id.btn_search)
    Button mBtnSearch;

    public SearchView(Context context) {
        super(context);
    }

    //2、在两个参数的构造器里 inflate布局
    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View inflate = inflate(context, R.layout.search_view_layout, this);
      //3、绑定butterknife
        ButterKnife.bind(inflate);
    }

    @OnClick(R.id.btn_search)
    public void onViewClicked() {
        String search_content = mEdtSearchContent.getText().toString().trim();
        if (monSearchClickListener != null) {
            monSearchClickListener.onSearch(search_content);
        }
    }


    //4、暴露一个接口给外部
   private onSearchClickListener monSearchClickListener;

    public void setSearchClickListener(onSearchClickListener monSearchClickListener) {
        this.monSearchClickListener = monSearchClickListener;
    }

   public interface onSearchClickListener {
        void onSearch(String searchContent);
    }


}
