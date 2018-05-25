package com.qianyi.shine.ui.career_planning.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiHome;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.ui.career_planning.adapter.TestAdapter;
import com.qianyi.shine.ui.career_planning.entity.SuitableForMeEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/2.
 */

public class SuitableForMyProfessionActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.iv_back)
    public ImageView back;
    @BindView(R.id.tv_title)
    public TextView title;
    @BindView(R.id.text_Interest)
    public LinearLayout text_Interest;
    @BindView(R.id.text_Character)
    public LinearLayout text_Character;

    @Override
    protected void initViews() {
        BaseActivity.addActivity(this);
        title.setText("适合我的职业");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    protected void initData() {
    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_suitableme);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }

    @OnClick({R.id.text_Interest,R.id.text_Character})
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.text_Interest:
                startActivity(new Intent(SuitableForMyProfessionActivity.this,TestInterestActivity.class));
            break;
            case R.id.text_Character:
                startActivity(new Intent(SuitableForMyProfessionActivity.this,TestCharacterActivity.class));
                break;

            default:
            break;


        }
    }
}
