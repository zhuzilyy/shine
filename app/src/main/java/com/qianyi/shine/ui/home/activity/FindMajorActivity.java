package com.qianyi.shine.ui.home.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.ui.home.fragment.BenkeFragment;
import com.qianyi.shine.ui.home.fragment.MajorBenkeFragment;
import com.qianyi.shine.ui.home.fragment.MajorZhuanKeFragment;
import com.qianyi.shine.ui.home.fragment.ZhuankeFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/6.
 */

public class FindMajorActivity extends BaseActivity {
    private Fragment currentFragment,zhuankeFragment,benkeFragment;
    @BindView(R.id.tv_benKe)
    TextView tv_benKe;
    @BindView(R.id.tv_zhuanKe)
    TextView tv_zhuanKe;
    @BindView(R.id.view_zhuanKe)
    View view_zhuanKe;
    @BindView(R.id.view_benKe)
    View view_benKe;
    private MyReceiver myReceiver;
    @Override
    protected void initViews() {
        zhuankeFragment=new MajorZhuanKeFragment();
        benkeFragment=new MajorBenkeFragment();
        //初始化碎片
        initFragment();
        //注册广播 点击选择专业的条目关闭专业选择的界面
        myReceiver=new MyReceiver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("com.action.setwilling");
        registerReceiver(myReceiver,intentFilter);
    }

    @Override
    protected void initData() {

    }
    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_find_major);
    }
    @Override
    protected void initListener() {

    }
    @Override
    protected void setStatusBarColor() {

    }
    private void initFragment() {
        if (benkeFragment == null) {
            benkeFragment = new MajorBenkeFragment();
        }

        if (!benkeFragment.isAdded()) {
            // 提交事务
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fl_frame, benkeFragment).commit();

            // 记录当前Fragment
            currentFragment = benkeFragment;
        }
    }
    /**
     * 添加或者显示碎片
     *
     * @param transaction
     * @param fragment
     */
    private void addOrShowFragment(FragmentTransaction transaction,
                                   Fragment fragment) {
        if (currentFragment == fragment)
            return;
        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            transaction.hide(currentFragment).add(R.id.fl_frame, fragment).commit();
        } else {
            transaction.hide(currentFragment).show(fragment).commit();
        }
        currentFragment = fragment;
    }
    @OnClick({R.id.ll_benKe,R.id.ll_zhuanKe,R.id.iv_back})
    public void click(View view){
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        switch (view.getId()){
            case R.id.ll_benKe:
                tv_benKe.setTextColor(getResources().getColor(R.color.main_blue));
                tv_zhuanKe.setTextColor(Color.parseColor("#000000"));
                view_benKe.setVisibility(View.VISIBLE);
                view_zhuanKe.setVisibility(View.INVISIBLE);
                if (benkeFragment == null) {
                    benkeFragment = new MajorBenkeFragment();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), benkeFragment);
                break;
            case R.id.ll_zhuanKe:
                tv_zhuanKe.setTextColor(getResources().getColor(R.color.main_blue));
                tv_benKe.setTextColor(Color.parseColor("#000000"));
                view_zhuanKe.setVisibility(View.VISIBLE);
                view_benKe.setVisibility(View.INVISIBLE);
                if (zhuankeFragment == null) {
                    zhuankeFragment = new MajorZhuanKeFragment();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), zhuankeFragment);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
        transaction.commit();
    }

    class MyReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.action.setwilling")){
                finish();
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }
}
