package com.qianyi.shine.ui.career_planning.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;
import com.yanzhikai.pictureprogressbar.PictureProgressBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/17.
 */

public class TestActivity extends BaseActivity implements View.OnClickListener {
    private ImageView back;
    private TextView title;
    @BindView(R.id.pb)
    public PictureProgressBar pb;
    @BindView(R.id.next_question_btn)
    public Button next_question_btn;

    @Override
    protected void initViews() {
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title = findViewById(R.id.tv_title);
        title.setText("测试");
        pb.setDrawableIds(new int[]{R.drawable.i00, R.drawable.i01, R.drawable.i02, R.drawable.i03, R.drawable.i04, R.drawable.i05, R.drawable.i06});
        pb.setProgress(30);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_test);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.next_question_btn})
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.next_question_btn:
                startActivity(new Intent(TestActivity.this,InterestResultActivity.class));
            break;

            default:
            break;


        }

    }
}
