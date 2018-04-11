package com.qianyi.shine.ui.home.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
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
    private IntelligentFillCollegeActivity activity = new IntelligentFillCollegeActivity();
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_area)
    TextView tv_area;
    @BindView(R.id.tv_majorName)
    TextView tv_majorName;
    @BindView(R.id.tv_occupationName)
    TextView tv_occupationName;
    private MyReceiver myReceiver;
    private String majorName,area,occupationName;
    @Override
    protected void initViews() {
        tv_title.setText("意愿设置");

        //注册广播 拿到专业数据
        myReceiver=new  MyReceiver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("com.action.setwilling");
        registerReceiver(myReceiver,intentFilter);
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
    @OnClick({R.id.iv_back,R.id.rl_collegeArea,R.id.rl_major,R.id.rl_occupation,R.id.btn_confirm})
    public void click(View view){
        Intent intent=null;
        switch (view.getId()){
            case R.id.btn_confirm:
                int count=0;
                intent=new Intent();
                if (!TextUtils.isEmpty(area)){
                    count++;
                }
                if (!TextUtils.isEmpty(majorName)){
                    count++;
                }
                if (!TextUtils.isEmpty(occupationName)){
                    count++;
                }
                if (count==0){
                    Toast.makeText(WillingsSettingActivity.this, "请至少设置1项意愿", Toast.LENGTH_SHORT).show();
                    return;
                }
                intent.putExtra("willing",count);
                intent.putExtra("area",area);
                intent.putExtra("majorName",majorName);
                intent.putExtra("occupationName",occupationName);
                setResult(1,intent);
                finish();
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_collegeArea:
                startActivityForResult(new Intent(WillingsSettingActivity.this,SelectCollegeAreaActivity.class),1);
                break;
            case R.id.rl_major:
                intent=new Intent(WillingsSettingActivity.this,FindMajorActivity.class);
                intent.putExtra("tag","willingSetting");
                startActivityForResult(intent,2);
                break;
            case R.id.rl_occupation:
                intent=new Intent(WillingsSettingActivity.this,SearchOccupationActivity.class);
                intent.putExtra("tag","willingSetting");
                startActivityForResult(intent,3);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            switch (requestCode){
                case 1:
                    area=data.getStringExtra("area");
                    tv_area.setText(area);
                    break;
                case 3:
                    occupationName=data.getStringExtra("cccupationName");
                    tv_occupationName.setText(occupationName);
                    break;
            }
        }
    }

    class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.action.setwilling")){
                majorName=intent.getStringExtra("majorName");
                tv_majorName.setText(majorName);
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }
}
