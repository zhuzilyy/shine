package com.qianyi.shine.ui.home.activity;

import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.ui.home.adapter.HomeSearchCollegeAdapter;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/10.
 */

public class AcceptanceRateActivity extends BaseActivity {
    @BindView(R.id.lv_college)
    ListView lv_college;
    @BindView(R.id.et_search)
    EditText et_search;
    private HomeSearchCollegeAdapter homeSearchCollegeAdapter;
    @Override
    protected void initViews() {

    }
    @Override
    protected void initData() {
        homeSearchCollegeAdapter=new HomeSearchCollegeAdapter(this);
        lv_college.setAdapter(homeSearchCollegeAdapter);
    }
    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_acceptance_rate);
    }

    @Override
    protected void initListener() {

    }
    @Override
    protected void setStatusBarColor() {
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                lv_college.setVisibility(View.VISIBLE);
                return false;
            }
        });
        //条目点击事件
        lv_college.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                jumpActivity(AcceptanceRateActivity.this,PriorityCollegeDetailsActivity.class);
            }
        });
    }
}
