package com.qianyi.shine.ui.home.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiHome;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.ui.college.adapter.AreaAdapter;
import com.qianyi.shine.ui.college.adapter.GirdDropDownAdapter;
import com.qianyi.shine.ui.home.adapter.PriorityCollegeAdapter;
import com.qianyi.shine.ui.home.bean.PriorityCollegeBean;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/7.
 */

public class PriorityCollegeActivity extends BaseActivity {
    private String[] headers = {"排序","省份","类型"};
    private List<View> popupViews = new ArrayList<>();
    private GirdDropDownAdapter orderAdapter;
    private AreaAdapter areaAdapter,typeAdapter;
    private String[] orderDatas = {"概率", "分数线", "排名"};
    private String[] citys = {"全国", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};
    private String[] types={"综合","理工","财经","农林","医药","师范","体育","政法","艺术","民族","军事","语言"};
    private int constellationPosition = 0;
    private int typeConstellationPosition = 0;
    @BindView(R.id.dropDownMenu)
    public DropDownMenu mDropDownMenu;
    @BindView(R.id.tv_title)
    public TextView tv_title;
    //**************
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private PriorityCollegeAdapter mAdapter;
    public List<PriorityCollegeBean> infoList;
    private int mNextRequestPage = 1;
    private static final int PAGE_SIZE = 6;
    private View view_header;
    @Override
    protected void initViews() {
        view_header=getLayoutInflater().inflate(R.layout.header_priority_college,null);
        tv_title.setText("院校优先填报");
        infoList=new ArrayList<>();
          /*排序*/
        final ListView orderView = new ListView(PriorityCollegeActivity.this);
        orderAdapter = new GirdDropDownAdapter(PriorityCollegeActivity.this, Arrays.asList(orderDatas));
        orderView.setDividerHeight(0);
        orderView.setAdapter(orderAdapter);
        orderView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
              /*  orderAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[3] : orderDatas[position]);
                mDropDownMenu.closeMenu();*/
                orderAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(orderDatas[position]);
                mDropDownMenu.closeMenu();
                Toast.makeText(PriorityCollegeActivity.this, "排序条目", Toast.LENGTH_SHORT).show();
            }
        });
        //省份
        final View areaView = getLayoutInflater().inflate(R.layout.custom_layout, null);
        GridView constellation = areaView.findViewById(R.id.constellation);
        areaAdapter = new AreaAdapter(PriorityCollegeActivity.this, Arrays.asList(citys));
        constellation.setAdapter(areaAdapter);
        TextView ok = areaView.findViewById(R.id.ok);
        /*地区点击选择事件*/
        constellation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                areaAdapter.setCheckItem(position);
                constellationPosition = position;
                Toast.makeText(PriorityCollegeActivity.this, "星座条目", Toast.LENGTH_SHORT).show();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDropDownMenu.setTabText(constellationPosition == 0 ? headers[0] : citys[constellationPosition]);
                mDropDownMenu.closeMenu();
                Toast.makeText(PriorityCollegeActivity.this, "ok", Toast.LENGTH_SHORT).show();
            }
        });
        /*类型*/
        final View typeView = getLayoutInflater().inflate(R.layout.layout_type_menu, null);
        GridView type = typeView.findViewById(R.id.type);
        typeAdapter = new AreaAdapter(PriorityCollegeActivity.this, Arrays.asList(types));
        type.setAdapter(typeAdapter);
        TextView confirm = typeView.findViewById(R.id.confirm);
        /*地区点击选择事件*/
        type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                typeAdapter.setCheckItem(position);
                typeConstellationPosition = position;
                Toast.makeText(PriorityCollegeActivity.this, "星座条目", Toast.LENGTH_SHORT).show();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDropDownMenu.setTabText(typeConstellationPosition == 0 ? headers[0] : types[typeConstellationPosition]);
                mDropDownMenu.closeMenu();
                Toast.makeText(PriorityCollegeActivity.this, "ok", Toast.LENGTH_SHORT).show();
            }
        });
        //init popupViews{添加的顺序决定展示的条目类型}
        popupViews.add(orderView);
        popupViews.add(areaView);
        popupViews.add(typeView);
        //填充布局
        View contentView= LayoutInflater.from(PriorityCollegeActivity.this).inflate(R.layout.layout_refresh,null);
        swipeRefreshLayout=contentView.findViewById(R.id.swipeLayout);
        recyclerView= contentView.findViewById(R.id.rv_list);
        initContentView();
        //init dropdownview
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
        //清空popupviews,否则报tab的数量和popupviews的数量不相等的错
        popupViews.clear();
    }
    @Override
    protected void initData() {
        for (int i = 0; i <10 ; i++) {
            PriorityCollegeBean collegeBean=new PriorityCollegeBean();
            infoList.add(collegeBean);
        }
    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_priority_college);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
    /***
     * 处理加载的布局
     *
     */
    private void initContentView() {
        swipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        recyclerView.setLayoutManager(new LinearLayoutManager(PriorityCollegeActivity.this));
        //上拉加载
        initAdapter();
        //下拉刷新
        initRefreshLayout();
        swipeRefreshLayout.setRefreshing(true);
        refresh();

    }
    private void initAdapter() {
        mAdapter = new PriorityCollegeAdapter(R.layout.item_priority_college,infoList);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mAdapter.setPreLoadNumber(3);
        recyclerView.setAdapter(mAdapter);
        mAdapter.addHeaderView(view_header);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(PriorityCollegeActivity.this, com.qianyi.shine.ui.college.activity.CollegeActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
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
                        setData(true,infoList);
                        mAdapter.setEnableLoadMore(true);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                Log.i("ppp","132"+e);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setData(true,infoList);
                        mAdapter.setEnableLoadMore(true);
                        swipeRefreshLayout.setRefreshing(false);
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
                        setData(false, infoList);
                    }
                });

            }

            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.loadMoreFail();
                        Toast.makeText(PriorityCollegeActivity.this, "网络错误", Toast.LENGTH_LONG).show();
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
            Toast.makeText(PriorityCollegeActivity.this, "第一页如果不够一页就不显示没有更多数据布局", Toast.LENGTH_SHORT).show();
        } else {
            mAdapter.loadMoreComplete();
        }
    }
    @OnClick({R.id.iv_back})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
