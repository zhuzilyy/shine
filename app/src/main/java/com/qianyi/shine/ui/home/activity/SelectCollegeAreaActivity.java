package com.qianyi.shine.ui.home.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.ui.home.adapter.SelectAreaAdapter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/8.
 */

public class SelectCollegeAreaActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.gv_area)
    GridView gv_area;
    private SelectAreaAdapter selectAreaAdapter;
    private String[] areas={"全国","北京市","天津市","上海市","重庆市","河北省","山西省","辽宁省","吉林省","黑龙江省",
            "江苏省","浙江省","安徽省","福建省","江西省","山东省","河南省","湖北省","湖南省","广东省","海南省","四川省","贵州省",
            "云南省","陕西省","甘肃省","青海省","内蒙古","广西","宁夏","新疆",};
    @Override
    protected void initViews() {
        tv_title.setText("选择省份");
        selectAreaAdapter=new SelectAreaAdapter(areas,SelectCollegeAreaActivity.this);
        gv_area.setAdapter(selectAreaAdapter);
        gv_area.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String area = areas[i];
                Intent intent=new Intent();
                intent.putExtra("area",area);
                setResult(1,intent);
                finish();
            }
        });
    }
    @Override
    protected void initData() {

    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_select_college_area);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.iv_back})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
