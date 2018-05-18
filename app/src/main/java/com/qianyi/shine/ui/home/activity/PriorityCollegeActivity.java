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
import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.ui.account.bean.LoginBean;
import com.qianyi.shine.ui.college.adapter.AreaAdapter;
import com.qianyi.shine.ui.college.adapter.GirdDropDownAdapter;
import com.qianyi.shine.ui.home.adapter.PriorityCollegeAdapter;
import com.qianyi.shine.ui.home.bean.SchoolInfo;
import com.qianyi.shine.ui.home.bean.UniversityBean;
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
 * Created by Administrator on 2018/4/7.
 */

public class PriorityCollegeActivity extends BaseActivity {
    private String[] headers = {"排名","全国","综合"};
    private List<View> popupViews = new ArrayList<>();
    private GirdDropDownAdapter orderAdapter;
    private AreaAdapter areaAdapter,typeAdapter;
    private String[] orderDatas = {"排名","录取率","分数线"};
    private String[] citys =  {"全国","北京","天津","上海","重庆","河北","山西","辽宁","吉林","黑龙江",
            "江苏","浙江","安徽","福建","江西","山东","河南","湖北","湖南","广东","海南","四川","贵州",
            "云南","陕西","甘肃","青海","内蒙古","广西","宁夏","新疆"};
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
    public List<SchoolInfo> infoList;
    private int mNextRequestPage = 1;
    private static final int PAGE_SIZE = 6;
    private View view_header;
    private String member_id,order="rank",area="",school_type="",rate_type="";
    private TextView reload;
    private RelativeLayout no_internet_rl,no_data_rl;
    @Override
    protected void initViews() {
        Intent intent=getIntent();
        if (intent!=null){
            rate_type=intent.getStringExtra("risk");
        }
        BaseActivity.addActivity(this);
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
                orderAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(orderDatas[position]);
                mDropDownMenu.closeMenu();
                String selectOrder=orderDatas[position];
                if (selectOrder.equals("排名")){
                    order="rank";
                }else if(selectOrder.equals("录取率")){
                    order="rate";
                }else if(selectOrder.equals("分数线")){
                    order="difen";
                }
                refresh();
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
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDropDownMenu.setTabText(constellationPosition == 0 ? "全国" : citys[constellationPosition]);
                mDropDownMenu.closeMenu();
                if (citys[constellationPosition].equals("全国")){
                    area="";
                }else{
                    area=citys[constellationPosition];
                }
                refresh();
            }
        });
        /*类型*/
        final View typeView = getLayoutInflater().inflate(R.layout.layout_type_menu, null);
        final GridView type = typeView.findViewById(R.id.type);
        typeAdapter = new AreaAdapter(PriorityCollegeActivity.this, Arrays.asList(types));
        type.setAdapter(typeAdapter);
        TextView confirm = typeView.findViewById(R.id.confirm);
        /*类型点击选择事件*/
        type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                typeAdapter.setCheckItem(position);
                typeConstellationPosition = position;
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDropDownMenu.setTabText(typeConstellationPosition == 0 ?"综合" : types[typeConstellationPosition]);
                mDropDownMenu.closeMenu();
                school_type=types[typeConstellationPosition];
                if (school_type.equals("综合")){
                    school_type="";
                }
                refresh();

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
        reload=contentView.findViewById(R.id.reload);
        no_internet_rl=contentView.findViewById(R.id.no_internet_rl);
        no_data_rl=contentView.findViewById(R.id.no_data_rl);
        //点击重新加载数据
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh();
            }
        });
        initContentView();
        //init dropdownview
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
        //清空popupviews,否则报tab的数量和popupviews的数量不相等的错
        popupViews.clear();



    }
    @Override
    protected void initData() {
       if (!Utils.hasInternet()){
           swipeRefreshLayout.setVisibility(View.GONE);
           no_internet_rl.setVisibility(View.VISIBLE);
           no_data_rl.setVisibility(View.GONE);
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
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
//        mAdapter.setPreLoadNumber(3);
        recyclerView.setAdapter(mAdapter);
        mAdapter.addHeaderView(view_header);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(PriorityCollegeActivity.this,PriorityCollegeDetailsActivity.class);
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
        LoginBean.LoginData.LoginInfo loginInfo = Utils.readUser(this);
        member_id=loginInfo.getId();
        mNextRequestPage = 1;
        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
       apiHome.schoolPrior(apiConstant.SCHOOL_PRiOR, member_id, mNextRequestPage, order,area ,school_type,rate_type, new RequestCallBack<String>() {
           @Override
           public void onSuccess(Call call, Response response, final String s) {
               Log.i("tag",s);
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       Gson gson=new Gson();
                       UniversityBean universityBean = gson.fromJson(s, UniversityBean.class);
                       List<SchoolInfo> priorSchoolList =universityBean.getData().getInfo().getPriorSchoolList();
                       if (priorSchoolList!=null && priorSchoolList.size()>0){
                           setData(true,priorSchoolList);
                           swipeRefreshLayout.setVisibility(View.VISIBLE);
                           no_internet_rl.setVisibility(View.GONE);
                           no_data_rl.setVisibility(View.GONE);
                       }else{
                           swipeRefreshLayout.setVisibility(View.GONE);
                           no_internet_rl.setVisibility(View.GONE);
                           no_data_rl.setVisibility(View.VISIBLE);
                       }
                       mAdapter.setEnableLoadMore(true);
                       swipeRefreshLayout.setRefreshing(false);
                   }
               });
           }
           @Override
           public void onEror(Call call, int statusCode, Exception e) {
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       swipeRefreshLayout.setVisibility(View.GONE);
                       no_internet_rl.setVisibility(View.VISIBLE);
                       no_data_rl.setVisibility(View.GONE);
                       mAdapter.setEnableLoadMore(true);
                       swipeRefreshLayout.setRefreshing(false);
                   }
               });
           }
       });
    }
    //加载
    private void loadMore() {
        mNextRequestPage++;
        apiHome.schoolPrior(apiConstant.SCHOOL_PRiOR, member_id, mNextRequestPage, order,area , school_type, rate_type,new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson=new Gson();
                        UniversityBean universityBean = gson.fromJson(s, UniversityBean.class);
                        List<SchoolInfo> universityList = universityBean.getData().getInfo().getPriorSchoolList();
                        //数据不为空
                        setData(false,universityList);
                        mAdapter.setEnableLoadMore(true);
                        swipeRefreshLayout.setRefreshing(false);

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
            //Toast.makeText(PriorityCollegeActivity.this, "第一页如果不够一页就不显示没有更多数据布局", Toast.LENGTH_SHORT).show();
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
