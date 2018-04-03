package com.qianyi.shine.ui.college.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.fragment.adapter.GridAdapter;
import com.qianyi.shine.fragment.adapter.PullToRefreshAdapter;
import com.qianyi.shine.ui.account.activity.WebviewActivity;
import com.qianyi.shine.ui.career_planning.entity.SuitableForMeEntity;
import com.qianyi.shine.ui.college.adapter.ProfessionAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/3/31.
 */

public class collegeProfessionalSettingsFragment extends BaseFragment {
    @BindView(R.id.swipeLayout)
    public SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rv_list)
    public RecyclerView mRecyclerView;
    public ProfessionAdapter mAdapter;
    public List<SuitableForMeEntity> list_temp;
    private int mNextRequestPage = 1;
    private static final int PAGE_SIZE = 6;

    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        View layoutRes= inflater.inflate(R.layout.fragment_college_professionalsettings,null);
        return layoutRes;
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
        list_temp=new ArrayList<>();
        list_temp.add(new SuitableForMeEntity("积分上飞机","斐林试剂佛我文件佛if我欧文覅积分","发链接非叫我佛教佛为我金佛我我飞机哦我IE见覅欧文"));
        list_temp.add(new SuitableForMeEntity("积分上飞机","斐林试剂佛我文件佛if我欧文覅积分","发链接非叫我佛教佛为我金佛我我飞机哦我IE见覅欧文"));
        list_temp.add(new SuitableForMeEntity("积分上飞机","斐林试剂佛我文件佛if我欧文覅积分","发链接非叫我佛教佛为我金佛我我飞机哦我IE见覅欧文"));
        list_temp.add(new SuitableForMeEntity("积分上飞机","斐林试剂佛我文件佛if我欧文覅积分","发链接非叫我佛教佛为我金佛我我飞机哦我IE见覅欧文"));
        list_temp.add(new SuitableForMeEntity("积分上飞机","斐林试剂佛我文件佛if我欧文覅积分","发链接非叫我佛教佛为我金佛我我飞机哦我IE见覅欧文"));

        list_temp.add(new SuitableForMeEntity("积分上飞机","斐林试剂佛我文件佛if我欧文覅积分","发链接非叫我佛教佛为我金佛我我飞机哦我IE见覅欧文"));
        list_temp.add(new SuitableForMeEntity("积分上飞机","斐林试剂佛我文件佛if我欧文覅积分","发链接非叫我佛教佛为我金佛我我飞机哦我IE见覅欧文"));
        list_temp.add(new SuitableForMeEntity("积分上飞机","斐林试剂佛我文件佛if我欧文覅积分","发链接非叫我佛教佛为我金佛我我飞机哦我IE见覅欧文"));
        list_temp.add(new SuitableForMeEntity("积分上飞机","斐林试剂佛我文件佛if我欧文覅积分","发链接非叫我佛教佛为我金佛我我飞机哦我IE见覅欧文"));
        list_temp.add(new SuitableForMeEntity("积分上飞机","斐林试剂佛我文件佛if我欧文覅积分","发链接非叫我佛教佛为我金佛我我飞机哦我IE见覅欧文"));

        list_temp.add(new SuitableForMeEntity("积分上飞机","斐林试剂佛我文件佛if我欧文覅积分","发链接非叫我佛教佛为我金佛我我飞机哦我IE见覅欧文"));
        list_temp.add(new SuitableForMeEntity("积分上飞机","斐林试剂佛我文件佛if我欧文覅积分","发链接非叫我佛教佛为我金佛我我飞机哦我IE见覅欧文"));
        list_temp.add(new SuitableForMeEntity("积分上飞机","斐林试剂佛我文件佛if我欧文覅积分","发链接非叫我佛教佛为我金佛我我飞机哦我IE见覅欧文"));
        list_temp.add(new SuitableForMeEntity("积分上飞机","斐林试剂佛我文件佛if我欧文覅积分","发链接非叫我佛教佛为我金佛我我飞机哦我IE见覅欧文"));
        list_temp.add(new SuitableForMeEntity("积分上飞机","斐林试剂佛我文件佛if我欧文覅积分","发链接非叫我佛教佛为我金佛我我飞机哦我IE见覅欧文"));

    }

    @Override
    protected void initListener() {

    }

    private void initAdapter() {
        mAdapter = new ProfessionAdapter(R.layout.professional_item,list_temp);
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
    private void initRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }
    //刷新
    private void refresh() {
        Toast.makeText(mActivity, "1346458465", Toast.LENGTH_SHORT).show();
        mNextRequestPage = 1;
        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        apiHome.refresh("http://www.baidu.com", mNextRequestPage, new com.qianyi.shine.callbcak.RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, String s) {
                Log.i("ppp","131"+s);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setData(true,list_temp);
                        mAdapter.setEnableLoadMore(true);
                        mSwipeRefreshLayout.setRefreshing(false);


                    }
                });
            }

            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                Log.i("ppp","132"+e);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setData(true,list_temp);
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
                        setData(false, list_temp);
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
            Toast.makeText(getActivity(), "第一页如果不够一页就不显示没有更多数据布局", Toast.LENGTH_SHORT).show();
        } else {
            mAdapter.loadMoreComplete();
        }
    }

}
