package com.qianyi.shine.ui.career_planning.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.util.TouchEventUtil;
import com.google.gson.Gson;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.api.apiTest;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.dialog.CustomLoadingDialog;
import com.qianyi.shine.ui.account.bean.LoginBean;
import com.qianyi.shine.ui.career_planning.adapter.TextMySmpleAdapter;
import com.qianyi.shine.ui.career_planning.entity.CharacterResultBean;
import com.qianyi.shine.ui.career_planning.entity.InterestResultBean;
import com.qianyi.shine.ui.career_planning.view.radarview.RadarData;
import com.qianyi.shine.ui.career_planning.view.radarview.RadarView;
import com.qianyi.shine.ui.home.view.MyListView;
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

public class InterestResultActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_back)
    public ImageView back;
    @BindView(R.id.tv_title)
    public TextView title;
    @BindView(R.id.TestAgain_ll)
    public LinearLayout TestAgain_ll;
    private String keyStrings;

    //************************
    @BindView(R.id.tv_type)
    public TextView tv_type;
    @BindView(R.id.tv_majorInterest)
    public TextView tv_majorInterest;
    @BindView(R.id.tv_color)
    public TextView tv_color;
    @BindView(R.id.tv_type2)
    public TextView tv_type2;
    @BindView(R.id.tv_type3)
    public TextView tv_type3;
    @BindView(R.id.tv_color2)
    public TextView tv_color2;
    @BindView(R.id.tv_type4)
    public TextView tv_type4;
    @BindView(R.id.tv_title01)
    public TextView tv_title01;
    @BindView(R.id.tv_title02)
    public TextView tv_title02;
    @BindView(R.id.tv_title03)
    public TextView tv_title03;
    @BindView(R.id.intersetList)
    public MyListView InterestLv;

    @BindView(R.id.tv_content02)
    public TextView tv_content02;
    @BindView(R.id.tv_content03)
    public TextView tv_content03;




    @Override
    protected void initViews() {
        title.setText("兴趣");
        keyStrings= getIntent().getStringExtra("InterestResult");
        Log.i("123456",keyStrings);

    }

    @Override
    protected void initData() {
        final CustomLoadingDialog loadingDialog = new CustomLoadingDialog(InterestResultActivity.this);
        loadingDialog.show();
        LoginBean.LoginData.LoginInfo user= Utils.readUser(InterestResultActivity.this);
        if(user!=null){
            apiTest.getHLDResult(apiConstant.GETHLDRESULT, user.getId(), keyStrings, new RequestCallBack<String>() {
                @Override
                public void onSuccess(Call call, Response response, String s) {
                    Log.i("tag",s);
                    loadingDialog.dismiss();
                    Gson gson = new Gson();
                    InterestResultBean resultBean = gson.fromJson(s, InterestResultBean.class);
                    if(resultBean!=null){
                        String code = resultBean.getCode();
                        if("0".equals(code)){
                            InterestResultBean.InterestResultData resultData =resultBean.getData();
                            if(resultData!=null){
                                InterestResultBean.InterestResultData.InterestResultInfo resultInfo = resultData.getInfo();
                                if(resultInfo !=null){
                                    InitDataFromWeb(resultInfo);

                                }
                            }
                        }
                    }
                }

                @Override
                public void onEror(Call call, int statusCode, Exception e) {
                    loadingDialog.dismiss();
                }
            });
        }


    }
    /***
     * 赋值数据
     * @param info
     */
    private void InitDataFromWeb(InterestResultBean.InterestResultData.InterestResultInfo info) {

        if(info == null){
            return;
        }

        tv_type.setText(info.getType());
        tv_majorInterest.setText("主导兴趣为： "+info.getDecription());
        tv_color.setText(info.getColor().getName()+" : "+info.getColor().getValue());
        tv_type2.setText(info.getType());
        tv_type3.setText(info.getDecription());
        tv_color2.setText(info.getColor().getName()+" : "+info.getColor().getValue());
        tv_type4.setText(info.getDecription());

            tv_title01.setText(info.getInteresting().getName());

        TextMySmpleAdapter adapter_zzgx =new TextMySmpleAdapter(InterestResultActivity.this,info.getInteresting().getValue(),0);
        InterestLv.setAdapter(adapter_zzgx);

        tv_title02.setText(info.getZyfw().getName());
        tv_content02.setText(info.getZyfw().getValue());
        tv_title03.setText(info.getJtzy().getName());
        tv_content03.setText(info.getJtzy().getValue());





    }

    /***
     * 返回List<Strng>
     * @param value
     * @return
     */
    private List<String> getListString(List<String> value) {
        List<String> ListStr=new ArrayList<>();
        String str = value.get(0);
        String[] strings = str.split("Ø");
        for (int i = 0; i< strings.length;i++){
            ListStr.add(strings[i]);
        }
        ListStr.remove(0);
        return ListStr;

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
