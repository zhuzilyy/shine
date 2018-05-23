package com.qianyi.shine.ui.account.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qianyi.shine.MainActivity;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiAccount;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.dialog.CustomLoadingDialog;
import com.qianyi.shine.ui.account.bean.LoginBean;
import com.qianyi.shine.ui.home.activity.SelectCollegeAreaActivity;
import com.qianyi.shine.utils.SPUtils;
import com.qianyi.shine.utils.SetStatusBarColor;
import com.qianyi.shine.utils.Utils;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by NEUNB on 2018/3/29.
 */

public class GuessScoreActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    private int REQUESTCODE = 101;
    private String arer;
    @BindView(R.id.tv_selectArea)
    public TextView tv_selectArea;
    @BindView(R.id.rg)
    public RadioGroup rg;
    private String type="2";
    @BindView(R.id.et_myrank)
    public EditText et_myrank;
    @BindView(R.id.et_myscore)
    public EditText et_myscore;
    @BindView(R.id.ll_wraning)
    public LinearLayout ll_wraning;
    private Intent intent;
    private String tag;
    @Override
    protected void initViews() {
        BaseActivity.addActivity(this);
        tv_title.setText("高考预估成绩");
        boolean isFirst= (boolean) SPUtils.get(GuessScoreActivity.this,"isFirst",true);
        if (isFirst){
            ll_wraning.setVisibility(View.VISIBLE);
        }else{
            ll_wraning.setVisibility(View.GONE);
        }

        intent=getIntent();
        if (intent!=null){
            tag=intent.getStringExtra("tag");
        }
    }
    @Override
    protected void initData() {

    }
    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_guess_score);

    }

    @Override
    protected void initListener() {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_wenke:
                        type = "2";
                        break;
                    case R.id.rb_like:
                        type = "1";
                        break;
                }
            }
        });
    }
    @OnClick({R.id.btn_confirm, R.id.iv_back, R.id.tv_selectArea,R.id.tv_know})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_confirm:
                String prov = tv_selectArea.getText().toString().trim();
                String myScore = et_myscore.getText().toString().trim();
                String myRank = et_myrank.getText().toString().trim();
                if(TextUtils.isEmpty(prov)){
                    Toast.makeText(this, "请选择地区", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(type)){
                    Toast.makeText(this, "请选择文理科", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(myScore)){
                    Toast.makeText(this, "请输入分数", Toast.LENGTH_SHORT).show();
                    return;
                }
                LoginBean.LoginData.LoginInfo loginInfo = Utils.readUser(this);
                String limit = loginInfo.getMember_scoreinfo().getLimit();
                if (limit.equals("0")){
                    Toast.makeText(this, "只能填写3次高考信息", Toast.LENGTH_SHORT).show();
                    return;
                }
                String id = Utils.readUser(GuessScoreActivity.this).getId();
                goToMain(id,type,prov,myScore,myRank);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_selectArea:
                Intent intent = new Intent(GuessScoreActivity.this, SelectCollegeAreaActivity.class);
                startActivityForResult(intent, REQUESTCODE);
                break;
            case R.id.tv_know:
                ll_wraning.setVisibility(View.GONE);
                SPUtils.put(GuessScoreActivity.this,"isFirst",false);
                break;
        }
    }

    /***
     * 启程
     * @param id
     * @param type
     * @param prov
     * @param myScore
     * @param myRank
     */
    private void goToMain(String id, String type, String prov, String myScore, String myRank) {
        final CustomLoadingDialog loadingDialog = new CustomLoadingDialog(GuessScoreActivity.this);
        loadingDialog.show();
        apiAccount.Launch(apiConstant.LAUNCH,id, type, prov, myScore, myRank, new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, String s) {
                loadingDialog.dismiss();
                Gson gson = new Gson();
                LoginBean loginBean = gson.fromJson(s, LoginBean.class);
                if(loginBean != null){
                    String code = loginBean .getCode();
                    if("0".equals(code)){
                       LoginBean.LoginData loginData =  loginBean.getData();
                       LoginBean.LoginData.LoginInfo loginInfo = loginData.getInfo();
                        String score = loginInfo.getMember_scoreinfo().getScore();
                        String type = loginInfo.getMember_scoreinfo().getType();
                        String level = loginInfo.getMember_scoreinfo().getLevel();
                        String area = loginInfo.getMember_scoreinfo().getProv();
                        SPUtils.put(GuessScoreActivity.this,"score",score);
                        SPUtils.put(GuessScoreActivity.this,"type",type);
                        SPUtils.put(GuessScoreActivity.this,"level",level);
                        SPUtils.put(GuessScoreActivity.this,"area",area);
                        if(loginInfo != null){
                           try{
                               Utils.clearSharedUser(GuessScoreActivity.this);
                               Utils.saveUser(loginInfo,GuessScoreActivity.this);
                               if (tag.equals("setScore")){
                                   startActivity(new Intent(GuessScoreActivity.this,MainActivity.class));
                                   finish();
                               }else if (tag.equals("intelligentFill")||tag.equals("homePage")){
                                   finish();
                               }
                           }catch (Exception e){
                               Log.i("exception",e.getMessage());
                           }
                       }
                    }else {
                        Toast.makeText(GuessScoreActivity.this, ""+loginBean.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                loadingDialog.dismiss();

            }
        });


    }

    @Override
    protected void setStatusBarColor() {
        SetStatusBarColor.setWindowStatusBarColor(this, Color.parseColor("#ffffff"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 101:
                arer = data.getStringExtra("area");
                if (!TextUtils.isEmpty(arer)) {
                    tv_selectArea.setText(arer);
                }
                break;

            default:
                break;


        }


    }
}
