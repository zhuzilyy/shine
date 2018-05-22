package com.qianyi.shine.ui.home.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.qianyi.shine.R;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.api.apiHome;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.dialog.CustomLoadingDialog;
import com.qianyi.shine.ui.account.bean.LoginBean;
import com.qianyi.shine.ui.college.activity.CollegeActivity;
import com.qianyi.shine.ui.college.adapter.MyPageAdapter;
import com.qianyi.shine.ui.home.fragment.OccupationBaseInfoFragment;
import com.qianyi.shine.ui.home.fragment.OccupationDetailsFragment;
import com.qianyi.shine.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/7.
 */

public class OccupationDetailActivity extends BaseActivity {
    @BindView(R.id.tablayout)
    public TabLayout tab;
    @BindView(R.id.view_pager)
    public ViewPager viewPager;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_category)
    TextView tv_category;
    public MyPageAdapter myPageAdapter;
    private MyReceiver myReceiver;
    private String occupationId,memberId,name,occupationName,occupationParentName;
    private CustomLoadingDialog customLoadingDialog;
    @Override
    protected void initViews() {
        BaseActivity.addActivity(this);
        tv_title.setText("职业介绍");
        myPageAdapter = new MyPageAdapter(getSupportFragmentManager());
        ArrayList<Fragment> datas = new ArrayList<Fragment>();
        datas.add(new OccupationBaseInfoFragment());
        datas.add(new OccupationDetailsFragment());
        myPageAdapter.setData(datas);

        ArrayList<String> titles = new ArrayList<String>();
        titles.add("基本信息");
        titles.add("职业信息");
        myPageAdapter.setTitles(titles);

        viewPager.setAdapter(myPageAdapter);
        // 将ViewPager与TabLayout相关联
        tab.setupWithViewPager(viewPager);
        //注册广播
        myReceiver=new MyReceiver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("com.action.occupation");
        registerReceiver(myReceiver,intentFilter);

        customLoadingDialog=new CustomLoadingDialog(this);
        LoginBean.LoginData.LoginInfo loginInfo = Utils.readUser(this);
        memberId= loginInfo.getId();
        Intent intent = getIntent();
        if (intent!=null){
            occupationName=intent.getStringExtra("occupationName");
            occupationParentName=intent.getStringExtra("occupationParentName");
        }
    }
    @Override
    protected void initData() {

    }
    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_occupation_detail);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.iv_back,R.id.tv_addFocus})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_addFocus:
                if (TextUtils.isEmpty(occupationId)){
                    Toast.makeText(this, "网络错误", Toast.LENGTH_SHORT).show();
                    return;
                }
                addFocus();
                break;
        }
    }
    //关注职业
    private void addFocus() {
        customLoadingDialog.show();
        apiHome.focusOccupation(apiConstant.FOCUS_OCCUPATION, memberId, occupationId,occupationParentName,occupationName, new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                Log.i("tag",s);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        customLoadingDialog.dismiss();
                        try {
                            JSONObject jsonObject=new JSONObject(s);
                            String info = jsonObject.getString("info");
                            Toast.makeText(OccupationDetailActivity.this, info, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                customLoadingDialog.dismiss();
                Toast.makeText(OccupationDetailActivity.this, "网络错误关注失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }

    class MyReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("com.action.occupation")){
                name=intent.getStringExtra("name");
                String category=intent.getStringExtra("category");
                occupationId=intent.getStringExtra("occupationId");
                tv_name.setText(name);
                tv_category.setText(category);
            }
        }
    }
}
