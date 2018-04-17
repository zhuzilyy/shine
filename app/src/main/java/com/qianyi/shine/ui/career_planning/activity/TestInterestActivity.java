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

    @Override
    protected void initViews() {
       title = findViewById(R.id.tv_title);
        title.setText("兴趣");
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
                startActivity(intent);
                break;

            default:
            break;


        }
    }
}
