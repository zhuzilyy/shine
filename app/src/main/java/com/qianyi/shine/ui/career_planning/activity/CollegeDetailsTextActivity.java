package com.qianyi.shine.ui.career_planning.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/5/24.
 */

public class CollegeDetailsTextActivity extends BaseActivity {
    @BindView(R.id.tvv) public TextView tvv;
    @BindView(R.id.tv_title) public TextView title;
    @BindView(R.id.iv_back) ImageView back;
    private String CollegeStr="";
    private String CollegeNameStr="";

    @Override
    protected void initViews() {

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        CollegeStr=getIntent().getStringExtra("college_content");
        CollegeNameStr=getIntent().getStringExtra("title");

    }

    @Override
    protected void initData() {
        title.setText(CollegeNameStr);
        tvv.setText(CollegeStr);
    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_college_details_text_more);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
}
