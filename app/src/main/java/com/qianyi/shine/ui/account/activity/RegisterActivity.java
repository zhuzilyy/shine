package com.qianyi.shine.ui.account.activity;

import android.view.View;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;

import butterknife.OnClick;

/**
 * Created by NEUNB on 2018/3/29.
 */

public class RegisterActivity extends BaseActivity {
    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_register);

    }

    @Override
    protected void initListener() {

    }
    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.tv_login,R.id.iv_login_wechat,R.id.iv_login_weibo,R.id.iv_login_qq})
    public void click(View view){
        switch (view.getId()){
            case R.id.tv_login:
                jumpActivity(this,LoginActivity.class);
                finish();
                break;
            case R.id.iv_login_qq:
                jumpActivity(RegisterActivity.this, BindPhoneActivity.class);
                break;
            case R.id.iv_login_wechat:
                jumpActivity(RegisterActivity.this, BindPhoneActivity.class);
                break;
            case R.id.iv_login_weibo:
                jumpActivity(RegisterActivity.this, BindPhoneActivity.class);
                break;
        }
    }
}
