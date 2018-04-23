package com.qianyi.shine.ui.career_planning.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qianyi.shine.R;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.api.apiTest;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.dialog.CustomLoadingDialog;
import com.qianyi.shine.ui.account.bean.LoginBean;
import com.qianyi.shine.ui.career_planning.view.radarview.RadarData;
import com.qianyi.shine.ui.career_planning.view.radarview.RadarView;
import com.qianyi.shine.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/17.
 */

public class CharacterResultActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_back)
    public ImageView back;
    @BindView(R.id.tv_title)
    public TextView title;
    @BindView(R.id.TestAgain_ll)
    public LinearLayout TestAgain_ll;
    //****************
    //
    @BindView(R.id.tv_ABC)
    public TextView tv_ABC;
    @BindView(R.id.tv_keywords)
    public TextView tv_keywords;
    @BindView(R.id.tv_introduce)
    public TextView tv_introduce;
    @BindView(R.id.tv_result)
    public TextView tv_result;
    @BindView(R.id.tv_keywords2)
    public TextView tv_keywords2;
    //对组织的贡献
    @BindView(R.id.tv_Contribution_organization)
    public TextView tv_Contribution_organization;
    //领导模式
    @BindView(R.id.tv_LeadershipModel)
    public TextView tv_LeadershipModel;
    //学习模式
    @BindView(R.id.tv_LearningMode)
    public TextView tv_LearningMode;
    //倾向性顺序
    @BindView(R.id.tv_TendentiousSequence)
    public TextView tv_TendentiousSequence;
    //解决问题的模式
    @BindView(R.id.tv_problemSolving)
    public TextView tv_problemSolving;
    //工作环境倾向性
    @BindView(R.id.tv_workingEnvironment)
    public TextView tv_workingEnvironment;
    //潜在的缺点
    @BindView(R.id.tv_PotentialDefect)
    public TextView tv_PotentialDefect;
    //发展建议
    @BindView(R.id.tv_DevelopmentProposals)
    public TextView tv_DevelopmentProposals;
    private String keyStrings;
    @Override
    protected void initViews() {


        title.setText("性格");
        keyStrings = getIntent().getStringExtra("CharatorResult");
        Log.i("123456",keyStrings);
    }

    @Override
    protected void initData() {
        final CustomLoadingDialog loadingDialog = new CustomLoadingDialog(CharacterResultActivity.this);
        loadingDialog.show();
        LoginBean.LoginData.LoginInfo user=Utils.readUser(CharacterResultActivity.this);
        if(user!=null){
            apiTest.getMITResult(apiConstant.GETMBTRESULT, user.getId(), keyStrings, new RequestCallBack<String>() {
                @Override
                public void onSuccess(Call call, Response response, String s) {
                    loadingDialog.dismiss();
                    Log.i("()",s);
                }

                @Override
                public void onEror(Call call, int statusCode, Exception e) {
                    loadingDialog.dismiss();
                    Log.i("()",e.getMessage());
                }
            });
        }




    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_character_result);

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
                startActivity(new Intent(CharacterResultActivity.this,SuitableForMyProfessionActivity.class));
            break;

            default:
            break;


        }

    }
}
