package com.qianyi.shine.ui.mine.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.qianyi.shine.ui.account.bean.LoginBean;
import com.qianyi.shine.ui.college.activity.CollegeActivity;
import com.qianyi.shine.ui.home.activity.CollegeListActivity;
import com.qianyi.shine.ui.home.bean.CollectionCollegeInfo;
import com.qianyi.shine.ui.home.bean.CollectionSchoolInfo;
import com.qianyi.shine.ui.home.bean.FocusCollegeBean;
import com.qianyi.shine.ui.mine.adapter.CollectionCollegeAdapter;
import com.qianyi.shine.ui.mine.adapter.CollegeAdapter;
import com.qianyi.shine.ui.mine.bean.CollegeBean;
import com.qianyi.shine.ui.mine.bean.UniversityInfo;
import com.qianyi.shine.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/3.
 */

public class CollegeFragment extends BaseFragment{
    @BindView(R.id.rv_college)
    RecyclerView rv_college;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.no_internet_rl)
    RelativeLayout no_internet_rl;
    @BindView(R.id.no_data_rl)
    RelativeLayout no_data_rl;
    @BindView(R.id.reload)
    TextView reload;
    private CollectionCollegeAdapter collegeAdapter;
    private int mNextRequestPage = 1;
    private static final int PAGE_SIZE = 6;
    private View view_college;
    private CustomLoadingDialog customLoadingDialog;
    private String memberId;
    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        view_college=inflater.inflate(R.layout.fragment_college,null);
        return view_college;
    }
    @Override
    protected void initViews() {
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        rv_college.setLayoutManager(new LinearLayoutManager(getActivity()));
        //上拉加载
        initAdapter();
        //下拉刷新
        initRefreshLayout();
        mSwipeRefreshLayout.setRefreshing(true);
        customLoadingDialog=new CustomLoadingDialog(getActivity());
        LoginBean.LoginData.LoginInfo loginInfo = Utils.readUser(getActivity());
        memberId= loginInfo.getId();
    }
    @Override
    protected void initData() {
        if (!Utils.hasInternet()){
            mSwipeRefreshLayout.setVisibility(View.GONE);
            no_data_rl.setVisibility(View.GONE);
            no_internet_rl.setVisibility(View.VISIBLE);
        }else{
            customLoadingDialog.show();
            refresh();
        }
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customLoadingDialog.show();
                refresh();
            }
        });
    }
    @Override
    protected void initListener() {

    }
    private void initAdapter() {
        collegeAdapter = new CollectionCollegeAdapter(R.layout.item_college);
        collegeAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });
        collegeAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        rv_college.setAdapter(collegeAdapter);
        rv_college.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                List<CollectionSchoolInfo> data = collegeAdapter.getData();
                Intent intent=new Intent(getActivity(), CollegeActivity.class);
                intent.putExtra("id",data.get(position).getId());
                intent.putExtra("name",data.get(position).getName());
                getActivity().startActivity(intent);
            }
        });
    }
    //刷新
    private void refresh() {
        mNextRequestPage = 1;
        collegeAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        apiHome.focusCollegeList(apiConstant.FOCUS_COLLEGE_LIST, memberId, mNextRequestPage, new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                customLoadingDialog.dismiss();
                Log.i("tag",s);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        List<CollectionSchoolInfo> infoList=new ArrayList<>();
                        Gson gson=new Gson();
                        FocusCollegeBean focusCollegeBean = gson.fromJson(s, FocusCollegeBean.class);
                        List<CollectionCollegeInfo> collectionSchoolList = focusCollegeBean.getData().getInfo().getCollectionSchoolList();
                        for (int i = 0; i <collectionSchoolList.size() ; i++) {
                            CollectionSchoolInfo schoolinfo = collectionSchoolList.get(i).getSchoolinfo();
                            infoList.add(schoolinfo);
                        }
                        if (infoList.size()>0){
                            setData(true,infoList);
                            mSwipeRefreshLayout.setVisibility(View.VISIBLE);
                            no_data_rl.setVisibility(View.GONE);
                            no_internet_rl.setVisibility(View.GONE);
                        }else{
                            mSwipeRefreshLayout.setVisibility(View.GONE);
                            no_data_rl.setVisibility(View.VISIBLE);
                            no_internet_rl.setVisibility(View.GONE);
                        }
                        collegeAdapter.setEnableLoadMore(true);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                Log.i("tag",e.getMessage());
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
        apiHome.focusCollegeList(apiConstant.FOCUS_COLLEGE_LIST, memberId, mNextRequestPage, new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        List<CollectionSchoolInfo> infoList=new ArrayList<>();
                        Gson gson=new Gson();
                        FocusCollegeBean focusCollegeBean = gson.fromJson(s, FocusCollegeBean.class);
                        List<CollectionCollegeInfo> collectionSchoolList = focusCollegeBean.getData().getInfo().getCollectionSchoolList();
                        for (int i = 0; i <collectionSchoolList.size() ; i++) {
                            CollectionSchoolInfo schoolinfo = collectionSchoolList.get(i).getSchoolinfo();
                            infoList.add(schoolinfo);
                        }
                        //数据不为空
                        setData(false,infoList);
                        collegeAdapter.setEnableLoadMore(true);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        collegeAdapter.loadMoreFail();
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
            collegeAdapter.setNewData(data);
        } else {
            if (size > 0) {
                collegeAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            collegeAdapter.loadMoreEnd(isRefresh);
        } else {
            collegeAdapter.loadMoreComplete();
        }
    }
}
