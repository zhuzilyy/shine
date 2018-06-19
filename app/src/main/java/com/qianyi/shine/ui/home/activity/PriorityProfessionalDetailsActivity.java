package com.qianyi.shine.ui.home.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
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
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.ui.account.bean.LoginBean;
import com.qianyi.shine.ui.career_planning.entity.SuitableForMeEntity;
import com.qianyi.shine.ui.college.activity.ProfessionalActivity;
import com.qianyi.shine.ui.college.adapter.AreaAdapter;
import com.qianyi.shine.ui.college.adapter.EstablishAdapter;
import com.qianyi.shine.ui.college.adapter.EstablishProiAdapter;
import com.qianyi.shine.ui.college.adapter.GirdDropDownAdapter;
import com.qianyi.shine.ui.gaokao_news.view.XTitleView;
import com.qianyi.shine.ui.home.bean.ProfessionPriorBean;
import com.qianyi.shine.ui.home.bean.SchoolInfo;
import com.qianyi.shine.ui.mine.activity.VipActivity;
import com.qianyi.shine.utils.Utils;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
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

    private String citys[] = {"全国", "北京", "天津", "上海", "重庆", "河北", "山西", "辽宁", "吉林", "黑龙江",
            "江苏", "浙江", "安徽", "福建", "江西", "山东", "河南", "湖北", "湖南", "广东", "海南", "四川", "贵州",
            "云南", "陕西", "甘肃", "青海", "内蒙古", "广西", "宁夏", "新疆","西藏"};
    private String orderDatas[] = {"录取率", "分数线"};


    private int constellationPosition = 0;
    @BindView(R.id.dropDownMenu)
    public DropDownMenu mDropDownMenu;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.ll_openVip)
    LinearLayout ll_openVip;
    @BindView(R.id.rl_vip)
    RelativeLayout rl_vip;
    //**************
    private SwipeRefreshLayout swipeRefreshLayout;
    private RelativeLayout no_data_rl;
    private RecyclerView recyclerView;
    private EstablishProiAdapter mAdapter;

    private int mNextRequestPage = 1;
    private static final int PAGE_SIZE = 6;
    private String major_id = "";
    private String mCity = "";
    private String mOrder = "";
    private String isVip;
    private MyReceiver myReceiver;
    @Override
    protected void initViews() {
        LoginBean.LoginData.LoginInfo loginInfo = Utils.readUser(this);
        isVip=loginInfo.getIs_vip();
        if (isVip.equals("0")){
            ll_openVip.setVisibility(View.VISIBLE);
            rl_vip.setVisibility(View.VISIBLE);
        }else if (isVip.equals("1")){
            ll_openVip.setVisibility(View.GONE);
            rl_vip.setVisibility(View.GONE);
        }
        BaseActivity.addActivity(this);
        tv_title.setText("专业优先");
        major_id = getIntent().getStringExtra("major_id");

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
                mCity = citys[constellationPosition];
                refresh();

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

                if ("录取率".equals(orderDatas[position])) {
                    mOrder = "rate";
                } else if ("分数线".equals(orderDatas[position])) {
                    mOrder = "difen";
                } else if ("排名".equals(orderDatas[position])) {
                    mOrder = "rank";
                }
                refresh();

            }
        });

        constellation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                areaAdapter.setCheckItem(position);
                constellationPosition = position;


                //Toast.makeText(PriorityProfessionalDetailsActivity.this, "星座条目", Toast.LENGTH_SHORT).show();
            }
        });

        //填充布局
        View contentView = LayoutInflater.from(PriorityProfessionalDetailsActivity.this).inflate(R.layout.layout_refresh2, null);
        swipeRefreshLayout = contentView.findViewById(R.id.swipeLayout);
        no_data_rl = contentView.findViewById(R.id.no_data_rl);
        recyclerView = contentView.findViewById(R.id.rv_list);

        initContentView();


        //init dropdownview
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
        //清空popupviews,否则报tab的数量和popupviews的数量不相等的错
        popupViews.clear();


        //注册广播
        myReceiver=new MyReceiver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("com.action.open.vip");
        registerReceiver(myReceiver,intentFilter);

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


    }

    @Override
    protected void initListener() {

    }
    private void initAdapter() {
        mAdapter = new EstablishProiAdapter(PriorityProfessionalDetailsActivity.this);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
//      mAdapter.setPreLoadNumber(3);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<ProfessionPriorBean.ProfessionPriorData.ProfessionPriorInfo.ProfessionInfoList> infoLists=mAdapter.getData();
                Intent intent = new Intent(PriorityProfessionalDetailsActivity.this, PriorityCollegeDetailsActivity.class);
                intent.putExtra("collegeName",infoLists.get(position).getName());
                intent.putExtra("luqulv",infoLists.get(position).getRecruit_students().getRate());
                intent.putExtra("collegeid",infoLists.get(position).getId());

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
        LoginBean.LoginData.LoginInfo user = Utils.readUser(PriorityProfessionalDetailsActivity.this);
        if (user == null) {
            return;
        }
        if (TextUtils.isEmpty(major_id)) {
            return;
        }
       // Toast.makeText(this, "mcity==" + mCity + "      morder==" + mOrder, Toast.LENGTH_SHORT).show();
        mNextRequestPage = 1;
        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        apiHome.majorPriorMajor(apiConstant.PRIOR_MAJOR,mNextRequestPage,user.getId(), major_id, mOrder, "全国".endsWith(mCity)?"":mCity, new com.qianyi.shine.callbcak.RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                Log.i("ppp", "131" + s);
                PriorityProfessionalDetailsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        ProfessionPriorBean priorBean = gson.fromJson(s, ProfessionPriorBean.class);
                        if (priorBean != null) {
                            String code = priorBean.getCode();
                            ProfessionPriorBean.ProfessionPriorData priorData = priorBean.getData();
                            if (priorData != null) {
                                ProfessionPriorBean.ProfessionPriorData.ProfessionPriorInfo priorInfo = priorData.getInfo();
                                if (priorInfo != null) {
                                    List<ProfessionPriorBean.ProfessionPriorData.ProfessionPriorInfo.ProfessionInfoList> infoLists = priorInfo.getInfoList();
                                    if (infoLists!=null && infoLists.size()>0){
                                        setData(true, infoLists);
                                        mAdapter.setEnableLoadMore(true);
                                        swipeRefreshLayout.setRefreshing(false);
                                        swipeRefreshLayout.setVisibility(View.VISIBLE);
                                        no_data_rl.setVisibility(View.GONE);
                                    }else{
                                        swipeRefreshLayout.setVisibility(View.GONE);
                                        no_data_rl.setVisibility(View.VISIBLE);
                                    }
                                }
                            }
                        }
                    }
                });
            }

            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                Log.i("ppp", "132" + e);
                PriorityProfessionalDetailsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // setData(true,list_temp);
                        mAdapter.setEnableLoadMore(true);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
    }

    //加载
    private void loadMore() {
        LoginBean.LoginData.LoginInfo user = Utils.readUser(PriorityProfessionalDetailsActivity.this);
        if (user == null) {
            return;
        }
        if (TextUtils.isEmpty(major_id)) {
            return;
        }
        // Toast.makeText(this, "mcity==" + mCity + "      morder==" + mOrder, Toast.LENGTH_SHORT).show();
        mNextRequestPage++;
        apiHome.majorPriorMajor(apiConstant.PRIOR_MAJOR, mNextRequestPage, user.getId(), major_id, mOrder, "全国".endsWith(mCity)?"":mCity, new com.qianyi.shine.callbcak.RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                Log.i("ppp", "131" + s);
                PriorityProfessionalDetailsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        ProfessionPriorBean priorBean = gson.fromJson(s, ProfessionPriorBean.class);
                        if (priorBean != null) {
                            String code = priorBean.getCode();
                            ProfessionPriorBean.ProfessionPriorData priorData = priorBean.getData();
                            if (priorData != null) {
                                ProfessionPriorBean.ProfessionPriorData.ProfessionPriorInfo priorInfo = priorData.getInfo();
                                if (priorInfo != null) {
                                    List<ProfessionPriorBean.ProfessionPriorData.ProfessionPriorInfo.ProfessionInfoList> infoLists = priorInfo.getInfoList();
                                    if (infoLists!=null){
                                        setData(false, infoLists);
                                        mAdapter.setEnableLoadMore(true);
                                        swipeRefreshLayout.setRefreshing(false);
                                        swipeRefreshLayout.setVisibility(View.VISIBLE);
                                        no_data_rl.setVisibility(View.GONE);
                                    }else{
                                        swipeRefreshLayout.setVisibility(View.GONE);
                                        no_data_rl.setVisibility(View.VISIBLE);
                                    }
                                }
                            }
                        }
                    }
                });
            }

            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                Log.i("ppp", "132" + e);
                PriorityProfessionalDetailsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // setData(true,list_temp);
                        mAdapter.setEnableLoadMore(true);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
    }

    private void setData(boolean isRefresh, List data) {
        // mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            Log.i("uio", "isisisojfsojf===()()()()()");
            mAdapter.setNewData(data);
        } else {
            if (size > 0) {
                mAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            mAdapter.loadMoreEnd(isRefresh);
            //  Toast.makeText(PriorityProfessionalDetailsActivity.this, "第一页如果不够一页就不显示没有更多数据布局", Toast.LENGTH_SHORT).show();
        } else {
            mAdapter.loadMoreComplete();
        }
    }

    @OnClick({R.id.iv_back,R.id.btn_openVip})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_openVip:
                jumpActivity(PriorityProfessionalDetailsActivity.this, VipActivity.class);
                break;
        }
    }
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }
    //接收支付成功的广播
    class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("com.action.open.vip")){
                ll_openVip.setVisibility(View.GONE);
                rl_vip.setVisibility(View.GONE);
                refresh();
            }
        }
    }
}
