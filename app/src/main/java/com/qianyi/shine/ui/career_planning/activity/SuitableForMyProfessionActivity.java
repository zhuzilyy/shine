package com.qianyi.shine.ui.career_planning.activity;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiHome;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.fragment.adapter.GridAdapter;
import com.qianyi.shine.fragment.adapter.PullToRefreshAdapter;
import com.qianyi.shine.ui.career_planning.adapter.TestAdapter;
import com.qianyi.shine.ui.career_planning.entity.SuitableForMeEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/2.
 */

public class SuitableForMyProfessionActivity extends BaseActivity {
    @BindView(R.id.swipeLayout)
    public SwipeRefreshLayout swipeLayout;
    @BindView(R.id.rv_list)
    public RecyclerView mRecyclerView;
    private int mNextRequestPage = 1;
    private static final int PAGE_SIZE = 6;
    private TestAdapter mAdapter;
    private List<SuitableForMeEntity> forMeEntities;
    @BindView(R.id.iv_back)
    public ImageView back;
    @BindView(R.id.tv_title)
    public TextView title;
    @Override
    protected void initViews() {
        swipeLayout.setColorSchemeColors(Color.rgb(47,233,189));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(SuitableForMyProfessionActivity.this));
        //
        title.setText("适合我的职业");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //上拉加载
        initAdapter();
        //添加head
        addHeadView();
        //下拉刷新
        initRefreshLayout();
        swipeLayout.setRefreshing(true);
        refresh();



    }
    private void initRefreshLayout() {
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    private void addHeadView() {
        View headView = getLayoutInflater().inflate(R.layout.head_view_suitableme, (ViewGroup) mRecyclerView.getParent(), false);
        mAdapter.addHeaderView(headView);

    }

    @Override
    protected void initData() {
        forMeEntities=new ArrayList<>();
        forMeEntities.add(new SuitableForMeEntity("房间开始尖峰时刻金服","富家大室金佛山","foe金佛山飞机哦is阿胶粉还发快手废物"));
        forMeEntities.add(new SuitableForMeEntity("房间开始尖峰时刻金服","富家大室金佛山","foe金佛山飞机哦is阿胶粉还发快手废物"));
        forMeEntities.add(new SuitableForMeEntity("房间开始尖峰时刻金服","富家大室金佛山","foe金佛山飞机哦is阿胶粉还发快手废物"));
        forMeEntities.add(new SuitableForMeEntity("房间开始尖峰时刻金服","富家大室金佛山","foe金佛山飞机哦is阿胶粉还发快手废物"));
        forMeEntities.add(new SuitableForMeEntity("房间开始尖峰时刻金服","富家大室金佛山","foe金佛山飞机哦is阿胶粉还发快手废物"));
        forMeEntities.add(new SuitableForMeEntity("房间开始尖峰时刻金服","富家大室金佛山","foe金佛山飞机哦is阿胶粉还发快手废物"));
        forMeEntities.add(new SuitableForMeEntity("房间开始尖峰时刻金服","富家大室金佛山","foe金佛山飞机哦is阿胶粉还发快手废物"));
        forMeEntities.add(new SuitableForMeEntity("房间开始尖峰时刻金服","富家大室金佛山","foe金佛山飞机哦is阿胶粉还发快手废物"));
        forMeEntities.add(new SuitableForMeEntity("房间开始尖峰时刻金服","富家大室金佛山","foe金佛山飞机哦is阿胶粉还发快手废物"));
        forMeEntities.add(new SuitableForMeEntity("房间开始尖峰时刻金服","富家大室金佛山","foe金佛山飞机哦is阿胶粉还发快手废物"));
        forMeEntities.add(new SuitableForMeEntity("房间开始尖峰时刻金服","富家大室金佛山","foe金佛山飞机哦is阿胶粉还发快手废物"));
        forMeEntities.add(new SuitableForMeEntity("房间开始尖峰时刻金服","富家大室金佛山","foe金佛山飞机哦is阿胶粉还发快手废物"));
    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_suitableme);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
    private void initAdapter() {
        mAdapter = new TestAdapter(R.layout.lay_suitableme_item,forMeEntities);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mAdapter.setPreLoadNumber(3);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                Toast.makeText(SuitableForMyProfessionActivity.this, Integer.toString(position), Toast.LENGTH_LONG).show();
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
                        setData(true,forMeEntities);
                        mAdapter.setEnableLoadMore(true);
                        swipeLayout.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                Log.i("ppp","132"+e);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setData(true,forMeEntities);
                        mAdapter.setEnableLoadMore(true);
                       swipeLayout.setRefreshing(false);
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
                        setData(false, forMeEntities);
                    }
                });


            }

            @Override
            public void onEror(Call call, int statusCode, Exception e) {
               runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.loadMoreFail();
                        Toast.makeText(SuitableForMyProfessionActivity.this, "网络错误", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }
    private void setData(boolean isRefresh, List data) {
        mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            mAdapter.setNewData(data);
        } else {
            if (size > 0) {
                mAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            mAdapter.loadMoreEnd(isRefresh);
            Toast.makeText(SuitableForMyProfessionActivity.this, "第一页如果不够一页就不显示没有更多数据布局", Toast.LENGTH_SHORT).show();
        } else {
            mAdapter.loadMoreComplete();
        }
    }


}
