package com.qianyi.shine.ui.career_planning.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.ui.career_planning.view.radarview.RadarData;
import com.qianyi.shine.ui.career_planning.view.radarview.RadarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/17.
 */

public class InterestResultActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.radarView)
    public RadarView radarView;
    @BindView(R.id.iv_back)
    public ImageView back;
    @BindView(R.id.tv_title)
    public TextView title;
    @BindView(R.id.TestAgain_ll)
    public LinearLayout TestAgain_ll;
    @Override
    protected void initViews() {
        //玫瑰风向图
        List<RadarData> dataList = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            RadarData data = new RadarData("标题" + i, i * 11);
            dataList.add(data);
        }
        radarView.setDataList(dataList);
        title.setText("兴趣");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_interest_result);

    }

    @Override
    protected void initListener() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.TestAgain_ll})
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.TestAgain_ll:
                startActivity(new Intent(InterestResultActivity.this,SuitableForMyProfessionActivity.class));
            break;

            default:
            break;


        }

    }
}
