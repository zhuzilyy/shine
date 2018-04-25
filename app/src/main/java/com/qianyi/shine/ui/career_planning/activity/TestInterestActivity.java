package com.qianyi.shine.ui.career_planning.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/17.
 */

public class TestInterestActivity extends BaseActivity implements View.OnClickListener {
    private ImageView back;
    private TextView title;
    @BindView(R.id.btn_startTest)
    public Button btn_start;
    @BindView(R.id.tv_introduce)
    public TextView tv_introduce;


    @Override
    protected void initViews() {
       title = findViewById(R.id.tv_title);
        title.setText("兴趣");
        tv_introduce.setText("霍兰德认为，个人职业兴趣特性与职业之间应有一种内在的对应关系。根据兴趣的不同，人格可分为研究型（I）、艺术型（A）、社会型（S）、企业型（E）、传统型（C）、现实型（R）六个维度，每个人的性格都是这六个维度的不同程度组合。");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_textinterest);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.iv_back,R.id.btn_startTest})
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_back:
                finish();
            break;
            case R.id.btn_startTest:
                Intent intent = new Intent(TestInterestActivity.this,TestActivity.class);
                //testType  0:性格测试    1：兴趣测试
                intent.putExtra("testType","1");
                startActivity(intent);
                finish();
                break;

            default:
            break;


        }
    }


}
