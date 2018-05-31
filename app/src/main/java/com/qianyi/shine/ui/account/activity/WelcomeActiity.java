package com.qianyi.shine.ui.account.activity;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;


import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.ui.account.adapter.MyPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/5/9.
 */

public class WelcomeActiity extends BaseActivity {
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private MyPagerAdapter myPagerAdapter;
    private int[] res={R.mipmap.welcome1,R.mipmap.welcome2,R.mipmap.welcome3};
    private List<View> imageViews;
    private View welcome1,welcome2,welcome3;
    @Override
    protected void initViews() {
        welcome1=LayoutInflater.from(this).inflate(R.layout.view_welcome1,null);
        welcome2=LayoutInflater.from(this).inflate(R.layout.view_welcome2,null);
        welcome3=LayoutInflater.from(this).inflate(R.layout.view_welcome3,null);
        imageViews=new ArrayList<>();
        imageViews.add(welcome1);
        imageViews.add(welcome2);
        imageViews.add(welcome3);
        myPagerAdapter=new MyPagerAdapter(this,imageViews);
        viewpager.setAdapter(myPagerAdapter);
    }
    @Override
    protected void initData() {

    }
    @Override
    protected void getResLayout() {
        //设置全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
    }
    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
}
