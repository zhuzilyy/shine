package com.qianyi.shine.ui.college.activity;

import android.view.View;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.ui.gaokao_news.view.XTitleView;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/31.
 */

public class MoreCollegeActivity extends BaseActivity {
    @BindView(R.id.title)
    public XTitleView title;
    @Override
    protected void initViews() {
        title=findViewById(R.id.title);


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_morecollege);

    }

    @Override
    protected void initListener() {
        title.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void setStatusBarColor() {

    }
}
