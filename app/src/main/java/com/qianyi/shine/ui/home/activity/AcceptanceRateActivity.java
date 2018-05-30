package com.qianyi.shine.ui.home.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
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
import com.qianyi.shine.dialog.CustomLoadingDialog;
import com.qianyi.shine.ui.account.activity.OccupationWebviewActivity;
import com.qianyi.shine.ui.account.bean.LoginBean;
import com.qianyi.shine.ui.college.activity.CollegeActivity;
import com.qianyi.shine.ui.home.adapter.IntellgenceFillAdapter;
import com.qianyi.shine.ui.home.bean.SchoolInfo;
import com.qianyi.shine.ui.home.bean.UniversityBean;
import com.qianyi.shine.ui.mine.activity.VipActivity;
import com.qianyi.shine.utils.SPUtils;
import com.qianyi.shine.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/10.
 */

public class AcceptanceRateActivity extends BaseActivity {
    @BindView(R.id.rv_college)
    RecyclerView rv_college;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.no_internet_rl)
    RelativeLayout no_internet_rl;
    @BindView(R.id.no_data_rl)
    RelativeLayout no_data_rl;
    @BindView(R.id.reload)
    TextView reload;
    @BindView(R.id.tv_score)
    TextView tv_score;
    @BindView(R.id.et_search)
    EditText et_search;
    @BindView(R.id.ll_openVip)
    LinearLayout ll_openVip;
    private IntellgenceFillAdapter mAdapter;
    public List<SchoolInfo> infoList;
    private int mNextRequestPage = 1;
    private static final int PAGE_SIZE = 6;
    private CustomLoadingDialog customLoadingDialog;
    private String keyWord,memberId,isVip;
    private MyReceiver myReceiver;
    @Override
    protected void initViews() {
        BaseActivity.addActivity(this);
        LoginBean.LoginData.LoginInfo loginInfo = Utils.readUser(this);
        memberId= loginInfo.getId();
        isVip=loginInfo.getIs_vip();
        customLoadingDialog=new CustomLoadingDialog(this);
        swipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        rv_college.setLayoutManager(new LinearLayoutManager(AcceptanceRateActivity.this));
        //上拉加载
        initAdapter();
        //下拉刷新
        initRefreshLayout();
        swipeRefreshLayout.setRefreshing(false);
        setScoreData();

        //注册广播
        myReceiver= new MyReceiver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("com.action.open.vip");
        registerReceiver(myReceiver,intentFilter);
    }

    private void setScoreData() {
        LoginBean.LoginData.LoginInfo loginInfo = Utils.readUser(this);
        String score=loginInfo.getMember_scoreinfo().getScore();
        String area=loginInfo.getMember_scoreinfo().getProv();
        String type=loginInfo.getMember_scoreinfo().getType();

        tv_score.setText(area+"/"+type+"/"+score);
    }
    private void initRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }
    private void initAdapter() {
        mAdapter = new IntellgenceFillAdapter(R.layout.item_intellgence_fill,infoList);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        rv_college.setAdapter(mAdapter);
        rv_college.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<SchoolInfo> data = mAdapter.getData();
                Intent intent=new Intent(AcceptanceRateActivity.this, CollegeActivity.class);
                intent.putExtra("id",data.get(position).getId());
                intent.putExtra("name",data.get(position).getName());
                startActivity(intent);
            }
        });
    }
    //刷新
    private void refresh() {
        mNextRequestPage = 1;
        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        apiHome.acceptanceRate(apiConstant.ACCEPT_RATE, keyWord,memberId,mNextRequestPage,new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        customLoadingDialog.dismiss();
                        Gson gson=new Gson();
                        UniversityBean universityBean = gson.fromJson(s, UniversityBean.class);
                        List<SchoolInfo> universityList = universityBean.getData().getInfo().getPriorSchoolList();
                        //数据不为空
                        if (universityList!=null && universityList.size()>0){
                            setData(true,universityList);
                            if (isVip.equals("0")){
                                swipeRefreshLayout.setVisibility(View.GONE);
                                no_internet_rl.setVisibility(View.GONE);
                                no_data_rl.setVisibility(View.GONE);
                                ll_openVip.setVisibility(View.VISIBLE);
                            }else if(isVip.equals("1")){
                                swipeRefreshLayout.setVisibility(View.VISIBLE);
                                no_internet_rl.setVisibility(View.GONE);
                                no_data_rl.setVisibility(View.GONE);
                                ll_openVip.setVisibility(View.GONE);
                            }
                        }else{
                            //在这里显示一下逻辑
                            swipeRefreshLayout.setVisibility(View.GONE);
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
                        customLoadingDialog.dismiss();
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
                        Toast.makeText(AcceptanceRateActivity.this, "网络错误", Toast.LENGTH_LONG).show();
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



    @Override
    protected void initData() {

    }
    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_acceptance_rate);
    }

    @Override
    protected void initListener() {
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                keyWord=et_search.getText().toString().trim();
                if (!TextUtils.isEmpty(keyWord)){
                    if (Utils.hasInternet()){
                        customLoadingDialog.show();
                        refresh();
                    }else{
                        swipeRefreshLayout.setVisibility(View.GONE);
                        no_data_rl.setVisibility(View.GONE);
                        no_internet_rl.setVisibility(View.VISIBLE);
                    }
                }
                return false;
            }
        });
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh();
            }
        });
    }
    @Override
    protected void setStatusBarColor() {

    }

    @OnClick({R.id.iv_back,R.id.btn_openVip})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_openVip:
                jumpActivity(AcceptanceRateActivity.this, VipActivity.class);
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }

    //接收支付成功的广播
    class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("com.action.open.vip")){
                ll_openVip.setVisibility(View.GONE);
                swipeRefreshLayout.setVisibility(View.VISIBLE);
            }
        }
    }
}
