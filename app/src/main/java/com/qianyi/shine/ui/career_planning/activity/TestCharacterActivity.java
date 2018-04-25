package com.qianyi.shine.ui.career_planning.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/17.
 */

public class TestCharacterActivity extends BaseActivity implements View.OnClickListener {
    private ImageView back;
    private TextView title;
    @BindView(R.id.btn_startTest)
    public Button btn_start;
    @BindView(R.id.tv_introduce)
    public TextView tv_intrduce;
    @Override
    protected void initViews() {
        title = findViewById(R.id.tv_title);
        title.setText("性格");
        tv_intrduce.setText("依据MBTI相关理论，人与世界互动的方式主要分为四个维度，每个维度又分为不相关的两类，它们分别是:" +
                "精力energy：外向Extravert，内向Introvert；" +
                "感知perception：感觉sensing，感觉iNtution;" +
                "生活方式lifestyle:Judging，Perceiving；" +
                "判断judgement：思考Thinking，情感Feeling" +
                "*其中加粗字体为简写，即EISNJPTF八大方面。");

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_textcharacter);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.iv_back,R.id.btn_startTest})
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_back:
                finish();
            break;
            case R.id.btn_startTest:
                Intent intent = new Intent(TestCharacterActivity.this,TestActivity.class);
                //testType  0:性格测试    1：兴趣测试
                intent.putExtra("testType","0");
                startActivity(intent);
                finish();
                break;

            default:
            break;


        }
    }


}
