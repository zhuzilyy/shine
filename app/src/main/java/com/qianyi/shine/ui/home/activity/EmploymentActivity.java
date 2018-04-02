package com.qianyi.shine.ui.home.activity;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.ui.home.fragment.BenkeFragment;
import com.qianyi.shine.ui.home.fragment.ZhuankeFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by NEUNB on 2018/4/2.
 */

public class EmploymentActivity extends BaseActivity {
    private Fragment currentFragment,zhuankeFragment,benkeFragment;
    @BindView(R.id.tv_benKe)
    TextView tv_benKe;
    @BindView(R.id.tv_zhuanKe)
    TextView tv_zhuanKe;
    @BindView(R.id.view_zhuanKe)
    View view_zhuanKe;
    @BindView(R.id.view_benKe)
    View view_benKe;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @Override
    protected void initViews() {
        tv_title.setText("看就业");
        zhuankeFragment=new ZhuankeFragment();
        benkeFragment=new BenkeFragment();
        //初始化碎片
        initFragment();
    }

    private void initFragment() {
        if (benkeFragment == null) {
            benkeFragment = new BenkeFragment();
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

    @Override
    protected void initData() {

    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_employment);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

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
                    benkeFragment = new BenkeFragment();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), benkeFragment);
                break;
            case R.id.ll_zhuanKe:
                tv_zhuanKe.setTextColor(getResources().getColor(R.color.main_blue));
                tv_benKe.setTextColor(Color.parseColor("#000000"));
                view_zhuanKe.setVisibility(View.VISIBLE);
                view_benKe.setVisibility(View.INVISIBLE);
                if (zhuankeFragment == null) {
                    zhuankeFragment = new ZhuankeFragment();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), zhuankeFragment);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
        transaction.commit();
    }
}
