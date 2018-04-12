package com.qianyi.shine.ui.mine.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by NEUNB on 2018/3/31.
 */

public class PersonalInfoActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.btn_quit)
    Button btn_quit;
    @Override
    protected void initViews() {
        tv_title.setText("我的资料");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_personal_info);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.iv_back,R.id.btn_quit})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_quit:
                quitAccount();
                break;
        }
    }

    /***
     * 退出当前账号
     */
    private void quitAccount() {

    }
}
