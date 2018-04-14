package com.qianyi.shine.ui.home.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

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

/**
 * Created by Administrator on 2018/4/7.
 */

public class OccupationDetailActivity extends BaseActivity {
    @BindView(R.id.tablayout)
    public TabLayout tab;
    @BindView(R.id.view_pager)
    public ViewPager viewPager;
    @BindView(R.id.titile)
    public XTitleView title;

    public MyPageAdapter myPageAdapter;

    @Override
    protected void initViews() {
        BaseActivity.addActivity(this);
        title.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
}
