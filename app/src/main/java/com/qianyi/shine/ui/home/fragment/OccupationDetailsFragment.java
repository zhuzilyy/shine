package com.qianyi.shine.ui.home.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiHome;
import com.qianyi.shine.application.MyApplication;
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.fragment.entity.TestEntity;
import com.qianyi.shine.ui.account.activity.WebviewActivity;

import com.qianyi.shine.ui.home.adapter.OccupationDetailsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/7.
 */

public class OccupationDetailsFragment extends BaseFragment {
    @BindView(R.id.rv_list)
    public RecyclerView mRecyclerView;
    @BindView(R.id.swipeLayout)
    public SwipeRefreshLayout mSwipeRefreshLayout;
    private OccupationDetailsAdapter mAdapter;
    List<TestEntity> testEntities;
    private int mNextRequestPage = 1;
    private static final int PAGE_SIZE = 6;
    
    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_occupation_details,null);
    }

    @Override
    protected void initViews() {
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //上拉加载
        initAdapter();

        //下拉刷新
        initRefreshLayout();
        mSwipeRefreshLayout.setRefreshing(true);
        refresh();

    }

   

    @Override
    protected void initData() {
        testEntities = new ArrayList<>();
        testEntities.add(new TestEntity(R.mipmap.toutiao, "福克斯的回复回复康师傅ISO花花覅会滴啊上花覅胡覅武器哈佛付款后覅if刚切完就看你看了 一和覅偶"));
        testEntities.add(new TestEntity(R.mipmap.tou2, "佛菩萨的反馈ljkfosj jfoisjefoskjfpos 一和覅偶"));
        testEntities.add(new TestEntity(R.mipmap.tou3, "FJSKLJFOFJOWIJOIJFOJFLSJFOSEJFOSJDLFOSFUOWEJOF JFOWJFOWJFOJHFKSHFIUHFIOWEHJO"));
        testEntities.add(new TestEntity(R.mipmap.toutiao, "福克斯的回复回复康师傅ISO花花覅会滴啊上花覅胡覅武器哈佛付款后覅if刚切完就看你看了 一和覅偶"));
        testEntities.add(new TestEntity(R.mipmap.tou2, "佛菩萨的反馈ljkfosj jfoisjefoskjfpos 一和覅偶"));
        testEntities.add(new TestEntity(R.mipmap.tou3, "FJSKLJFOFJOWIJOIJFOJFLSJFOSEJFOSJDLFOSFUOWEJOF JFOWJFOWJFOJHFKSHFIUHFIOWEHJO"));
        testEntities.add(new TestEntity(R.mipmap.toutiao, "福克斯的回复回复康师傅ISO花花覅会滴啊上花覅胡覅武器哈佛付款后覅if刚切完就看你看了 一和覅偶"));
        testEntities.add(new TestEntity(R.mipmap.tou2, "佛菩萨的反馈ljkfosj jfoisjefoskjfpos 一和覅偶"));
        testEntities.add(new TestEntity(R.mipmap.tou3, "FJSKLJFOFJOWIJOIJFOJFLSJFOSEJFOSJDLFOSFUOWEJOF JFOWJFOWJFOJHFKSHFIUHFIOWEHJO"));


    }



    @Override
    protected void initListener() {

    }



    //****************************************
    private void initAdapter() {
        mAdapter = new OccupationDetailsAdapter(R.layout.layout_occupationdetails_item, testEntities);
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
                Intent intent=new Intent(getActivity(), WebviewActivity.class);
                intent.putExtra("title","高考头条");
                intent.putExtra("url","http://www.baidu.com");

                startActivity(intent);
            }
        });


    }

    //刷新
    private void refresh() {
        mNextRequestPage = 1;
        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        apiHome.refresh("http://www.baidu.com", mNextRequestPage,"", new com.qianyi.shine.callbcak.RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, String s) {
                Log.i("ppp", "131" + s);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setData(true, testEntities);
                        mAdapter.setEnableLoadMore(true);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                Log.i("ppp", "132" + e);
               getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setData(true, testEntities);
                        mAdapter.setEnableLoadMore(true);
                        mSwipeRefreshLayout.setRefreshing(false);
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
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setData(false, testEntities);
                    }
                });

            }

            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.loadMoreFail();
                        Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_LONG).show();
                    }
                });
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
            Toast.makeText(getActivity(), "第一页如果不够一页就不显示没有更多数据布局", Toast.LENGTH_SHORT).show();
        } else {
            mAdapter.loadMoreComplete();
        }
    }
}
