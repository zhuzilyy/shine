package com.qianyi.shine.ui.mine.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.ui.mine.fragment.CollegeFragment;
import com.qianyi.shine.ui.mine.fragment.MajorFragment;
import com.qianyi.shine.ui.mine.fragment.MeasurementFragment;
import com.qianyi.shine.ui.mine.fragment.ProfessionFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/3.
 */

public class FocusActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.view_college)
    View view_college;
    @BindView(R.id.view_major)
    View view_major;
    @BindView(R.id.view_profession)
    View view_profession;
    @BindView(R.id.view_measurement)
    View view_measurement;
    private Fragment currentFragment,collegeFragment,majorFragment,professionFragment,measurementFragment;
    @Override
    protected void initViews() {
        BaseActivity.addActivity(this);
        tv_title.setText("我的关注");
        collegeFragment=new CollegeFragment();
        majorFragment=new MajorFragment();
        professionFragment=new ProfessionFragment();
        measurementFragment=new MeasurementFragment();
        //初始化碎片
        initFragment();
    }
    @Override
    protected void initData() {

    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_focus);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
    private void initFragment() {
        if (collegeFragment == null) {
            collegeFragment = new CollegeFragment();
        }

        if (!collegeFragment.isAdded()) {
            // 提交事务
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fl_frame, collegeFragment).commit();

            // 记录当前Fragment
            currentFragment = collegeFragment;
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
    @OnClick({R.id.ll_college,R.id.ll_major,R.id.ll_profession,R.id.ll_measurement,R.id.iv_back})
    public void click(View view){
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        switch (view.getId()){
            case R.id.ll_college:
                setViewVisible(view_college,view_major,view_measurement,view_profession);
                if (collegeFragment == null) {
                    collegeFragment = new CollegeFragment();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), collegeFragment);
                break;
            case R.id.ll_major:
                setViewVisible(view_major,view_college,view_measurement,view_profession);
                if (majorFragment == null) {
                    majorFragment = new MajorFragment();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), majorFragment);
                break;
            case R.id.ll_profession:
                setViewVisible(view_profession,view_college,view_major,view_measurement);
                if (professionFragment == null) {
                    professionFragment = new ProfessionFragment();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), professionFragment);
                break;
            case R.id.ll_measurement:
                setViewVisible(view_measurement,view_college,view_major,view_profession);
                if (measurementFragment == null) {
                    measurementFragment = new MeasurementFragment();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), measurementFragment);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
        transaction.commit();
    }
    //设置可见和不可见
    private void setViewVisible(View view1, View view2, View view3, View view4) {
        view1.setVisibility(View.VISIBLE);
        view2.setVisibility(View.INVISIBLE);
        view3.setVisibility(View.INVISIBLE);
        view4.setVisibility(View.INVISIBLE);

    }
}
