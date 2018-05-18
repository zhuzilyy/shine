package com.qianyi.shine.ui.home.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.api.apiHome;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.ui.account.activity.GuessScoreActivity;
import com.qianyi.shine.ui.account.bean.LoginBean;
import com.qianyi.shine.ui.college.activity.CollegeActivity;
import com.qianyi.shine.ui.home.adapter.IntellgenceFillAdapter;
import com.qianyi.shine.ui.home.bean.IntellgenceFillBean;
import com.qianyi.shine.ui.home.bean.SchoolInfo;
import com.qianyi.shine.ui.home.bean.UniversityBean;
import com.qianyi.shine.ui.mine.bean.CollegeBean;
import com.qianyi.shine.ui.mine.bean.UniversityInfo;
import com.qianyi.shine.utils.SPUtils;
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

public class IntelligentFillCollegeActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.rv_college)
    RecyclerView rv_college;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.no_internet_rl)
    RelativeLayout no_internet_rl;
    @BindView(R.id.no_data_rl)
    RelativeLayout no_data_rl;
    @BindView(R.id.btn_comfirm_)
    Button btn_comfirm_;
    private IntellgenceFillAdapter mAdapter;
    public List<SchoolInfo> infoList;
    private int mNextRequestPage = 1;
    private static final int PAGE_SIZE = 6;
    private TextView tv_willings;
    //=========意愿设置========================
    private  String area;
    private  String majorName;
    private  String occupationName,memberId;
    private TextView tv_collegeData;
    private View view_header;
    private boolean isHasHeader;
    @Override
    protected void initViews() {
        BaseActivity.addActivity(this);
        tv_title.setText("智能填报");
        infoList=new ArrayList<>();
        swipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        rv_college.setLayoutManager(new LinearLayoutManager(IntelligentFillCollegeActivity.this));
        //上拉加载
        initAdapter();
        //下拉刷新
        initRefreshLayout();
        swipeRefreshLayout.setRefreshing(true);
        LoginBean.LoginData.LoginInfo loginInfo = Utils.readUser(this);
        memberId= loginInfo.getId();
        refresh();
    }
    @Override
    protected void initData() {
    }
    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_intelligent_fill_university);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
    private void initAdapter() {
        mAdapter = new IntellgenceFillAdapter(R.layout.item_intellgence_fill,infoList);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        rv_college.setAdapter(mAdapter);
        //添加headerView
        view_header=getLayoutInflater().inflate(R.layout.header_intelligence_fill,null);
        tv_willings=view_header.findViewById(R.id.tv_willings);
        mAdapter.addHeaderView(view_header);
        setHeaderData(view_header);
        //点击事件
        view_header.findViewById(R.id.rl_willing).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(IntelligentFillCollegeActivity.this,WillingsSettingActivity.class),1);
            }
        });
        rv_college.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(IntelligentFillCollegeActivity.this, com.qianyi.shine.ui.college.activity.CollegeActivity.class);
                startActivity(intent);
            }
        });
    }
    //设置头部的一些信息
    private void setHeaderData(View view_header) {
        tv_collegeData=view_header.findViewById(R.id.tv_collegeData);
        RelativeLayout rl_setScore=view_header.findViewById(R.id.rl_setScore);
        String area = (String) SPUtils.get(IntelligentFillCollegeActivity.this, "area", "");
        String type = (String) SPUtils.get(IntelligentFillCollegeActivity.this, "type", "");
        String score = (String) SPUtils.get(IntelligentFillCollegeActivity.this, "score", "");
        tv_collegeData.setText(area+"/"+type+"/"+score);
        LoginBean.LoginData.LoginInfo loginInfo = Utils.readUser(this);
        String intention_area = loginInfo.getMember_scoreinfo().getIntention_area();
        String intention_job = loginInfo.getMember_scoreinfo().getIntention_job();
        String intention_major = loginInfo.getMember_scoreinfo().getIntention_major();
        int count=0;
        if (!TextUtils.isEmpty(intention_area)){
            count++;
        }
        if (!TextUtils.isEmpty(intention_job)){
            count++;
        }
        if (!TextUtils.isEmpty(intention_major)){
            count++;
        }
        tv_willings.setText("已经设置"+count+"个意愿");
        rl_setScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(IntelligentFillCollegeActivity.this,GuessScoreActivity.class);
                intent.putExtra("tag","intelligentFill");
                startActivity(intent);
            }
        });
    }
    private void initRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }
    //刷新
    private void refresh() {
        mNextRequestPage = 1;
        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        apiHome.intellgentFill(apiConstant.INTELLGENT_FILL, mNextRequestPage, memberId, new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                Log.i("tag",memberId+"=======memberId=======");
                Log.i("tag",s);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson=new Gson();
                        UniversityBean universityBean = gson.fromJson(s, UniversityBean.class);
                        List<SchoolInfo> universityList = universityBean.getData().getInfo().getPriorSchoolList();
                        //数据不为空
                        if (universityList!=null && universityList.size()>0){
                            setData(true,universityList);
                            swipeRefreshLayout.setVisibility(View.VISIBLE);
                            btn_comfirm_.setVisibility(View.VISIBLE);
                            no_internet_rl.setVisibility(View.GONE);
                            no_data_rl.setVisibility(View.GONE);
                        }else{
                            swipeRefreshLayout.setVisibility(View.GONE);
                            btn_comfirm_.setVisibility(View.GONE);
                            no_internet_rl.setVisibility(View.GONE);
                            no_data_rl.setVisibility(View.VISIBLE);
                        }
                        mAdapter.setEnableLoadMore(true);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setVisibility(View.GONE);
                        no_internet_rl.setVisibility(View.VISIBLE);
                        no_data_rl.setVisibility(View.GONE);
                        mAdapter.setEnableLoadMore(true);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
    }
    //加载
    private void loadMore() {
        mNextRequestPage++;
        apiHome.intellgentFill(apiConstant.INTELLGENT_FILL, mNextRequestPage,memberId,new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson=new Gson();
                        UniversityBean universityBean = gson.fromJson(s, UniversityBean.class);
                        List<SchoolInfo> universityList = universityBean.getData().getInfo().getPriorSchoolList();
                        //数据不为空
                        setData(false,universityList);
                        mAdapter.setEnableLoadMore(true);
                        swipeRefreshLayout.setRefreshing(false);

                    }
                });
            }
            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.loadMoreFail();
                        Toast.makeText(IntelligentFillCollegeActivity.this, "网络错误", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void setData(boolean isRefresh, List data) {
        mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            Log.i("uio","isisisojfsojf===()()()()()");
            mAdapter.setNewData(data);
        } else {
            if (size > 0) {
                mAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            mAdapter.loadMoreEnd(isRefresh);
        } else {
            mAdapter.loadMoreComplete();
        }
    }
    @OnClick({R.id.iv_back})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
    @OnClick({R.id.btn_comfirm_})
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_willings:
                Toast.makeText(this, "22222222222222", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_comfirm_:

                if(!TextUtils.isEmpty(area)){
                        //只设置了地区
                        Intent intent = new Intent(IntelligentFillCollegeActivity.this, PriorityCollegeActivity.class);
                        startActivity(intent);
                }else if(!TextUtils.isEmpty(majorName) || !TextUtils.isEmpty(occupationName)){
                    //设置了专业或职业
                    Intent intent = new Intent(IntelligentFillCollegeActivity.this, PriorityProfessionalDetailsActivity.class);
                    startActivity(intent);
                }
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
           int willingCount=data.getIntExtra("willing",0);
           area=data.getStringExtra("area");
           majorName=data.getStringExtra("majorName");
           occupationName=data.getStringExtra("occupationName");
            tv_willings.setText("已选"+willingCount+"项意愿");
        }
    }
}
