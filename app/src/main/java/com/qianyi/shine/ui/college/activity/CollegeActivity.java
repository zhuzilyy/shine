package com.qianyi.shine.ui.college.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.api.apiHome;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.dialog.CustomLoadingDialog;
import com.qianyi.shine.fragment.HomeFragment;
import com.qianyi.shine.ui.account.activity.FindPwdActiviy;
import com.qianyi.shine.ui.account.bean.LoginBean;
import com.qianyi.shine.ui.college.fragments.collegeEmploymentProspectsFragment;
import com.qianyi.shine.ui.college.fragments.collegeIntroductionFragment;
import com.qianyi.shine.ui.college.fragments.collegeProfessionalSettingsFragment;
import com.qianyi.shine.ui.college.fragments.collegeScoreFragment;
import com.qianyi.shine.ui.home.bean.CollegeDetailsBean;
import com.qianyi.shine.utils.SPUtils;
import com.qianyi.shine.utils.ToastUtils;
import com.qianyi.shine.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/3/30.
 */

public class CollegeActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.college_Introduction)
    public RelativeLayout college_Introduction;
    @BindView(R.id.college_ProfessionalSettings)
    public RelativeLayout college_ProfessionalSettings;
    @BindView(R.id.college_Score)
    public RelativeLayout college_Score;
    @BindView(R.id.college_EmploymentProspects)
    public RelativeLayout college_EmploymentProspects;
    @BindView(R.id.indicator01)
    public View Indicator01;
    @BindView(R.id.indicator02)
    public View Indicator02;
    @BindView(R.id.indicator03)
    public View Indicator03;
    @BindView(R.id.indicator04)
    public View Indicator04;
    private Fragment currentFra=new Fragment();
    private collegeIntroductionFragment introductionFragment;
    private collegeProfessionalSettingsFragment professionalSettingsFragment;
    private collegeScoreFragment scoreFragment;
    private collegeEmploymentProspectsFragment employmentProspectsFragment;
    private FragmentManager fragmentManager;
    @BindView(R.id.collete_tv01)
    public TextView collete_tv01;
    @BindView(R.id.collete_tv02)
    public TextView collete_tv02;
    @BindView(R.id.collete_tv03)
    public TextView collete_tv03;
    @BindView(R.id.collete_tv04)
    public TextView collete_tv04;
    @BindView(R.id.tv_tag)
    public TextView tv_tag;
    @BindView(R.id.back)
    public ImageView back;
    private String universityName,memberId;
    public static String  collegeId;
    //==================================================
    @BindView(R.id.college_logo) public RoundedImageView college_logo;
    @BindView(R.id.collegeName) public TextView collegeName;
    @BindView(R.id.collegeOrder) public TextView collegeOrder;
    private CustomLoadingDialog customLoadingDialog;
    @Override
    protected void initViews() {
        BaseActivity.addActivity(this);
        collegeId=getIntent().getStringExtra("id");
        universityName=getIntent().getStringExtra("name");
        LoginBean.LoginData.LoginInfo loginInfo = Utils.readUser(this);
        memberId= loginInfo.getId();
        setStatusColor();
        ButterKnife.bind(this);
        fragmentManager=getSupportFragmentManager();
        introductionFragment=new collegeIntroductionFragment();
        FragmentTransaction ft=fragmentManager.beginTransaction();
        AddOrShowFra(ft,introductionFragment);
        customLoadingDialog=new CustomLoadingDialog(this);

    }

    @Override
    protected void initData() {
            if(!TextUtils.isEmpty(collegeId)){
                getCollegeData(collegeId);
            }
    }

    /***
     * 获取大学详情数据
     * @param collegeId
     */
    private void getCollegeData(String collegeId) {
        final CustomLoadingDialog loadingDialog = new CustomLoadingDialog(CollegeActivity.this);
        loadingDialog.show();
        apiHome.getCollegeData(apiConstant.COLLEGE_DETAILS, collegeId, new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("s",s);
                        loadingDialog.dismiss();
                        Gson gson = new Gson();
                        CollegeDetailsBean detailsBean = gson.fromJson(s, CollegeDetailsBean.class);
                        if(detailsBean!=null){
                            String code = detailsBean.getCode();
                            if("0".equals(code)){
                                CollegeDetailsBean.CollegeDetailsData detailsData = detailsBean.getData();
                                if(detailsData!=null){
                                    CollegeDetailsBean.CollegeDetailsData.CollegeDetailsInfo detailsInfo = detailsData.getInfo();
                                    if(detailsInfo!=null){
                                        //赋值界面数据

                                        String wmzyId=detailsInfo.getWmzy_school_id();
                                        String is_211 = detailsInfo.getIs_211();
                                        String is_985 = detailsInfo.getIs_985();
                                        String level_211;
                                        String level_985;
                                        if (is_211.equals("1")){
                                            level_211="211";
                                        }else{
                                            level_211="非211";
                                        }
                                        if (is_985.equals("1")){
                                            level_985="985";
                                        }else{
                                            level_985="非985";
                                        }
                                        tv_tag.setText(level_211+" "+level_985);
                                        SPUtils.put(CollegeActivity.this,"wmzyId",wmzyId);
                                        setCollegeData(detailsInfo);
                                        introductionFragment.setCollegeDataFromRoot(detailsInfo);
                                        Intent intent=new Intent();
                                        intent.setAction("com.action.introduce.success");
                                        sendBroadcast(intent);

                                    }
                                }
                            }
                        }
                    }
                });
            }
            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismiss();
                    }
                });
                Log.i("tt",e.getMessage());
            }
        });
    }

    /***
     * 赋值当前界面数据
     * @param info
     */
    private void setCollegeData(CollegeDetailsBean.CollegeDetailsData.CollegeDetailsInfo info) {
        //logo
        Glide.with(CollegeActivity.this).load(info.getLogo()).into(college_logo);
        //大学名称
        collegeName.setText(info.getName());
        //是否是985
        if("1".equals(info.getIs_985())){

        }
        //综合排名
        collegeOrder.setText(info.getRank());



    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_college);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }

    @OnClick({R.id.college_Introduction, R.id.college_ProfessionalSettings, R.id.college_Score,
            R.id.college_EmploymentProspects,R.id.back,R.id.tv_addFocus})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.college_Introduction:
                showIndicator(0);
                if(introductionFragment==null){
                    introductionFragment=new collegeIntroductionFragment();
                }
                FragmentTransaction ft_introduce=fragmentManager.beginTransaction();
                AddOrShowFra(ft_introduce,introductionFragment);
                break;
            case R.id.college_ProfessionalSettings:
                showIndicator(1);
                if(professionalSettingsFragment==null){
                    professionalSettingsFragment=new collegeProfessionalSettingsFragment();
                }
                FragmentTransaction ft_professionalSettings=fragmentManager.beginTransaction();
                AddOrShowFra(ft_professionalSettings,professionalSettingsFragment);
                break;
            case R.id.college_Score:
                showIndicator(2);
                if(scoreFragment==null){
                    scoreFragment=new collegeScoreFragment();
                }
                FragmentTransaction ft_score=fragmentManager.beginTransaction();
                AddOrShowFra(ft_score,scoreFragment);
                break;
            case R.id.college_EmploymentProspects:
                showIndicator(3);
                if(employmentProspectsFragment==null){
                    employmentProspectsFragment=new collegeEmploymentProspectsFragment();
                }
                FragmentTransaction ft_employmentProspects=fragmentManager.beginTransaction();
                AddOrShowFra(ft_employmentProspects,employmentProspectsFragment);
                break;
            case R.id.tv_addFocus:
                focusCollege();
                break;
        }
    }
    //关注学校
    private void focusCollege() {
        customLoadingDialog.show();
        apiHome.focusCollege(apiConstant.FOCUS_COLLEGE, memberId, collegeId, universityName, new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                Log.i("tag",s);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        customLoadingDialog.dismiss();
                        try {
                            JSONObject jsonObject=new JSONObject(s);
                            String info = jsonObject.getString("info");
                            Toast.makeText(CollegeActivity.this, info, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                        customLoadingDialog.dismiss();
                Toast.makeText(CollegeActivity.this, "网络错误关注失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /***
     * 显示隐藏Fragment
     *
     * @param ft
     * @param Fra
     */
    private void AddOrShowFra(FragmentTransaction ft, Fragment Fra) {
        if (currentFra == Fra) {
            return;
        }
        if (!Fra.isAdded()) {
            ft.hide(currentFra).add(R.id.college_switLayout, Fra).commit();

        } else {
            ft.hide(currentFra).show(Fra).commit();

        }
        currentFra = Fra;


    }


    /***
     * 显示指示器
     */
    private void showIndicator(int index) {
        switch (index) {
            case 0:
                Indicator01.setVisibility(View.VISIBLE);
                Indicator02.setVisibility(View.INVISIBLE);
                Indicator03.setVisibility(View.INVISIBLE);
                Indicator04.setVisibility(View.INVISIBLE);
                collete_tv01.setTextColor(getResources().getColor(R.color.main_blue));
                collete_tv02.setTextColor(getResources().getColor(R.color.black));
                collete_tv03.setTextColor(getResources().getColor(R.color.black));
                collete_tv04.setTextColor(getResources().getColor(R.color.black));

                break;
            case 1:
                Indicator01.setVisibility(View.INVISIBLE);
                Indicator02.setVisibility(View.VISIBLE);
                Indicator03.setVisibility(View.INVISIBLE);
                Indicator04.setVisibility(View.INVISIBLE);

                collete_tv01.setTextColor(getResources().getColor(R.color.black));
                collete_tv02.setTextColor(getResources().getColor(R.color.main_blue));
                collete_tv03.setTextColor(getResources().getColor(R.color.black));
                collete_tv04.setTextColor(getResources().getColor(R.color.black));

                break;
            case 2:
                Indicator01.setVisibility(View.INVISIBLE);
                Indicator02.setVisibility(View.INVISIBLE);
                Indicator03.setVisibility(View.VISIBLE);
                Indicator04.setVisibility(View.INVISIBLE);

                collete_tv01.setTextColor(getResources().getColor(R.color.black));
                collete_tv02.setTextColor(getResources().getColor(R.color.black));
                collete_tv03.setTextColor(getResources().getColor(R.color.main_blue));
                collete_tv04.setTextColor(getResources().getColor(R.color.black));

                break;
            case 3:
                Indicator01.setVisibility(View.INVISIBLE);
                Indicator02.setVisibility(View.INVISIBLE);
                Indicator03.setVisibility(View.INVISIBLE);
                Indicator04.setVisibility(View.VISIBLE);

                collete_tv01.setTextColor(getResources().getColor(R.color.black));
                collete_tv02.setTextColor(getResources().getColor(R.color.black));
                collete_tv03.setTextColor(getResources().getColor(R.color.black));
                collete_tv04.setTextColor(getResources().getColor(R.color.main_blue));

                break;

            default:
                break;


        }

    }
}
