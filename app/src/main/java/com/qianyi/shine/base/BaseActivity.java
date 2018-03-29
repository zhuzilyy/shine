package com.qianyi.shine.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Administrator on 2018/1/25.
 */

public abstract class BaseActivity extends FragmentActivity {
    Unbinder unbinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initConfigure();
    }
    private void initConfigure() {
        //加载布局
        getResLayout();
        unbinder= ButterKnife.bind(this);
        //初始化控件
        initViews();
        //初始化数据
        initData();
        initListener();
        //设置状态栏的颜色
        setStatusColor();
    }
    private void setStatusColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus();
        }
    }
    private void setTranslucentStatus() {
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        final int status = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        params.flags |= status;
        window.setAttributes(params);
    }
    //跳转的方法
    public void jumpActivity(Context context,Class<?> targetActivity){
        Intent intent=new Intent(context,targetActivity);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    protected abstract void initViews();
    protected abstract void initData();
    protected abstract void getResLayout();
    protected abstract void initListener();
}
