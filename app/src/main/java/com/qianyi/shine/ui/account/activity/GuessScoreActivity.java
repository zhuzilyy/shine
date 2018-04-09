package com.qianyi.shine.ui.account.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.qianyi.shine.MainActivity;
import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.ui.home.activity.SelectCollegeAreaActivity;
import com.qianyi.shine.utils.SetStatusBarColor;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by NEUNB on 2018/3/29.
 */

public class GuessScoreActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @Override
    protected void initViews() {
        tv_title.setText("高考预估成绩");

    }
    @Override
    protected void initData() {

    }
    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_guess_score);

    }

    @Override
    protected void initListener() {

    }
    @OnClick({R.id.btn_confirm,R.id.iv_back,R.id.tv_selectArea})
    public void click(View view){
        switch (view.getId()){
            case R.id.btn_confirm:
                jumpActivity(this,MainActivity.class);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_selectArea:
                jumpActivity(this,SelectCollegeAreaActivity.class);
                break;
        }
    }

    @Override
    protected void setStatusBarColor() {
        SetStatusBarColor.setWindowStatusBarColor(this,Color.parseColor("#ffffff"));
    }
}
