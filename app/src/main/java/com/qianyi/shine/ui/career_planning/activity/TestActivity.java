package com.qianyi.shine.ui.career_planning.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.api.apiTest;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.dialog.CustomLoadingDialog;
import com.qianyi.shine.ui.career_planning.entity.HLDBean;
import com.qianyi.shine.ui.career_planning.entity.MitBean;
import com.yanzhikai.pictureprogressbar.PictureProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

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
    private String testType;
    private List<MitBean.MitData.MitInfo.MitList> mitLists;
    private String totalNum;
    @BindView(R.id.rg_character)
    public RadioGroup rg_character;
    @BindView(R.id.rg_interest)
    public RadioGroup rg_interest;
    @BindView(R.id.tv_total)
    public TextView tv_total;
    @BindView(R.id.tv_current)
    public TextView tv_current;
    @BindView(R.id.question_content)
    public TextView question_content;
    @BindView(R.id.ra_character_a)
    public RadioButton ra_character_a;
    @BindView(R.id.ra_character_b)
    public RadioButton ra_character_b;
    public int currentNum = 0;
    @BindView(R.id.pre_question)
    public TextView pre_question;
    //用于记录性格测试答案的Stringbuilder
    public StringBuilder CharachorStringBuilder = new StringBuilder();
    //用于记录兴趣测试答案的Stringbuilder
    public StringBuilder InterestStringBuilder = new StringBuilder();
    //用于记录当前性格测试答案
    public String currentCharactorAnswer;
    //用于记录当前兴趣测试答案
    public String currentInterestAnswer;
    private List<HLDBean.HLDData.HLDInfo.HLDQuestionLIST> hldQuestion; ;

    @Override
    protected void initViews() {
        //根据testType决定是性格测试还是兴趣测试 testType  0:性格测试    1：兴趣测试
        testType = getIntent().getStringExtra("testType");
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title = findViewById(R.id.tv_title);
        title.setText("测试");
        pb.setDrawableIds(new int[]{R.drawable.i00, R.drawable.i01, R.drawable.i02, R.drawable.i03, R.drawable.i04, R.drawable.i05, R.drawable.i06});
        pb.setProgress(0);
    }

    @Override
    protected void initData() {
        if ("0".equals(testType)) {
            hideShowAnswerType(testType);
            getCharacterData();
        } else if ("1".equals(testType)) {
            hideShowAnswerType(testType);
            getInterestData();
        }
    }
    /***
     * 根据题型显示不同的选项
     * @param testType
     */
    private void hideShowAnswerType(String testType) {
        if ("0".equals(testType)) {
            rg_character.setVisibility(View.VISIBLE);
            rg_interest.setVisibility(View.GONE);
        } else if ("1".equals(testType)) {
            rg_character.setVisibility(View.GONE);
            rg_interest.setVisibility(View.VISIBLE);
        }
    }

    /***
     * 获取兴趣测试数据
     */
    private void getInterestData() {
        final CustomLoadingDialog loadingDialog = new CustomLoadingDialog(TestActivity.this);
        loadingDialog.show();
        apiTest.getHLD_TestData(apiConstant.GETHLDINTERESTDDATA, new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, String s) {
                loadingDialog.dismiss();
                Gson gson =new Gson();
                HLDBean hldBean = gson.fromJson(s, HLDBean.class);
                if(hldBean!=null){
                   String code = hldBean.getCode();
                   if("0" .equals(code)){
                      HLDBean.HLDData hldData = hldBean.getData();
                      if(hldData!=null){
                          HLDBean.HLDData.HLDInfo hldInfo = hldData.getInfo();
                          if(hldInfo != null){
                              hldQuestion = hldInfo.getQuestion_list();
                              totalNum = hldInfo.getListtotal();
                              pb.setMax(Integer.parseInt(totalNum));
                              initHLDQuestionOne(hldQuestion.get(0));
                          }
                      }
                   }
                }
            }

            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                Log.i("yui",e.getMessage());
                loadingDialog.dismiss();
            }
        });
    }

    /***
     * 初始化兴趣测试
     * @param item
     */
    private void initHLDQuestionOne(HLDBean.HLDData.HLDInfo.HLDQuestionLIST item) {
        tv_total.setText(totalNum);
        tv_current.setText(item.getOrder());
        pb.setProgress(Integer.parseInt(item.getOrder()));
        question_content.setText(item.getQuestion());

    }

    /***
     * 获取性格测试数据
     */
    private void getCharacterData() {
        final CustomLoadingDialog loadingDialog = new CustomLoadingDialog(TestActivity.this);
        loadingDialog.show();
        apiTest.getMIT_TestData(apiConstant.GETMBTDATA, new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, String s) {
                loadingDialog.dismiss();
                Gson gson = new Gson();
                MitBean mitBean = gson.fromJson(s, MitBean.class);
                if (mitBean != null) {
                    String code = mitBean.getCode();
                    if ("0".equals(code)) {
                        MitBean.MitData mitData = mitBean.getData();
                        if (mitData != null) {
                            MitBean.MitData.MitInfo mitInfo = mitData.getInfo();
                            if (mitInfo != null) {
                                totalNum = mitInfo.getListtotal();
                                pb.setMax(Integer.parseInt(totalNum));
                                mitLists = mitInfo.getQuestion_list();
                                initQuestionOne(mitLists.get(0));
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
    /***
     * 获取性格测试数据后，显示题目详情
     * @param item
     */
    private void initQuestionOne(MitBean.MitData.MitInfo.MitList item) {
        tv_total.setText(totalNum);
        tv_current.setText(item.getOrder());
        pb.setProgress(Integer.parseInt(item.getOrder()));
        question_content.setText(item.getQuetion());

        ra_character_a.setText("A. " + item.getKey().getA());
        ra_character_b.setText("B. " + item.getKey().getB());
//      ra_character_a.setChecked(false);
//      ra_character_b.setChecked(false);


    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_test);

    }

    @Override
    protected void initListener() {
        //性格测试
        rg_character.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.ra_character_a:
                        currentCharactorAnswer = "A";
                        break;
                    case R.id.ra_character_b:
                        currentCharactorAnswer = "B";
                        break;

                    default:
                        break;
                }
            }
        });
        //兴趣测试
        rg_interest.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.ra_interest_a:
                        currentInterestAnswer = "1";
                        break;
                    case R.id.ra_interest_b:
                        currentInterestAnswer = "0";
                        break;

                    default:
                        break;


                }
            }
        });

    }

    @Override
    protected void setStatusBarColor() {

    }

    @OnClick({R.id.next_question_btn, R.id.pre_question})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_question_btn:
                if ("0".equals(testType)) {
                    doCharactor();
                } else if ("1".equals(testType)) {
                    doInterest();

                }
                break;
            case R.id.pre_question:

                if ("0".equals(testType)) {
                    if (currentNum > 0) {
                        currentNum--;
                        initQuestionOne(mitLists.get(currentNum));
                        //销毁当前题的答案
                        if(CharachorStringBuilder.toString().length()>=2){
                            CharachorStringBuilder.delete(CharachorStringBuilder.length()-2,CharachorStringBuilder.length());
                        }
                    }
                } else if ("1".equals(testType)) {
                    if (currentNum > 0) {
                        currentNum--;
                        initHLDQuestionOne(hldQuestion.get(currentNum));
                        //销毁当前题的答案
                        if(InterestStringBuilder.toString().length()>=2){
                            InterestStringBuilder.delete(InterestStringBuilder.length()-2,InterestStringBuilder.length());
                        }
                    }

                }


                break;

            default:
                break;
        }

    }

    /***
     * 处理兴趣测试结果
     */
    private void doInterest() {
        //记录答案
        if(TextUtils.isEmpty(currentInterestAnswer)){
            InterestStringBuilder.append(""+",");
        }else {
            InterestStringBuilder.append(currentInterestAnswer+",");
        }
        if (currentNum != Integer.parseInt(totalNum) - 1) {
            currentNum++;
            initHLDQuestionOne(hldQuestion.get(currentNum));
        } else {
            Intent intent =new Intent(TestActivity.this, InterestResultActivity.class);
            intent.putExtra("InterestResult",InterestStringBuilder.toString());
            startActivity(intent);
            finish();
        }
    }

    /****
     * 处理性格测试
     */
    private void doCharactor() {
        //记录答案
        if(TextUtils.isEmpty(currentCharactorAnswer)){
            CharachorStringBuilder.append(""+",");
        }else {
            CharachorStringBuilder.append(currentCharactorAnswer+",");
        }
        if (currentNum != Integer.parseInt(totalNum) - 1) {
            currentNum++;
            initQuestionOne(mitLists.get(currentNum));
        } else {
            Intent intent =new Intent(TestActivity.this, CharacterResultActivity.class);
            intent.putExtra("CharatorResult",CharachorStringBuilder.toString());
            startActivity(intent);
            finish();
        }
    }


}
