package com.qianyi.shine.ui.home.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/8.
 */

public class WillingsSettingActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_area)
    TextView tv_area;
    @Override
    protected void initViews() {
        tv_title.setText("意愿设置");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_willing_setting);
    }

    @Override
    protected void initListener() {

    }
    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.iv_back,R.id.rl_collegeArea,R.id.rl_major,R.id.rl_occupation})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_collegeArea:
                startActivityForResult(new Intent(WillingsSettingActivity.this,SelectCollegeAreaActivity.class),1);
                break;
            case R.id.rl_major:
                startActivityForResult(new Intent(WillingsSettingActivity.this,FindMajorActivity.class),2);
                break;
            case R.id.rl_occupation:
                startActivityForResult(new Intent(WillingsSettingActivity.this,SearchOccupationActivity.class),3);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            switch (requestCode){
                case 1:
                    String area=data.getStringExtra("area");
                    tv_area.setText(area);
                    break;
            }
        }
    }
}
