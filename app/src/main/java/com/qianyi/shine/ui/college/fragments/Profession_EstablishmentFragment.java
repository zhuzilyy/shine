package com.qianyi.shine.ui.college.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.fragment.adapter.GridAdapter;
import com.qianyi.shine.fragment.adapter.PullToRefreshAdapter;
import com.qianyi.shine.ui.account.activity.WebviewActivity;
import com.qianyi.shine.ui.college.adapter.AreaAdapter;
import com.qianyi.shine.ui.college.adapter.GirdDropDownAdapter;
import com.qianyi.shine.ui.college.adapter.ListDropDownAdapter;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/3.
 */

public class Profession_EstablishmentFragment extends BaseFragment {
    private String headers[] = {"排序", "省份", "类型"};
    private List<View> popupViews = new ArrayList<>();


    private GirdDropDownAdapter orderAdapter;
    private GirdDropDownAdapter typeAdapter;
    private AreaAdapter areaAdapter;

    private String citys[] = {"不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};
    private String orderDatas[] = {"概率", "分数线", "排名"};
    private String typeDatas[] = {"提前批", "一本", "二本"};

    private int constellationPosition = 0;
    @BindView(R.id.dropDownMenu)
    public DropDownMenu mDropDownMenu;

    //**************
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_profession_establish,null);
    }

    @Override
    protected void initViews() {

        //省份
        final View areaView = getLayoutInflater().inflate(R.layout.custom_layout, null);
        GridView constellation = areaView.findViewById(R.id.constellation);
        areaAdapter = new AreaAdapter(getActivity(), Arrays.asList(citys));
        constellation.setAdapter(areaAdapter);
        TextView ok = areaView.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDropDownMenu.setTabText(constellationPosition == 0 ? headers[1] : citys[constellationPosition]);
                mDropDownMenu.closeMenu();
                Toast.makeText(mActivity, "ok", Toast.LENGTH_SHORT).show();
            }
        });

        //排序
        final ListView orderView = new ListView(getActivity());
        orderAdapter = new GirdDropDownAdapter(getActivity(), Arrays.asList(orderDatas));
        orderView.setDividerHeight(0);
        orderView.setAdapter(orderAdapter);

        //类型
        final ListView typeView = new ListView(getActivity());
        typeView.setDividerHeight(0);
        typeAdapter = new GirdDropDownAdapter(getActivity(), Arrays.asList(typeDatas));
        typeView.setAdapter(orderAdapter);




        //init popupViews{添加的顺序决定展示的条目类型}
        popupViews.add(orderView);
        popupViews.add(areaView);
        popupViews.add(typeView);


        //add item click event
        orderView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                orderAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[0] : orderDatas[position]);
                mDropDownMenu.closeMenu();
                Toast.makeText(mActivity, "排序条目", Toast.LENGTH_SHORT).show();
            }
        });

        typeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                typeAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[2] : typeDatas[position]);
                mDropDownMenu.closeMenu();
                Toast.makeText(mActivity, "类型条目", Toast.LENGTH_SHORT).show();

            }
        });
        constellation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                areaAdapter.setCheckItem(position);
                constellationPosition = position;
                Toast.makeText(mActivity, "星座条目", Toast.LENGTH_SHORT).show();
            }
        });

        //填充布局
        View contentView= LayoutInflater.from(getActivity()).inflate(R.layout.layout_refresh,null);
        swipeRefreshLayout=contentView.findViewById(R.id.swipeLayout);
        recyclerView= contentView.findViewById(R.id.rv_list);
        initContentView(recyclerView,swipeRefreshLayout);


        //init dropdownview
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
        //清空popupviews,否则报tab的数量和popupviews的数量不相等的错
        popupViews.clear();

    }

    /***
     * 处理加载的布局
     * @param recyclerView
     * @param swipeRefreshLayout
     */
    private void initContentView(RecyclerView recyclerView, SwipeRefreshLayout swipeRefreshLayout) {
        swipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        //上拉加载
//        initAdapter();
//        //添加head
//        addHeadView();
//        //下拉刷新
//        initRefreshLayout();
//        mSwipeRefreshLayout.setRefreshing(true);
//        refresh();

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }




}
