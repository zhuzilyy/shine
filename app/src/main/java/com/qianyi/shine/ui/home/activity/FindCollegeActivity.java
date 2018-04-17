package com.qianyi.shine.ui.home.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.ui.home.adapter.CollegeAdapter;
import com.qianyi.shine.ui.home.view.MyGridView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/1.
 */

public class FindCollegeActivity extends BaseActivity {
    @BindView(R.id.gridview)
    MyGridView gridview;
    @BindView(R.id.et_searchCollege)
    EditText et_searchCollege;
    private CollegeAdapter collegeAdapter;
    @Override
    protected void initViews() {
        BaseActivity.addActivity(this);
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
        et_searchCollege.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                String keyWords=et_searchCollege.getText().toString().trim();
                if (!TextUtils.isEmpty(keyWords)){
                    jumpActivity(FindCollegeActivity.this,CollegeListActivity.class);
                }
                return false;
            }
        });
        //条目点击事件
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(FindCollegeActivity.this,CollegeListActivity.class));
            }
        });
    }
    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.iv_back,R.id.ll_allCollege,R.id.ll_collegeRank,R.id.ll_benSheng,R.id.ll_beiShangGaung,R.id.ll_quanGuo,
            R.id.ll_yiBen,R.id.ll_erBen,R.id.ll_sanBen,R.id.ll_zhuanke,R.id.ll_985,R.id.ll_211,R.id.ll_zhuoYue,R.id.ll_shengZhongDian})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            //全部大学
            case R.id.ll_allCollege:
                startActivity(new Intent(FindCollegeActivity.this,CollegeListActivity.class));
                break;
            //大学排名
            case R.id.ll_collegeRank:
                startActivity(new Intent(FindCollegeActivity.this,CollegeListActivity.class));
                break;
            //本省
            case R.id.ll_benSheng:
                startActivity(new Intent(FindCollegeActivity.this,CollegeListActivity.class));
                break;
            //北上广
            case R.id.ll_beiShangGaung:
                startActivity(new Intent(FindCollegeActivity.this,CollegeListActivity.class));
                break;
            //全国
            case R.id.ll_quanGuo:
                startActivity(new Intent(FindCollegeActivity.this,CollegeListActivity.class));
                break;
            //一本
            case R.id.ll_yiBen:
                startActivity(new Intent(FindCollegeActivity.this,CollegeListActivity.class));
                break;
            //二本
            case R.id.ll_erBen:
                startActivity(new Intent(FindCollegeActivity.this,CollegeListActivity.class));
                break;
            //三本
            case R.id.ll_sanBen:
                startActivity(new Intent(FindCollegeActivity.this,CollegeListActivity.class));
                break;
            //专科
            case R.id.ll_zhuanke:
                startActivity(new Intent(FindCollegeActivity.this,CollegeListActivity.class));
                break;
            //985
            case R.id.ll_985:
                startActivity(new Intent(FindCollegeActivity.this,CollegeListActivity.class));
                break;
            //211
            case R.id.ll_211:
                startActivity(new Intent(FindCollegeActivity.this,CollegeListActivity.class));
                break;
            //卓越
            case R.id.ll_zhuoYue:
                startActivity(new Intent(FindCollegeActivity.this,CollegeListActivity.class));
                break;
            //省重点
            case R.id.ll_shengZhongDian:
                startActivity(new Intent(FindCollegeActivity.this,CollegeListActivity.class));
                break;


        }
    }
}
