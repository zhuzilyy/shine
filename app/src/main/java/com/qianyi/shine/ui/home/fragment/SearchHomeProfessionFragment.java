package com.qianyi.shine.ui.home.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.api.apiHome;
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.dialog.CustomLoadingDialog;
import com.qianyi.shine.ui.college.activity.ProfessionalActivity;
import com.qianyi.shine.ui.home.adapter.SearchMajorAdapter;
import com.qianyi.shine.ui.home.bean.SearchMajorBean;
import com.qianyi.shine.ui.home.bean.SearchMajorInfo;
import com.qianyi.shine.utils.Utils;

import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/10.
 */

public class SearchHomeProfessionFragment extends BaseFragment {
    @BindView(R.id.rv_major)
    public RecyclerView rv_major;
    @BindView(R.id.swipeLayout)
    public SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.no_data_rl)
    RelativeLayout no_data_rl;
    @BindView(R.id.no_internet_rl)
    RelativeLayout no_internet_rl;
    @BindView(R.id.reload)
    TextView reload;
    private Intent intent;
    private int mNextRequestPage = 1;
    private static final int PAGE_SIZE = 6;
    private CustomLoadingDialog customLoadingDialog;
    private String keyWord;
    private SearchMajorAdapter searchMajorAdapter;
    /*@BindView(R.id.listView)
    public ListView mList;*/
    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_search_homeprofession,null);
    }
    @Override
    protected void initViews() {
        customLoadingDialog=new CustomLoadingDialog(getContext());
        intent=getActivity().getIntent();
        if (intent!=null){
            keyWord=intent.getStringExtra("keyWord");
        }
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        rv_major.setLayoutManager(new LinearLayoutManager(getActivity()));
        //上拉加载
        initAdapter();
        //下拉刷新
        initRefreshLayout();
        mSwipeRefreshLayout.setRefreshing(true);

    }

    @Override
    protected void initData() {
        if (!Utils.hasInternet()){
            mSwipeRefreshLayout.setVisibility(View.GONE);
            no_internet_rl.setVisibility(View.VISIBLE);
            no_data_rl.setVisibility(View.GONE);
        }else{
            customLoadingDialog.show();
            refresh();
        }
        //点击重新加载数据
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh();
            }
        });
    }
    //获取数据
    private void refresh() {
        mNextRequestPage = 1;
        searchMajorAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        apiHome.searchMajor(apiConstant.SEARCH_MAJOR, keyWord, mNextRequestPage, new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        customLoadingDialog.dismiss();
                        Gson gson=new Gson();
                        SearchMajorBean searchMajorBean = gson.fromJson(s, SearchMajorBean.class);
                        List<SearchMajorInfo> resultList = searchMajorBean.getData().getInfo().getResultList();
                        if (resultList!=null&& resultList.size()>0){
                            setData(true,resultList);
                            mSwipeRefreshLayout.setVisibility(View.VISIBLE);
                            no_data_rl.setVisibility(View.GONE);
                            no_internet_rl.setVisibility(View.GONE);
                        }else{
                            mSwipeRefreshLayout.setVisibility(View.GONE);
                            no_data_rl.setVisibility(View.VISIBLE);
                            no_internet_rl.setVisibility(View.GONE);
                        }
                        searchMajorAdapter.setEnableLoadMore(true);
                        mSwipeRefreshLayout.setRefreshing(false);

                    }
                });
            }
            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                customLoadingDialog.dismiss();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setVisibility(View.GONE);
                        no_data_rl.setVisibility(View.GONE);
                        no_internet_rl.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }

    //加载
    private void loadMore() {
        mNextRequestPage++;
        apiHome.searchMajor(apiConstant.SEARCH_MAJOR, keyWord, mNextRequestPage, new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson=new Gson();
                        SearchMajorBean searchMajorBean = gson.fromJson(s, SearchMajorBean.class);
                        List<SearchMajorInfo> resultList = searchMajorBean.getData().getInfo().getResultList();
                        //数据不为空
                        setData(false,resultList);
                        searchMajorAdapter.setEnableLoadMore(true);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        searchMajorAdapter.loadMoreFail();
                    }
                });
            }
        });
    }
    private void initAdapter() {
        searchMajorAdapter = new SearchMajorAdapter(R.layout.lay_home_search_profession_item);
        searchMajorAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });
        searchMajorAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        rv_major.setAdapter(searchMajorAdapter);

        rv_major.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                List<SearchMajorInfo> data = searchMajorAdapter.getData();
                String id = data.get(position).getId();
                String major_name = data.get(position).getMajor_name();
                Intent intent=new Intent(getActivity(), ProfessionalActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("name",major_name);
                startActivity(intent);
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
            searchMajorAdapter.setNewData(data);
        } else {
            if (size > 0) {
                searchMajorAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            searchMajorAdapter.loadMoreEnd(isRefresh);
        } else {
            searchMajorAdapter.loadMoreComplete();
        }
    }

    @Override
    protected void initListener() {

    }
}
