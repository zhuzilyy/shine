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
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.api.apiHome;
import com.qianyi.shine.api.apiMine;
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.ui.account.bean.LoginBean;
import com.qianyi.shine.ui.career_planning.activity.CharacterResultActivity;
import com.qianyi.shine.ui.career_planning.activity.InterestResultActivity;
import com.qianyi.shine.ui.career_planning.activity.TestActivity;
import com.qianyi.shine.ui.mine.adapter.MeasurementAdapter;
import com.qianyi.shine.ui.mine.bean.MeasurementBean;
import com.qianyi.shine.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/3.
 */

public class MeasurementFragment extends BaseFragment {
    @BindView(R.id.rv_measurement)
    public RecyclerView rv_measurement;
    @BindView(R.id.swipeLayout)
    public SwipeRefreshLayout mSwipeRefreshLayout;
    private MeasurementAdapter measurementAdapter;
    List<MeasurementBean.MeasurementData.MeasurementInfo.CollectionJobList> infoList;
    private int mNextRequestPage = 1;
    private static final int PAGE_SIZE = 6;
    private View view_measurement;
    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        view_measurement=inflater.inflate(R.layout.fragment_measurement,null);
        return view_measurement;
    }
    @Override
    protected void initViews() {
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        rv_measurement.setLayoutManager(new LinearLayoutManager(getActivity()));
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

    }

    @Override
    protected void initListener() {


    }
    private void initAdapter() {
        measurementAdapter = new MeasurementAdapter(R.layout.item_measurement, infoList);
        measurementAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });
        measurementAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        rv_measurement.setAdapter(measurementAdapter);


        rv_measurement.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                List<MeasurementBean.MeasurementData.MeasurementInfo.CollectionJobList> infoList= measurementAdapter.getData();
                if("1".equals(infoList.get(position).getType())){
                    //霍兰德须兴趣测评
                    Intent intent =new Intent(getActivity(), InterestResultActivity.class);
                    intent.putExtra("InterestResult",infoList.get(position).getKeystring());
                    startActivity(intent);

                }else if("0".equals(infoList.get(position).getType())){
                    //性格
                    Intent intent =new Intent(getActivity(), CharacterResultActivity.class);
                    intent.putExtra("CharatorResult",infoList.get(position).getKeystring());
                    startActivity(intent);
                }
            }
        });




    }
    //刷新
    private void refresh() {
        LoginBean.LoginData.LoginInfo user = Utils.readUser(getActivity());
        if(user== null){
            return;
        }
        mNextRequestPage = 1;
        measurementAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        apiMine.measurementReport(apiConstant.Evaluationeport,user.getId(), mNextRequestPage+"", new com.qianyi.shine.callbcak.RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                Log.i("ppp", "131" + s);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("ss",s);
                        Gson gson =new Gson();
                        MeasurementBean measurementBean = gson.fromJson(s,MeasurementBean.class);
                        if(measurementBean!=null){
                            MeasurementBean.MeasurementData measurementData= measurementBean.getData();
                            if(measurementData!=null){
                                final MeasurementBean.MeasurementData.MeasurementInfo measurementInfo =measurementData.getInfo();
                                if(measurementInfo!=null){
                                    setData(true,  measurementInfo.getCollectionJobList());
                                    measurementAdapter.setEnableLoadMore(true);
                                    mSwipeRefreshLayout.setRefreshing(false);

                                }
                            }
                        }
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
                        measurementAdapter.setEnableLoadMore(true);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
    }
    //加载
    private void loadMore() {

        LoginBean.LoginData.LoginInfo user = Utils.readUser(getActivity());
        if(user== null){
            return;
        }
        apiMine.measurementReport(apiConstant.Evaluationeport,user.getId(), mNextRequestPage+"", new com.qianyi.shine.callbcak.RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                Log.i("ppp", "131" + s);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("ss",s);
                        Gson gson =new Gson();
                        MeasurementBean measurementBean = gson.fromJson(s,MeasurementBean.class);
                        if(measurementBean!=null){
                            MeasurementBean.MeasurementData measurementData= measurementBean.getData();
                            if(measurementData!=null){
                                final MeasurementBean.MeasurementData.MeasurementInfo measurementInfo =measurementData.getInfo();
                                if(measurementInfo!=null){
                                    setData(false,  measurementInfo.getCollectionJobList());
                                    measurementAdapter.setEnableLoadMore(true);
                                    mSwipeRefreshLayout.setRefreshing(false);


                                }

                            }
                        }



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
                        measurementAdapter.setEnableLoadMore(true);
                        mSwipeRefreshLayout.setRefreshing(false);
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
            measurementAdapter.setNewData(data);
        } else {
            if (size > 0) {
                measurementAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            measurementAdapter.loadMoreEnd(isRefresh);
        } else {
            measurementAdapter.loadMoreComplete();
        }
    }
}
