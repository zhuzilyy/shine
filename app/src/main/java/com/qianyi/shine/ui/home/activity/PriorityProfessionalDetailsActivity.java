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
import com.qianyi.shine.ui.career_planning.entity.SuitableForMeEntity;
import com.qianyi.shine.ui.college.adapter.AreaAdapter;
import com.qianyi.shine.ui.college.adapter.EstablishAdapter;
import com.qianyi.shine.ui.college.adapter.GirdDropDownAdapter;
import com.qianyi.shine.ui.gaokao_news.view.XTitleView;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/9.
 */

public class PriorityProfessionalDetailsActivity extends BaseActivity {
    private String headers[] = {"排序", "省份"};
    private List<View> popupViews = new ArrayList<>();


    private GirdDropDownAdapter orderAdapter;
    private AreaAdapter areaAdapter;

    private String citys[] = {"不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};
    private String orderDatas[] = {"概率", "分数线", "排名"};
  

    private int constellationPosition = 0;
    @BindView(R.id.dropDownMenu)
    public DropDownMenu mDropDownMenu;

    //**************
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private EstablishAdapter mAdapter;
    public List<SuitableForMeEntity> list_temp;
    private int mNextRequestPage = 1;
    private static final int PAGE_SIZE = 6;
    @BindView(R.id.title)
    public XTitleView title;

    @Override
    protected void initViews() {
        BaseActivity.addActivity(this);
        title.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //省份
        final View areaView = getLayoutInflater().inflate(R.layout.custom_layout, null);
        GridView constellation = areaView.findViewById(R.id.constellation);
        areaAdapter = new AreaAdapter(PriorityProfessionalDetailsActivity.this, Arrays.asList(citys));
        constellation.setAdapter(areaAdapter);
        TextView ok = areaView.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDropDownMenu.setTabText(constellationPosition == 0 ? headers[1] : citys[constellationPosition]);
                mDropDownMenu.closeMenu();
                Toast.makeText(PriorityProfessionalDetailsActivity.this, "ok", Toast.LENGTH_SHORT).show();
            }
        });

        //排序
        final ListView orderView = new ListView(PriorityProfessionalDetailsActivity.this);
        orderAdapter = new GirdDropDownAdapter(PriorityProfessionalDetailsActivity.this, Arrays.asList(orderDatas));
        orderView.setDividerHeight(0);
        orderView.setAdapter(orderAdapter);


        //init popupViews{添加的顺序决定展示的条目类型}
        popupViews.add(orderView);
        popupViews.add(areaView);
      

        //add item click event
        orderView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                orderAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[0] : orderDatas[position]);
                mDropDownMenu.closeMenu();
                Toast.makeText(PriorityProfessionalDetailsActivity.this, "排序条目", Toast.LENGTH_SHORT).show();
            }
        });

        constellation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                areaAdapter.setCheckItem(position);
                constellationPosition = position;
                Toast.makeText(PriorityProfessionalDetailsActivity.this, "星座条目", Toast.LENGTH_SHORT).show();
            }
        });

        //填充布局
        View contentView= LayoutInflater.from(PriorityProfessionalDetailsActivity.this).inflate(R.layout.layout_refresh,null);
        swipeRefreshLayout=contentView.findViewById(R.id.swipeLayout);
        recyclerView= contentView.findViewById(R.id.rv_list);
        initContentView();


        //init dropdownview
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
        //清空popupviews,否则报tab的数量和popupviews的数量不相等的错
        popupViews.clear();
        

    }

  
    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_priority_details_profession);
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
        recyclerView.setLayoutManager(new LinearLayoutManager(PriorityProfessionalDetailsActivity.this));

        //上拉加载
        initAdapter();

        //下拉刷新
        initRefreshLayout();
        swipeRefreshLayout.setRefreshing(true);
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
        mAdapter = new EstablishAdapter(R.layout.priority_profession_item,list_temp);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//      mAdapter.setPreLoadNumber(3);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                Intent intent=new Intent(PriorityProfessionalDetailsActivity.this,PriorityCollegeDetailsActivity.class);
                PriorityProfessionalDetailsActivity.this.startActivity(intent);
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
        //Toast.makeText(PriorityProfessionalDetailsActivity.this, "1346458465", Toast.LENGTH_SHORT).show();
        mNextRequestPage = 1;
        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        apiHome.refresh("http://www.baidu.com", mNextRequestPage, new com.qianyi.shine.callbcak.RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, String s) {
                Log.i("ppp","131"+s);
                PriorityProfessionalDetailsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setData(true,list_temp);
                        mAdapter.setEnableLoadMore(true);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                Log.i("ppp","132"+e);
                PriorityProfessionalDetailsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setData(true,list_temp);
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
                PriorityProfessionalDetailsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setData(false, list_temp);
                    }
                });

            }

            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                PriorityProfessionalDetailsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.loadMoreFail();
                        Toast.makeText(PriorityProfessionalDetailsActivity.this, "网络错误", Toast.LENGTH_LONG).show();
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
            Toast.makeText(PriorityProfessionalDetailsActivity.this, "第一页如果不够一页就不显示没有更多数据布局", Toast.LENGTH_SHORT).show();
        } else {
            mAdapter.loadMoreComplete();
        }
    }

}
