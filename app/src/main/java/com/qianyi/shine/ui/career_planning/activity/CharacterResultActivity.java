package com.qianyi.shine.ui.career_planning.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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
    @BindView(R.id.zzgxList)
    public MyListView zzgxList;
    //领导模式
    @BindView(R.id.leaderList)
    public MyListView leaderList;
    //学习模式
    @BindView(R.id.LearnList)
    public MyListView LearnList;
    //倾向性顺序
    @BindView(R.id.tendTv)
    public TextView tendTv;
    //解决问题的模式
    @BindView(R.id.questionList)
    public MyListView questionList;
    //工作环境倾向性
    @BindView(R.id.workingList)
    public MyListView workingList;
    //潜在的缺点
    @BindView(R.id.potentialList)
    public MyListView potentialList;
    //发展建议
    @BindView(R.id.devilopmentList)
    public MyListView devilopmentList;
    private String keyStrings;

    private TextMySmpleAdapter mySmpleAdapter ;
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
                    Gson gson = new Gson();
                    CharacterResultBean resultBean = gson.fromJson(s, CharacterResultBean.class);
                    if(resultBean!=null){
                        String code = resultBean.getCode();
                        if("0".equals(code)){
                            CharacterResultBean.CharacterResultData resultData =resultBean.getData();
                            if(resultData!=null){
                               CharacterResultBean.CharacterResultData.CharacterResultInfo resultInfo = resultData.getInfo();
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
                    Log.i("()",e.getMessage());
                }
            });
        }

    }

    /***
     * 赋值数据
     * @param info
     */
    private void InitDataFromWeb(CharacterResultBean.CharacterResultData.CharacterResultInfo info) {
        tv_ABC.setText(info.getType());
        tv_introduce.setText(info.getDescription());
        tv_result.setText(info.getType());
        tv_keywords2.setText(info.getDescription());

        //对组织的贡献
        TextMySmpleAdapter adapter_zzgx =new TextMySmpleAdapter(CharacterResultActivity.this,getListString(info.getZzgx().getValue()),1);
        zzgxList.setAdapter(adapter_zzgx);
        //领导模式
        TextMySmpleAdapter adapter_llm  =new TextMySmpleAdapter(CharacterResultActivity.this,getListString(info.getLdms().getValue()),1);
        leaderList.setAdapter(adapter_llm);
        //学习模式
        TextMySmpleAdapter adapter_learn  =new TextMySmpleAdapter(CharacterResultActivity.this,getListString(info.getXxms().getValue()),1);
        LearnList.setAdapter(adapter_learn);
        //倾向性顺序
        String tentStr = info.getXxms().getExtend().getValue();
        if(!TextUtils.isEmpty(tentStr)){
            tendTv.setText(tentStr);
        }
        //解决问题
        TextMySmpleAdapter adapter_question  =new TextMySmpleAdapter(CharacterResultActivity.this,getListString(info.getJjms().getValue()),1);
        questionList.setAdapter(adapter_question);
        //工作环境
        TextMySmpleAdapter adapter_working  =new TextMySmpleAdapter(CharacterResultActivity.this,getListString(info.getGzhj().getValue()),1);
        workingList.setAdapter(adapter_working);
        //潜在的缺点
        TextMySmpleAdapter adapter_potentail  =new TextMySmpleAdapter(CharacterResultActivity.this,getListString(info.getQzqd().getValue()),1);
        potentialList.setAdapter(adapter_potentail);
        //发展建议
        TextMySmpleAdapter adapter_development  =new TextMySmpleAdapter(CharacterResultActivity.this,getListString(info.getQzqd().getValue()),1);
        devilopmentList.setAdapter(adapter_development);



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
