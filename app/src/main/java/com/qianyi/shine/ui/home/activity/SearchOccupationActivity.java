package com.qianyi.shine.ui.home.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kanade.treeadapter.Node;
import com.kanade.treeadapter.TreeAdapter;
import com.kanade.treeadapter.TreeItemClickListener;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.api.apiHome;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.dialog.CustomLoadingDialog;
import com.qianyi.shine.fragment.entity.FirstLevel;
import com.qianyi.shine.fragment.entity.MajorBean;
import com.qianyi.shine.fragment.entity.SecondLevel;
import com.qianyi.shine.fragment.entity.ThirdLevel;
import com.qianyi.shine.ui.college.activity.ProfessionalActivity;
import com.qianyi.shine.ui.home.bean.FirstJob;
import com.qianyi.shine.ui.home.bean.JobBean;
import com.qianyi.shine.ui.home.bean.Major;
import com.qianyi.shine.ui.home.bean.SecondJob;
import com.qianyi.shine.ui.home.bean.ThirdJob;
import com.qianyi.shine.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/7.
 */

public class SearchOccupationActivity extends BaseActivity {
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.et_searchOccupation)
    EditText et_searchOccupation;
    @BindView(R.id.no_internet_rl)
    RelativeLayout no_internet_rl;
    @BindView(R.id.no_data_rl)
    RelativeLayout no_data_rl;
    private String tag;
    private Intent intent;
    private CustomLoadingDialog customLoadingDialog;
    private List<Major> jobList;
    @Override
    protected void initViews() {
        BaseActivity.addActivity(this);
        intent=getIntent();
        if (intent!=null){
            tag=intent.getStringExtra("tag");
        }
        customLoadingDialog=new CustomLoadingDialog(this);
        jobList=new ArrayList<>();
    }
    @Override
    protected void initData() {
        getData();
    }
    private void getData() {
        //有网络
        if (Utils.hasInternet()){
            customLoadingDialog.show();
            apiHome.getJobList(apiConstant.JOB_LIST, new RequestCallBack<String>() {
                @Override
                public void onSuccess(Call call, Response response, final String s) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Gson gson=new Gson();
                            JobBean jobBean = gson.fromJson(s, JobBean.class);
                            List<FirstJob> firstJobList = jobBean.getData().getInfo().getJobList();
                            if (firstJobList.size()>0){
                                recyclerView.setVisibility(View.VISIBLE);
                                no_internet_rl.setVisibility(View.GONE);
                                no_data_rl.setVisibility(View.GONE);
                                //第一层数据
                                for (int i = 0; i < firstJobList.size(); i++) {
                                    FirstJob firstJob = firstJobList.get(i);
                                    String cate_one_name =firstJob .getCate_one_name();
                                    String cate_one_id=firstJob.getCate_one_id();
                                    long firstLongId=Long.parseLong(cate_one_id);
                                    Major firstMajor=new Major(firstLongId,0,cate_one_name,1);
                                    jobList.add(firstMajor);
                                    //非空判断防止崩溃
                                    //Log.i("tag","111"+major_cate);
                                    List<SecondJob> secondJobList = firstJob.getCate_two_list();
                                    if (secondJobList!=null && secondJobList.size()>0){
                                        for (int j = 0; j <secondJobList.size() ; j++) {
                                            //第二层数据
                                            SecondJob secondJob = secondJobList.get(j);
                                            String cate_two_name = secondJob.getCate_two_name();
                                            String cate_two_id = secondJob.getCate_two_id();
                                            long secondLongId=Long.parseLong(cate_two_id);
                                            List<ThirdJob> thirdJobList = secondJob.getCate_three_list();
                                            Major secondMajor=new Major(secondLongId,firstLongId,cate_two_name);
                                            jobList.add(secondMajor);
                                            if (thirdJobList!=null && thirdJobList.size()>0){
                                                for (int k = 0; k <thirdJobList.size() ; k++) {
                                                    ThirdJob thirdJob = thirdJobList.get(k);
                                                    String name = thirdJob.getCate_three_name();
                                                    String id = thirdJob.getCate_three_id();
                                                    long thirdLongId=Long.parseLong(id);
                                                    Major thirdMajor=new Major(thirdLongId,secondLongId,name);
                                                    jobList.add(thirdMajor);
                                                }
                                            }
                                        }
                                    }
                                }
                            }else{
                                recyclerView.setVisibility(View.GONE);
                                no_internet_rl.setVisibility(View.GONE);
                                no_data_rl.setVisibility(View.VISIBLE);
                            }
                            //设置适配器
                            setAdapter(jobList);
                        }
                    });
                }
                @Override
                public void onEror(Call call, int statusCode, Exception e) {
                    customLoadingDialog.dismiss();
                    recyclerView.setVisibility(View.GONE);
                    no_internet_rl.setVisibility(View.VISIBLE);
                    no_data_rl.setVisibility(View.GONE);
                }
            });
        }else{
            recyclerView.setVisibility(View.GONE);
            no_internet_rl.setVisibility(View.VISIBLE);
            no_data_rl.setVisibility(View.GONE);
        }
    }

    //加载适配器
    private void setAdapter(List<Major> majorList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final TreeAdapter<Major> adapter = new TreeAdapter<>(this);
        adapter.setNodes(majorList);
        recyclerView.setAdapter(adapter);
        customLoadingDialog.dismiss();
        adapter.setListener(new TreeItemClickListener() {
            @Override
            public void OnClick(Node node) {
                boolean leaf = node.isLeaf();
                if (leaf){
                    if (!TextUtils.isEmpty(tag)){
                        //意愿设置里面的查专业
                        if (tag.equals("willingSetting")){
                            Intent intent=new Intent();
                            intent.putExtra("cccupationName",node.getName());
                            setResult(3,intent);
                            finish();
                            //搜职业
                        }else if (tag.equals("searchOccupation")){
                            Intent intent=new Intent(SearchOccupationActivity.this,OccupationDetailActivity.class);
                            intent.putExtra("occupationName",node.getName());
                            startActivity(intent);
                        }
                    }
                }
            }
        });
    }


    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_search_occpation);
    }
    @Override
    protected void initListener() {
        et_searchOccupation.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                String keyWord=et_searchOccupation.getText().toString().trim();
                if (!TextUtils.isEmpty(keyWord)){
                    Intent intent=new Intent(SearchOccupationActivity.this,OccupationDetailActivity.class);
                    intent.putExtra("occupationName",keyWord);
                    startActivity(intent);
                }
                return false;
            }
        });
    }
    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.iv_back})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
