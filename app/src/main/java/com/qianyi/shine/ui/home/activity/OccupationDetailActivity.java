package com.qianyi.shine.ui.home.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.ui.college.adapter.MyPageAdapter;
import com.qianyi.shine.ui.college.fragments.Profession_BaseInfoFragment;
import com.qianyi.shine.ui.college.fragments.Profession_EstablishmentFragment;
import com.qianyi.shine.ui.college.fragments.Profession_ProspectsFragment;
import com.qianyi.shine.ui.gaokao_news.view.XTitleView;
import com.qianyi.shine.ui.home.fragment.OccupationBaseInfoFragment;
import com.qianyi.shine.ui.home.fragment.OccupationDetailsFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

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
        titles.add("职业细化");
        myPageAdapter.setTitles(titles);

        viewPager.setAdapter(myPageAdapter);
        // 将ViewPager与TabLayout相关联
        tab.setupWithViewPager(viewPager);
        //注册广播
        myReceiver=new MyReceiver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("com.action.occupation");
        registerReceiver(myReceiver,intentFilter);

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
    @OnClick({R.id.iv_back})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
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
                String name=intent.getStringExtra("name");
                String category=intent.getStringExtra("category");
                tv_name.setText(name);
                tv_category.setText(category);
            }
        }
    }
}
