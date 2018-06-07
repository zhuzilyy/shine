package com.qianyi.shine.ui.mine.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.qianyi.shine.R;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.ui.account.activity.WebviewActivity;
import com.qianyi.shine.utils.APKVersionCodeUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by NEUNB on 2018/3/29.
 */
public class SettingActivity extends BaseActivity{
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_versionCode)
    TextView tv_versionCode;
    @Override
    protected void initViews() {
        BaseActivity.addActivity(this);
        tv_title.setText("设置");
        String versoinName = APKVersionCodeUtils.getVerName(this);
        tv_versionCode.setText(versoinName);
    }
    @Override
    protected void initData() {

    }
    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_setting);
    }
    @Override
    protected void initListener() {

    }
    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.iv_back,R.id.rl_accountManage,R.id.rl_userAgreement})
    public void click(View view){
        Intent intent=null;
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_accountManage:
                intent=new Intent(SettingActivity.this,PersonalInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_userAgreement:
                intent=new Intent(SettingActivity.this,WebviewActivity.class);
                intent.putExtra("title","用户协议");
                intent.putExtra("url", apiConstant.AGREEMENT);
                startActivity(intent);
                break;
        }
    }
}
