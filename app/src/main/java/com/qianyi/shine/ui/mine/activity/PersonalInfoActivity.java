package com.qianyi.shine.ui.mine.activity;

import android.view.View;
import android.widget.TextView;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.dialog.SelfDialog;
import com.qianyi.shine.ui.account.activity.LoginActivity;
import com.qianyi.shine.utils.ListActivity;
import com.qianyi.shine.utils.Utils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by NEUNB on 2018/3/31.
 */

public class PersonalInfoActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
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
    @OnClick({R.id.iv_back,R.id.tv_quit})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_quit:
                quitAccount();
                break;
        }
    }

    /***
     * 退出当前账号
     */
    private void quitAccount() {
        final SelfDialog quitDialog = new SelfDialog(this);
        quitDialog.setTitle("提示");
        quitDialog.setMessage("是否退出登录");
        quitDialog.setYesOnclickListener("确定", new SelfDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
               Utils.clearSharedUser(PersonalInfoActivity.this);
               quitDialog.dismiss();
                ListActivity.close();
               jumpActivity(PersonalInfoActivity.this,LoginActivity.class);
            }
        });
        quitDialog.setNoOnclickListener("取消", new SelfDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                quitDialog.dismiss();
            }
        });
        quitDialog.show();
    }
}
