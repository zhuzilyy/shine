package com.qianyi.shine.ui.mine.fragment;

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
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.ui.mine.adapter.MajorAdapter;
import com.qianyi.shine.ui.mine.bean.MajorBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/3.
 */

public class MajorFragment extends BaseFragment {
    @BindView(R.id.rv_major)
    public RecyclerView rv_major;
    @BindView(R.id.swipeLayout)
    public SwipeRefreshLayout mSwipeRefreshLayout;
    private MajorAdapter majorAdapter;
    List<MajorBean> infoList;
    private int mNextRequestPage = 1;
    private static final int PAGE_SIZE = 6;
    private View view_major;
    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        view_major=inflater.inflate(R.layout.fragment_major,null);
        return view_major;
    }

    @Override
    protected void initViews() {
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        rv_major.setLayoutManager(new LinearLayoutManager(getActivity()));
        infoList=new ArrayList<>();
        //上拉加载
        initAdapter();
        //下拉刷新
        initRefreshLayout();
        mSwipeRefreshLayout.setRefreshing(true);
        refresh();
    }

    @Override
    protected void initData() {
        for (int i = 0; i <10 ; i++) {
            MajorBean majorBean=new MajorBean();
            infoList.add(majorBean);
        }
    }

    @Override
    protected void initListener() {

    }
    private void initAdapter() {
        majorAdapter = new MajorAdapter(R.layout.item_major, infoList);
        majorAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });
        majorAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        rv_major.setAdapter(majorAdapter);

        rv_major.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                Toast.makeText(getActivity(), Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });
    }
    //刷新
    private void refresh() {
        mNextRequestPage = 1;
        majorAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        apiHome.refresh("http://www.baidu.com", mNextRequestPage, "",new com.qianyi.shine.callbcak.RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, String s) {
                Log.i("ppp", "131" + s);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setData(true, infoList);
                        majorAdapter.setEnableLoadMore(true);
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
                        setData(true, infoList);
                        majorAdapter.setEnableLoadMore(true);
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
                        setData(false, infoList);
                    }
                });

            }

            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        majorAdapter.loadMoreFail();
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
            majorAdapter.setNewData(data);
        } else {
            if (size > 0) {
                majorAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            majorAdapter.loadMoreEnd(isRefresh);
            Toast.makeText(getActivity(), "第一页如果不够一页就不显示没有更多数据布局", Toast.LENGTH_SHORT).show();
        } else {
            majorAdapter.loadMoreComplete();
        }
    }
}
