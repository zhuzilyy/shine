package com.qianyi.shine.ui.home.activity;

import android.widget.GridView;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.ui.home.adapter.CollegeAdapter;
import com.qianyi.shine.ui.home.view.MyGridView;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/1.
 */

public class FindCollegeActivity extends BaseActivity {
    @BindView(R.id.gridview)
    MyGridView gridview;
    private CollegeAdapter collegeAdapter;
    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {
        collegeAdapter=new CollegeAdapter(this);
        gridview.setAdapter(collegeAdapter);
    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_find_college);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
}
