package com.qianyi.shine.ui.home.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.api.apiHome;
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.dialog.CustomLoadingDialog;
import com.qianyi.shine.ui.college.adapter.OccupationRightProfessionAdapter;
import com.qianyi.shine.ui.college.adapter.RightProfessionAdapter;
import com.qianyi.shine.ui.college.view.MyScrollview;
import com.qianyi.shine.ui.home.bean.JobInfoBean;
import com.qianyi.shine.ui.home.bean.JobMajor;
import com.qianyi.shine.ui.home.bean.OccupationInnerInfo;
import com.qianyi.shine.utils.Utils;

import java.security.acl.LastOwnerException;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/7.
 */

public class OccupationBaseInfoFragment extends BaseFragment {
    //对口职业
    @BindView(R.id.rightprofession_rv)
    public RecyclerView rightprofession_rv;
    @BindView(R.id.tv_workingContent)
    TextView tv_workingContent;
    @BindView(R.id.tv_female)
    TextView tv_female;
    @BindView(R.id.tv_male)
    TextView tv_male;
    @BindView(R.id.myScrollview)
    MyScrollview myScrollview;
    @BindView(R.id.no_internet_rl)
    RelativeLayout no_internet_rl;
    @BindView(R.id.no_data_rl)
    RelativeLayout no_data_rl;
    @BindView(R.id.reload)
    TextView reload;
    private OccupationRightProfessionAdapter adapter;
    private Intent intent;
    private String occupationName;
    private CustomLoadingDialog customLoadingDialog;
    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_occupation_baseinfo,null);
    }
    @Override
    protected void initViews() {
        customLoadingDialog=new CustomLoadingDialog(getActivity());
        intent=getActivity().getIntent();
        if (intent!=null){
            occupationName=intent.getStringExtra("occupationName");
        }
    }
    @Override
    protected void initData() {
        if (Utils.hasInternet()){
            customLoadingDialog.show();
            getData();
        }else{
            myScrollview.setVisibility(View.GONE);
            no_internet_rl.setVisibility(View.VISIBLE);
            no_data_rl.setVisibility(View.GONE);
        }
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
    }
    @Override
    protected void initListener() {

    }
    private void getData() {
        Log.i("tag","occupationName"+occupationName);
        apiHome.occupationDetail(apiConstant.OCCUPATION_DETAIL, occupationName, new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                customLoadingDialog.dismiss();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson=new Gson();
                        JobInfoBean jobInfoBean = gson.fromJson(s, JobInfoBean.class);
                        String code = jobInfoBean.getCode();
                        if (code.equals("0")){
                            myScrollview.setVisibility(View.VISIBLE);
                            no_internet_rl.setVisibility(View.GONE);
                            no_data_rl.setVisibility(View.GONE);
                            OccupationInnerInfo job_info = jobInfoBean.getData().getInfo().getJob_info();
                            tv_workingContent.setText(job_info.getContent());
                            tv_female.setText(job_info.getFemalte_ratio());
                            tv_male.setText(job_info.getMale_ratio());
                            //发送广播
                            String name = job_info.getName();
                            String category = job_info.getCategory();
                            Intent intent=new Intent();
                            intent.putExtra("name",name);
                            intent.putExtra("category",category);
                            intent.setAction("com.action.occupation");
                            getActivity().sendBroadcast(intent);
                            //对口职业
                            if (job_info!=null){
                                List<JobMajor> job_major = job_info.getJob_major();
                                rightprofession_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                                adapter=new OccupationRightProfessionAdapter(getActivity(),job_major);
                                rightprofession_rv.setAdapter(adapter);
                            }
                        }else if (code.equals("-100")){
                            Intent intent=new Intent();
                            intent.putExtra("name",occupationName);
                            intent.putExtra("category","暂无数据");
                            intent.setAction("com.action.occupation");
                            getActivity().sendBroadcast(intent);
                            myScrollview.setVisibility(View.GONE);
                            no_internet_rl.setVisibility(View.GONE);
                            no_data_rl.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                customLoadingDialog.dismiss();
                myScrollview.setVisibility(View.GONE);
                no_internet_rl.setVisibility(View.VISIBLE);
                no_data_rl.setVisibility(View.GONE);
            }
        });
    }
}
