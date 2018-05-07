package com.qianyi.shine.ui.college.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/5/7.
 */

public class BigImgActivity extends BaseActivity {
    @BindView(R.id.iv_bigImg) public ImageView iv_bigImg;
    @BindView(R.id.iv_back) public ImageView back;
    @BindView(R.id.tv_title) public TextView title;
    @Override
    protected void initViews() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title.setText("");
        String bigImgUrl = getIntent().getStringExtra("bigUrl");
        if(!TextUtils.isEmpty(bigImgUrl)){
            Glide.with(BigImgActivity.this).load(bigImgUrl).into(iv_bigImg);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_bigimg);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
}
