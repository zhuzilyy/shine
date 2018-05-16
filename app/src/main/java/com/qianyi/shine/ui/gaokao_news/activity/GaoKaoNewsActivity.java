package com.qianyi.shine.ui.gaokao_news.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.api.apiHome;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.fragment.entity.TestEntity;
import com.qianyi.shine.ui.account.activity.WebviewActivity;
import com.qianyi.shine.ui.gaokao_news.adapter.GaokaoAdapter;
import com.qianyi.shine.ui.gaokao_news.entivity.ArticleBean;
import com.qianyi.shine.ui.gaokao_news.view.XTitleView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/3/31.
 */

public class GaoKaoNewsActivity extends BaseActivity {
    @BindView(R.id.rv_list)
    public RecyclerView mRecyclerView;
    @BindView(R.id.swipeLayout)
    public SwipeRefreshLayout mSwipeRefreshLayout;
    private GaokaoAdapter mAdapter;
    List<TestEntity> testEntities;
    private int mNextRequestPage = 1;
    private static final int PAGE_SIZE = 6;
    @BindView(R.id.title)
    public XTitleView titleView;

    @Override
    protected void initViews() {
        BaseActivity.addActivity(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(GaoKaoNewsActivity.this));
        //上拉加载
        initAdapter();
        //下拉刷新
        initRefreshLayout();
        mSwipeRefreshLayout.setRefreshing(true);
        refresh();
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_gaokaonews);
        ButterKnife.bind(this);

    }

    @Override
    protected void initListener() {
        titleView.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                List<ArticleBean.AriticleData.AriticleInfo.AriticleList> ariticleList = adapter.getData();
                Intent intent = new Intent(GaoKaoNewsActivity.this, WebviewActivity.class);
                intent.putExtra("title", "高考头条");
                intent.putExtra("url", ariticleList.get(position).getWeburl());

                startActivity(intent);
            }
        });


    }

    @Override
    protected void setStatusBarColor() {


    }

    //****************************************
    private void initAdapter() {
        mAdapter = new GaokaoAdapter(GaoKaoNewsActivity.this);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mAdapter.setPreLoadNumber(3);
        mRecyclerView.setAdapter(mAdapter);


    }

    //刷新
    private void refresh() {
        mNextRequestPage = 1;
        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        apiHome.refresh(apiConstant.ARTICLEMORE, mNextRequestPage,"", new com.qianyi.shine.callbcak.RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                Log.i("pppxzy", "131" + s);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        ArticleBean articleBean = gson.fromJson(s, ArticleBean.class);
                        if (articleBean != null) {
                            String code = articleBean.getCode();
                            if ("0".equals(code)) {
                                ArticleBean.AriticleData ariticleData = articleBean.getData();
                                if (ariticleData != null) {
                                    ArticleBean.AriticleData.AriticleInfo ariticleInfo = ariticleData.getInfo();
                                    if(ariticleInfo != null){
                                        setData(true, ariticleInfo.getArticleList());
                                        mAdapter.setEnableLoadMore(true);
                                        mSwipeRefreshLayout.setRefreshing(false);
                                    }


                                }
                            } else {
                                Toast.makeText(GaoKaoNewsActivity.this, "" + articleBean.getInfo(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }

            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                Log.i("ppp", "132" + e);


            }
        });

    }

    //加载
    private void loadMore() {

        mNextRequestPage++;
     //   mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        apiHome.refresh(apiConstant.ARTICLEMORE, mNextRequestPage,"", new com.qianyi.shine.callbcak.RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                Log.i("pppxzy", "131" + s);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        ArticleBean articleBean = gson.fromJson(s, ArticleBean.class);
                        if (articleBean != null) {
                            String code = articleBean.getCode();
                            if ("0".equals(code)) {
                                ArticleBean.AriticleData ariticleData = articleBean.getData();
                                if (ariticleData != null) {
                                    ArticleBean.AriticleData.AriticleInfo ariticleInfo = ariticleData.getInfo();
                                    if(ariticleInfo != null){
                                        setData(false, ariticleInfo.getArticleList());
                                        mAdapter.setEnableLoadMore(true);
                                        mSwipeRefreshLayout.setRefreshing(false);
                                    }
                                }
                            } else {
                                Toast.makeText(GaoKaoNewsActivity.this, "" + articleBean.getInfo(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }

            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                Log.i("ppp", "132" + e);


            }
        });


    }

    private void initRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    private void setData(boolean isRefresh, List data) {
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
        } else {
            mAdapter.loadMoreComplete();
        }
    }
}
