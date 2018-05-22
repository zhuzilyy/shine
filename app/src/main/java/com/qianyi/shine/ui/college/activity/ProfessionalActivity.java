package com.qianyi.shine.ui.college.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.api.apiHome;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.bean.Util;
import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.dialog.CustomLoadingDialog;
import com.qianyi.shine.ui.account.bean.LoginBean;
import com.qianyi.shine.ui.career_planning.activity.TestActivity;
import com.qianyi.shine.ui.college.adapter.MyPageAdapter;
import com.qianyi.shine.ui.college.entivity.ProfessionBean;
import com.qianyi.shine.ui.college.fragments.Profession_BaseInfoFragment;
import com.qianyi.shine.ui.college.fragments.Profession_EstablishmentFragment;
import com.qianyi.shine.ui.college.fragments.Profession_ProspectsFragment;
import com.qianyi.shine.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/3.
 */

public class ProfessionalActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.iv_back)
    public ImageView back;
    @BindView(R.id.tv_title)
    public TextView title;
    @BindView(R.id.tablayout)
    public TabLayout tab;
    @BindView(R.id.view_pager)
    public ViewPager viewPager;
    public MyPageAdapter myPageAdapter;

    private Profession_ProspectsFragment profession_prospectsFragment;
    private Profession_BaseInfoFragment profession_baseInfoFragment;
    private Profession_EstablishmentFragment profession_establishmentFragment;
    //
    @BindView(R.id.tv_professionName) public TextView tv_professionName;
    @BindView(R.id.tv_sushuxueke) public TextView tv_sushuxueke;
    @BindView(R.id.tv_guanzhu) public TextView tv_guanzhu;
    //传进来的专业id
    private String id;
    private String Major_name;
    private String MajorName;

    @Override
    protected void initViews() {
        BaseActivity.addActivity(this);
        id= getIntent().getStringExtra("id");
        MajorName = getIntent().getStringExtra("name");
        if(TextUtils.isEmpty(id)){
            id="";
        }
        if(TextUtils.isEmpty(MajorName)){
            MajorName="";
        }
        title.setText("专业详情");


        myPageAdapter = new MyPageAdapter(getSupportFragmentManager());
        ArrayList<Fragment> datas = new ArrayList<Fragment>();

        profession_prospectsFragment=new Profession_ProspectsFragment();
        profession_baseInfoFragment= new Profession_BaseInfoFragment();
        profession_establishmentFragment = new Profession_EstablishmentFragment();

        datas.add(profession_baseInfoFragment);
        datas.add(profession_prospectsFragment);
        datas.add(profession_establishmentFragment);
        myPageAdapter.setData(datas);


        ArrayList<String> titles = new ArrayList<String>();
        titles.add("基本信息");
        titles.add("就业前景");
        titles.add("开设院校");
        myPageAdapter.setTitles(titles);

        viewPager.setAdapter(myPageAdapter);
        viewPager.setOffscreenPageLimit(3);
        // 将ViewPager与TabLayout相关联
        tab.setupWithViewPager(viewPager);

    }

    @Override
    protected void initData() {

        if(!TextUtils.isEmpty(id)||!TextUtils.isEmpty(MajorName)){
            Log.i("ssss",id);
            Log.i("ssss",MajorName);

            final CustomLoadingDialog loadingDialog = new CustomLoadingDialog(ProfessionalActivity.this);
            loadingDialog.show();
            apiHome.majorDetails(apiConstant.ZHUANYEDETAILS,id,MajorName, new RequestCallBack<String>() {
                @Override
                public void onSuccess(Call call, Response response, String s) {
                    loadingDialog.dismiss();
                    Log.i("tt",s);
                    Gson gson = new Gson();
                    ProfessionBean professionBean= gson.fromJson(s,ProfessionBean.class);
                    if(professionBean!=null){
                       String code = professionBean.getCode();
                       if("0".equals(code)){
                           ProfessionBean.ProfessionData professionData = professionBean.getData();
                           if(professionData!=null){
                               ProfessionBean.ProfessionData.ProfessionInfo professionInfo = professionData.getInfo();
                                if(professionInfo!=null){
                                    ProfessionBean.ProfessionData.ProfessionInfo.MajorInfo majorInfo =  professionInfo.getMajor_info();
                                    if(majorInfo!=null){
                                        //赋值本Activity数据
                                        setData(majorInfo);
                                        Major_name=majorInfo.getMajor_name();
                                        //赋值专业基本信息和就业前景数据
                                        profession_baseInfoFragment.setMajorInfo(majorInfo);
                                        profession_prospectsFragment.setMajorInfo(majorInfo);
                                        profession_establishmentFragment.setMajorInfo(majorInfo);
                                    }

                                }
                           }
                       }
                    }

                }

                @Override
                public void onEror(Call call, int statusCode, Exception e) {
                    loadingDialog.dismiss();
                    Log.i("uuu",e.getMessage());
                }
            });
        }




    }

    /***
     * 赋值数据
     * @param majorInfo
     */
    private void setData(ProfessionBean.ProfessionData.ProfessionInfo.MajorInfo majorInfo) {
        tv_professionName.setText(majorInfo.getMajor_name());
        //所属学科
        tv_sushuxueke.setText("所属学科："+majorInfo.getIndustry_name());
    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_professional);

    }

    @Override
    protected void initListener() {
        tv_guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginBean.LoginData.LoginInfo user = Utils.readUser(ProfessionalActivity.this);

                if(user!=null&& !TextUtils.isEmpty(Major_name)){
                    final CustomLoadingDialog loadingDialog =new CustomLoadingDialog(ProfessionalActivity.this);
                    loadingDialog.show();
                    apiHome.attentionMajor(apiConstant.ATTENTIONMAJOR,user.getId(), id, Major_name, new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(Call call, Response response, final String s) {
                          runOnUiThread(new Runnable() {
                              @Override
                              public void run() {
                                  loadingDialog.dismiss();
                                  try {
                                      JSONObject jsonObject = new JSONObject(s);
                                      String code= jsonObject.getString("code");
                                      String info= jsonObject.getString("info");
                                      Toast.makeText(ProfessionalActivity.this, info, Toast.LENGTH_SHORT).show();
                                      if("0".equals(code)){
                                          Toast.makeText(ProfessionalActivity.this, jsonObject.getString("info"), Toast.LENGTH_SHORT).show();
                                      }
                                  } catch (JSONException e) {
                                      e.printStackTrace();
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
                        }
                    });

                }

            }
        });
    }
    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.iv_back})
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }

    }
}
