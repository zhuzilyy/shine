package com.qianyi.shine.ui.home.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiHome;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.ui.home.adapter.IntellgenceFillAdapter;
import com.qianyi.shine.ui.home.bean.IntellgenceFillBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/7.
 */

public class IntelligentFillCollegeActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.rv_college)
    RecyclerView rv_college;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private IntellgenceFillAdapter mAdapter;
    public List<IntellgenceFillBean> infoList;
    private int mNextRequestPage = 1;
    private static final int PAGE_SIZE = 6;
    @Override
    protected void initViews() {
        tv_title.setText("智能填报");
        infoList=new ArrayList<>();
        swipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        rv_college.setLayoutManager(new LinearLayoutManager(IntelligentFillCollegeActivity.this));
        //上拉加载
        initAdapter();
        //下拉刷新
        initRefreshLayout();
        swipeRefreshLayout.setRefreshing(true);
        refresh();

    }
    @Override
    protected void initData() {
        for (int i = 0; i <10 ; i++) {
            IntellgenceFillBean intellgenceFillBean=new IntellgenceFillBean();
            infoList.add(intellgenceFillBean);
        }
        mAdapter.notifyDataSetChanged();
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
        View view_header=getLayoutInflater().inflate(R.layout.header_intelligence_fill,null);
        mAdapter.addHeaderView(view_header);


        rv_college.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(IntelligentFillCollegeActivity.this, com.qianyi.shine.ui.college.activity.CollegeActivity.class);
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
        apiHome.refresh("http://www.baidu.com", mNextRequestPage, new com.qianyi.shine.callbcak.RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, String s) {
                Log.i("ppp","131"+s);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setData(true,infoList);
                        mAdapter.setEnableLoadMore(true);
                        swipeRefreshLayout.setRefreshing(false);


                    }
                });
            }

            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                Log.i("ppp","132"+e);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setData(true,infoList);
                        mAdapter.setEnableLoadMore(true);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });


            }
        });

    }
    //加载
    private void loadMore() {

        apiHome.loadMore("http://www.baidu.com", mNextRequestPage, new com.qianyi.shine.callbcak.RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setData(false, infoList);
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
            Toast.makeText(IntelligentFillCollegeActivity.this, "第一页如果不够一页就不显示没有更多数据布局", Toast.LENGTH_SHORT).show();
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
}
