package com.qianyi.shine.ui.account.activity;

import android.Manifest;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;


import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.ui.account.adapter.MyPagerAdapter;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

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
    private String TAG="ss";

    @Override
    protected void initViews() {

        requestPermissions();

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


    private void requestPermissions() {
        RxPermissions rxPermission = new RxPermissions(WelcomeActiity.this);
        rxPermission
                .requestEach(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_CALENDAR,
                        Manifest.permission.READ_CALL_LOG,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.READ_SMS,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.SEND_SMS)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            // 用户已经同意该权限
                            Log.d(TAG, permission.name + " is granted.");
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                            Log.d(TAG, permission.name + " is denied. More info should be provided.");
                        } else {
                            // 用户拒绝了该权限，并且选中『不再询问』
                            Log.d(TAG, permission.name + " is denied.");
                        }
                    }
                });


    }



}
