package com.qianyi.shine.ui.account.activity;

import android.content.Intent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by NEUNB on 2018/3/31.
 */

public class SplashActivity extends BaseActivity {
    @BindView(R.id.iv_splash)
    ImageView iv_splash;
    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {
        //开始动画
        startAnim();
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
                jumpActivity(SplashActivity.this,LoginActivity.class);
                finish();
            }
        });
    }
}
