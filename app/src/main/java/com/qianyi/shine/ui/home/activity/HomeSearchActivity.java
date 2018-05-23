package com.qianyi.shine.ui.home.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.ui.account.view.MEditText;
import com.qianyi.shine.ui.college.adapter.MyPageAdapter;
import com.qianyi.shine.ui.college.fragments.Profession_BaseInfoFragment;
import com.qianyi.shine.ui.college.fragments.Profession_EstablishmentFragment;
import com.qianyi.shine.ui.college.fragments.Profession_ProspectsFragment;
import com.qianyi.shine.ui.home.fragment.MajorBenkeFragment;
import com.qianyi.shine.ui.home.fragment.MajorZhuanKeFragment;
import com.qianyi.shine.ui.home.fragment.SearchHomeCollegeFragment;
import com.qianyi.shine.ui.home.fragment.SearchHomeProfessionFragment;
import com.qianyi.shine.utils.SPUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/10.
 */

public class HomeSearchActivity extends BaseActivity {
    @BindView(R.id.tv_college)
    TextView tv_college;
    @BindView(R.id.tv_major)
    TextView tv_major;
    @BindView(R.id.view_college)
    View view_college;
    @BindView(R.id.view_major)
    View view_major;
    @BindView(R.id.tv_title)
    TextView tv_title;
    private Fragment currentFragment,collegeFragment,majorFragment;
    @Override
    protected void initViews() {
        BaseActivity.addActivity(this);
        collegeFragment=new SearchHomeCollegeFragment();
        majorFragment=new SearchHomeProfessionFragment();
        //初始化碎片
        initFragment();
        tv_title.setText("学校与专业查询");
    }
    private void initFragment() {
        if (collegeFragment == null) {
            collegeFragment = new SearchHomeCollegeFragment();
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
    @Override
    protected void initData() {

    }
    @OnClick({R.id.ll_college,R.id.ll_major,R.id.iv_back})
    public void click(View view){
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        switch (view.getId()){
            case R.id.ll_college:
                tv_college.setTextColor(getResources().getColor(R.color.main_blue));
                tv_major.setTextColor(Color.parseColor("#000000"));
                view_college.setVisibility(View.VISIBLE);
                view_major.setVisibility(View.INVISIBLE);
                if (collegeFragment == null) {
                    collegeFragment=new SearchHomeCollegeFragment();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), collegeFragment);
                break;
            case R.id.ll_major:
                tv_major.setTextColor(getResources().getColor(R.color.main_blue));
                tv_college.setTextColor(Color.parseColor("#000000"));
                view_major.setVisibility(View.VISIBLE);
                view_college.setVisibility(View.INVISIBLE);
                if (majorFragment == null) {
                    majorFragment = new SearchHomeProfessionFragment();
                }
                addOrShowFragment(getSupportFragmentManager().beginTransaction(), majorFragment);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
        transaction.commit();
    }
    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_home_search);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }

}
