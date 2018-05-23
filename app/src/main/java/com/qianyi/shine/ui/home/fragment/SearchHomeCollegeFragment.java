package com.qianyi.shine.ui.home.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.api.apiHome;
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.dialog.CustomLoadingDialog;
import com.qianyi.shine.receiver.MyReceiver;
import com.qianyi.shine.ui.account.activity.WebviewActivity;
import com.qianyi.shine.ui.college.activity.CollegeActivity;
import com.qianyi.shine.ui.home.adapter.HomeSearchCollegeAdapter;
import com.qianyi.shine.ui.home.adapter.SearchCollegeAdapter;
import com.qianyi.shine.ui.home.bean.SearchSchoolBean;
import com.qianyi.shine.ui.home.bean.SearchSchoolListInfo;
import com.qianyi.shine.ui.mine.activity.MessageActivity;
import com.qianyi.shine.ui.mine.adapter.MessageAdapter;
import com.qianyi.shine.ui.mine.bean.CollegeBean;
import com.qianyi.shine.ui.mine.bean.MessageBean;
import com.qianyi.shine.ui.mine.bean.MessageListInfo;
import com.qianyi.shine.ui.mine.bean.UniversityInfo;
import com.qianyi.shine.utils.SPUtils;
import com.qianyi.shine.utils.ToastUtils;
import com.qianyi.shine.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/10.
 */

public class SearchHomeCollegeFragment extends BaseFragment {
    @BindView(R.id.rv_college)
    public RecyclerView rv_college;
    @BindView(R.id.swipeLayout)
    public SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.no_data_rl)
    RelativeLayout no_data_rl;
    @BindView(R.id.no_internet_rl)
    RelativeLayout no_internet_rl;
    @BindView(R.id.reload)
    TextView reload;
    private String keyWord;
    private SearchCollegeAdapter searchCollegeAdapter;
    private CustomLoadingDialog customLoadingDialog;
    private List<SearchSchoolListInfo> infoList;
    private Intent intent;
    private int mNextRequestPage = 1;
    private static final int PAGE_SIZE = 6;
    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_search_homecollege,null);
    }
    @Override
    protected void initViews() {
        customLoadingDialog=new CustomLoadingDialog(getContext());
        intent=getActivity().getIntent();
        if (intent!=null){
            keyWord=intent.getStringExtra("keyWord");
        }
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        rv_college.setLayoutManager(new LinearLayoutManager(getActivity()));
        infoList=new ArrayList<>();
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
    @Override
    protected void initListener() {

    }
    private void initAdapter() {
        searchCollegeAdapter = new SearchCollegeAdapter(R.layout.lay_home_search_college_item);
        searchCollegeAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });
        searchCollegeAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        rv_college.setAdapter(searchCollegeAdapter);

        rv_college.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                List<SearchSchoolListInfo> data = searchCollegeAdapter.getData();
                Intent intent = new Intent(getActivity(), CollegeActivity.class);
                intent.putExtra("id",data.get(position).getId());
                intent.putExtra("name",data.get(position).getName());
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
            searchCollegeAdapter.setNewData(data);
        } else {
            if (size > 0) {
                searchCollegeAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            searchCollegeAdapter.loadMoreEnd(isRefresh);
        } else {
            searchCollegeAdapter.loadMoreComplete();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    //获取数据
    private void refresh() {
        mNextRequestPage = 1;
        searchCollegeAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        apiHome.searchCollege(apiConstant.SEARCH_COLLEGE, keyWord, mNextRequestPage, new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        customLoadingDialog.dismiss();
                        Gson gson=new Gson();
                        SearchSchoolBean searchSchoolBean = gson.fromJson(s, SearchSchoolBean.class);
                        List<SearchSchoolListInfo> resultList = searchSchoolBean.getData().getInfo().getResultList();
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
                        searchCollegeAdapter.setEnableLoadMore(true);
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
        apiHome.searchCollege(apiConstant.SEARCH_COLLEGE, keyWord, mNextRequestPage, new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson=new Gson();
                        SearchSchoolBean searchSchoolBean = gson.fromJson(s, SearchSchoolBean.class);
                        List<SearchSchoolListInfo> resultList = searchSchoolBean.getData().getInfo().getResultList();
                        //数据不为空
                        setData(false,resultList);
                        searchCollegeAdapter.setEnableLoadMore(true);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        searchCollegeAdapter.loadMoreFail();
                    }
                });
            }
        });
    }

}
