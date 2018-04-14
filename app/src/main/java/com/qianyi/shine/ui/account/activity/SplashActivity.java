package com.qianyi.shine.ui.account.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.qianyi.shine.MainActivity;
import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.ui.account.bean.LoginBean;
import com.qianyi.shine.utils.Utils;

import butterknife.BindView;

/**
 * Created by NEUNB on 2018/3/31.
 */

public class SplashActivity extends BaseActivity {
    @BindView(R.id.iv_splash)
    ImageView iv_splash;
    @Override
    protected void initViews() {
        BaseActivity.addActivity(this);
        boolean isGranted=checkedAllPermission(new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION
        });
        if(isGranted){
            //开始动画
            startAnim();
        }else {
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },102);
        }
    }
    @Override
    protected void initData() {
    }
    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
    private void startAnim() {
        AlphaAnimation animation=new AlphaAnimation(1, 1);
        animation.setDuration(2000);//设置动画持续时间
        animation.setRepeatCount(0);//设置重复次数
        animation.setFillAfter(true);//动画执行完后是否停留在执行完的状态
        iv_splash.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {

            }
            @Override
            public void onAnimationRepeat(Animation arg0) {

            }
            @Override
            public void onAnimationEnd(Animation animaton) {
                //自动登录
                LoginBean.LoginData.LoginInfo user=Utils.readUser(SplashActivity.this);
                if( user != null){
                    if(!TextUtils.isEmpty(user.getId())){
                        //登录过，进入主界面
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }else{
                    jumpActivity(SplashActivity.this,LoginActivity.class);
                }
                finish();

            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //请求吗正确
        if(102==requestCode){
            boolean isGranded=true;
            for (int permissionCode:grantResults) {
                if(permissionCode!= PackageManager.PERMISSION_GRANTED){
                    //权限被拒绝
                    isGranded=false;
                    Log.i("xzy","aaaaaaaaaaaaaaa");
                    break;
                }
            }
            if(isGranded){
                Log.i("xzy","在启动页的权限请求成功");
                //请求权限被赋予
                //开始动画
                startAnim();
            }else {
                //权限被拒绝
                Log.i("xzy","在启动页的权限请求失败");
                //开始动画
                startAnim();
            }
        }
    }
    private boolean checkedAllPermission(String[] strings) {
        for (String p:strings) {
            if(ContextCompat.checkSelfPermission(this,p)!= PackageManager.PERMISSION_GRANTED){
                //有一个没授予权限，就返回false
                return false;
            }
        }
        return true;
    }
}
