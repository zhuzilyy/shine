package com.qianyi.shine.ui.mine.fragment;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.qianyi.shine.ui.home.activity.OccupationDetailActivity;
import com.qianyi.shine.ui.mine.adapter.ProfessionAdapter;
import com.qianyi.shine.ui.mine.bean.CollectionJobBean;
import com.qianyi.shine.ui.mine.bean.CollectionJobListInfo;
import com.qianyi.shine.ui.mine.bean.ProfessionBean;
import com.qianyi.shine.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/3.
 */

public class ProfessionFragment extends BaseFragment {
    @BindView(R.id.rv_profession)
    public RecyclerView rv_profession;
    @BindView(R.id.swipeLayout)
    public SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.no_internet_rl)
    RelativeLayout no_internet_rl;
    @BindView(R.id.no_data_rl)
    RelativeLayout no_data_rl;
    @BindView(R.id.reload)
    TextView reload;
    private ProfessionAdapter professionAdapter;
    List<ProfessionBean> infoList;
    private int mNextRequestPage = 1;
    private static final int PAGE_SIZE = 6;
    private View view_profession;
    private CustomLoadingDialog customLoadingDialog;
    private String memberId;
    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        view_profession=inflater.inflate(R.layout.fragment_profession,null);
        return view_profession;
    }
    @Override
    protected void initViews() {
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        rv_profession.setLayoutManager(new LinearLayoutManager(getActivity()));
        infoList=new ArrayList<>();
        //上拉加载
        initAdapter();
        //下拉刷新
        initRefreshLayout();
        mSwipeRefreshLayout.setRefreshing(true);
       // refresh();
        LoginBean.LoginData.LoginInfo loginInfo = Utils.readUser(getActivity());
        memberId= loginInfo.getId();

        customLoadingDialog=new CustomLoadingDialog(getActivity());
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
        professionAdapter = new ProfessionAdapter(R.layout.item_major);
        professionAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });
        professionAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        rv_profession.setAdapter(professionAdapter);

        rv_profession.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                List<CollectionJobListInfo> data = professionAdapter.getData();
                Intent intent=new Intent(getActivity(), OccupationDetailActivity.class);
                intent.putExtra("occupationName",data.get(position).getJob_name());
                String job_cate_two_name = data.get(position).getJob_cate_two_name();
                if (TextUtils.isEmpty(job_cate_two_name)){
                    intent.putExtra("occupationParentName",data.get(position).getJob_name());
                }else{
                    intent.putExtra("occupationParentName",data.get(position).getJob_cate_two_name());
                }
                getActivity().startActivity(intent);
            }
        });
    }
    //刷新
    private void refresh() {
        mNextRequestPage = 1;
        professionAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        apiHome.focusJobList(apiConstant.FOCUS_JOB_LIST, memberId, mNextRequestPage, new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        customLoadingDialog.dismiss();
                        Gson gson=new Gson();
                        CollectionJobBean collectionJobBean = gson.fromJson(s, CollectionJobBean.class);
                        List<CollectionJobListInfo> collectionJobList = collectionJobBean.getData().getInfo().getCollectionJobList();
                        if (collectionJobList!=null && collectionJobList.size()>0){
                            setData(true,collectionJobList);
                            mSwipeRefreshLayout.setVisibility(View.VISIBLE);
                            no_data_rl.setVisibility(View.GONE);
                            no_internet_rl.setVisibility(View.GONE);
                        }else{
                            mSwipeRefreshLayout.setVisibility(View.GONE);
                            no_data_rl.setVisibility(View.VISIBLE);
                            no_internet_rl.setVisibility(View.GONE);
                        }
                        professionAdapter.setEnableLoadMore(true);
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
        apiHome.focusJobList(apiConstant.FOCUS_JOB_LIST, memberId, mNextRequestPage, new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson=new Gson();
                        CollectionJobBean collectionJobBean = gson.fromJson(s, CollectionJobBean.class);
                        List<CollectionJobListInfo> collectionJobList = collectionJobBean.getData().getInfo().getCollectionJobList();
                        //数据不为空
                        setData(false,collectionJobList);
                        professionAdapter.setEnableLoadMore(true);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        professionAdapter.loadMoreFail();
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
            professionAdapter.setNewData(data);
        } else {
            if (size > 0) {
                professionAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            professionAdapter.loadMoreEnd(isRefresh);
        } else {
            professionAdapter.loadMoreComplete();
        }
    }
}
