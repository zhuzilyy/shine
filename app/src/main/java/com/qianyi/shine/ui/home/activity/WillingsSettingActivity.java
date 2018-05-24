package com.qianyi.shine.ui.home.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.api.apiHome;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.dialog.CustomLoadingDialog;
import com.qianyi.shine.ui.account.activity.GuessScoreActivity;
import com.qianyi.shine.ui.account.activity.LoginActivity;
import com.qianyi.shine.ui.account.bean.LoginBean;
import com.qianyi.shine.utils.Utils;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/8.
 */

public class WillingsSettingActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_area)
    TextView tv_area;
    @BindView(R.id.tv_majorName)
    TextView tv_majorName;
    @BindView(R.id.tv_occupationName)
    TextView tv_occupationName;
    private MyReceiver myReceiver;
    private String intention_area="",intention_job="",intention_major="",majorId="",occupationParentName="";
    CustomLoadingDialog customLoadingDialog;
    @Override
    protected void initViews() {
        BaseActivity.addActivity(this);
        tv_title.setText("意愿设置");

        //注册广播 拿到专业数据
        myReceiver=new  MyReceiver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("com.action.setwilling");
        registerReceiver(myReceiver,intentFilter);
        //读取已经设置的意愿
        LoginBean.LoginData.LoginInfo loginInfo = Utils.readUser(this);
        LoginBean.LoginData.LoginInfo.MemberScoreInfo member_scoreinfo = loginInfo.getMember_scoreinfo();
        intention_area = member_scoreinfo.getIntention_area();
        intention_job = member_scoreinfo.getIntention_job();
        intention_major = member_scoreinfo.getIntention_major();
        majorId = member_scoreinfo.getMajor_id();
        occupationParentName = member_scoreinfo.getCate_two_name();
        if (TextUtils.isEmpty(intention_area)){
            tv_area.setText("点击选择");
        }else{
            tv_area.setText(intention_area);
        }
        if (TextUtils.isEmpty(intention_job)){
            tv_occupationName.setText("点击选择");
        }else{
            tv_occupationName.setText(intention_job);
        }
        if (TextUtils.isEmpty(intention_major)){
            tv_majorName.setText("点击选择");
        }else{
            tv_majorName.setText(intention_major);
        }


        customLoadingDialog=new CustomLoadingDialog(this);
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
    @OnClick({R.id.iv_back,R.id.rl_collegeArea,R.id.rl_major,R.id.rl_occupation,R.id.btn_confirm,R.id.iv_areaDelete,
    R.id.iv_majorDelete,R.id.iv_occupationDelete})
    public void click(View view){
        Intent intent=null;
        switch (view.getId()){
            case R.id.btn_confirm:
                int count=0;
                if (!TextUtils.isEmpty(intention_area)){
                    count++;
                }
                if (!TextUtils.isEmpty(intention_job)){
                    count++;
                }
                if (!TextUtils.isEmpty(intention_major)){
                    count++;
                }
                if (count==0){
                    Toast.makeText(WillingsSettingActivity.this, "请至少设置1项意愿", Toast.LENGTH_SHORT).show();
                    return;
                }
                setWillings(count);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_collegeArea:
                startActivityForResult(new Intent(WillingsSettingActivity.this,WillingSelectCollegeAreaActivity.class),1);
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
            case R.id.iv_areaDelete:
                intention_area="";
                tv_area.setText("点击选择");
                break;
            case R.id.iv_majorDelete:
                intention_major="";
                majorId="";
                tv_majorName.setText("点击选择");
                break;
            case R.id.iv_occupationDelete:
                intention_job="";
                tv_occupationName.setText("点击选择");
                break;
        }
    }
    //意愿设置
    private void setWillings(final int count) {
        customLoadingDialog.show();
        final LoginBean.LoginData.LoginInfo loginInfo = Utils.readUser(this);
        String memberId= loginInfo.getId();
        apiHome.willingSet(apiConstant.WILLING_SETTING, memberId, occupationParentName,majorId,intention_area, intention_major, intention_job, new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                customLoadingDialog.dismiss();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        LoginBean loginBean=gson.fromJson(s, LoginBean.class);
                        if(loginBean != null){
                            String code = loginBean.getCode();
                            if("0" .equals(code)){
                                LoginBean.LoginData.LoginInfo user = loginBean.getData().getInfo();
                                try {
                                    //存储当前用户
                                    Utils.saveUser(user,WillingsSettingActivity.this);
                                    Intent intent=new Intent();
                                    intent.putExtra("willing",count);
                                    intent.putExtra("area",intention_area);
                                    intent.putExtra("majorName",intention_major);
                                    intent.putExtra("occupationName",intention_job);
                                    intent.putExtra("majorId",majorId);
                                    setResult(1,intent);
                                    finish();
                                }catch (Exception e){
                                    Log.i("excaption_shine",e.getMessage());
                                }
                            }else {
                                Toast.makeText(WillingsSettingActivity.this, ""+loginBean.getInfo(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                customLoadingDialog.dismiss();
                Toast.makeText(WillingsSettingActivity.this, "网络错误意愿设置失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            switch (requestCode){
                case 1:
                    intention_area=data.getStringExtra("area");
                    tv_area.setText(intention_area);
                    break;
                case 3:
                    intention_job=data.getStringExtra("cccupationName");
                    occupationParentName=data.getStringExtra("occupationParentName");
                    tv_occupationName.setText(intention_job);
                    break;
            }
        }
    }

    class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.action.setwilling")){
                intention_major=intent.getStringExtra("majorName");
                majorId=intent.getStringExtra("majorId");
                tv_majorName.setText(intention_major);
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }
}
