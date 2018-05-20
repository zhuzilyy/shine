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
import com.qianyi.shine.dialog.CustomLoadingDialog;
import com.qianyi.shine.ui.college.adapter.AreaAdapter;
import com.qianyi.shine.ui.college.adapter.GirdDropDownAdapter;
import com.qianyi.shine.ui.mine.adapter.CollegeAdapter;
import com.qianyi.shine.ui.mine.bean.CollegeBean;
import com.qianyi.shine.ui.mine.bean.UniversityInfo;
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
 * Created by Administrator on 2018/4/6.
 */

public class CollegeListActivity extends BaseActivity {
    private String[] headers = {"全国", "类型", "批次","位次"};
    private List<View> popupViews = new ArrayList<>();
    private GirdDropDownAdapter orderAdapter;
    private GirdDropDownAdapter typeAdapter;
    private GirdDropDownAdapter batchAdapter;
    private AreaAdapter areaAdapter;
    private String[] citys = {"全国","北京","天津","上海","重庆","河北","山西","辽宁","吉林","黑龙江",
            "江苏","浙江","安徽","福建","江西","山东","河南","湖北","湖南","广东","海南","四川","贵州",
            "云南","陕西","甘肃","青海","内蒙古","广西","宁夏","新疆"};
    private String[] types={"985","211","默认"};
    private String[] batch= {"本科", "专科"};
    private String[] orderDatas = {"概率", "分数线", "排名"};
    private int constellationPosition = 0;
    @BindView(R.id.dropDownMenu)
    public DropDownMenu mDropDownMenu;
    @BindView(R.id.tv_title)
    public TextView tv_title;

    //**************
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private CollegeAdapter mAdapter;
    private int mNextRequestPage = 1;
    private static final int PAGE_SIZE = 6;
    private Intent intent;
    private String order,area,level,is_type,school_type,keyword;
    private TextView reload;
    private RelativeLayout no_internet_rl,no_data_rl;
    private CustomLoadingDialog customLoadingDialog;
    @Override
    protected void initViews() {
        BaseActivity.addActivity(this);
        tv_title.setText("大学列表");
        customLoadingDialog=new CustomLoadingDialog(this);
        //省份
        final View areaView = getLayoutInflater().inflate(R.layout.custom_layout, null);
        GridView constellation = areaView.findViewById(R.id.constellation);
        areaAdapter = new AreaAdapter(CollegeListActivity.this, Arrays.asList(citys));
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
                mDropDownMenu.setTabText(constellationPosition == 0 ? headers[0] : citys[constellationPosition]);
                mDropDownMenu.closeMenu();
                if (citys[constellationPosition].equals("全国")){
                    area="";
                }else{
                    area=citys[constellationPosition];
                }
                refresh();
            }
        });
        //类型
        final ListView typeView = new ListView(CollegeListActivity.this);
        typeView.setDividerHeight(0);
        typeAdapter = new GirdDropDownAdapter(CollegeListActivity.this, Arrays.asList(types));
        typeView.setAdapter(typeAdapter);
        typeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                typeAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(types[position]);
                mDropDownMenu.closeMenu();
                if (types[position].equals("默认")){
                    is_type="";
                }else{
                    is_type=types[position];
                }
                refresh();
            }
        });
        //批次
        final ListView batchView = new ListView(CollegeListActivity.this);
        batchView.setDividerHeight(0);
        batchAdapter = new GirdDropDownAdapter(CollegeListActivity.this, Arrays.asList(batch));
        batchView.setAdapter(batchAdapter);
        batchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                batchAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(batch[position]);
                mDropDownMenu.closeMenu();
            }
        });
        /*位次*/
        final ListView orderView = new ListView(CollegeListActivity.this);
        orderAdapter = new GirdDropDownAdapter(CollegeListActivity.this, Arrays.asList(orderDatas));
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
                Toast.makeText(CollegeListActivity.this, "排序条目", Toast.LENGTH_SHORT).show();
            }
        });
        //init popupViews{添加的顺序决定展示的条目类型}
        popupViews.add(areaView);
        popupViews.add(typeView);
        popupViews.add(batchView);
        popupViews.add(orderView);

        //填充布局
        View contentView= LayoutInflater.from(CollegeListActivity.this).inflate(R.layout.layout_refresh,null);
        reload=contentView.findViewById(R.id.reload);
        no_internet_rl=contentView.findViewById(R.id.no_internet_rl);
        no_data_rl=contentView.findViewById(R.id.no_data_rl);

        swipeRefreshLayout=contentView.findViewById(R.id.swipeLayout);
        recyclerView= contentView.findViewById(R.id.rv_list);
        //init dropdownview
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
        //清空popupviews,否则报tab的数量和popupviews的数量不相等的错
        popupViews.clear();
        intent=getIntent();
        if (intent!=null){
            order=intent.getStringExtra("order");
            area=intent.getStringExtra("area");
            level=intent.getStringExtra("level");
            is_type=intent.getStringExtra("is_type");
            school_type=intent.getStringExtra("school_type");
            keyword=intent.getStringExtra("keyword");
        }
        initContentView();
    }
    @Override
    protected void initData() {
        //网络错误时候的界面
        if (!Utils.hasInternet()){
            swipeRefreshLayout.setVisibility(View.GONE);
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
    protected void getResLayout() {
        setContentView(R.layout.activity_college_list);
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
        recyclerView.setLayoutManager(new LinearLayoutManager(CollegeListActivity.this));
        //上拉加载
        initAdapter();
        //下拉刷新
        initRefreshLayout();
        swipeRefreshLayout.setRefreshing(true);
        refresh();

    }
    private void initAdapter() {
        mAdapter = new CollegeAdapter(R.layout.item_college);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);

        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<UniversityInfo> infos =adapter.getData();
                Intent intent=new Intent(CollegeListActivity.this, com.qianyi.shine.ui.college.activity.CollegeActivity.class);
                intent.putExtra("id",infos.get(position).getId());
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
        apiHome.getCollegeList(apiConstant.FIND_COLLEGE,order,area,level,is_type,school_type,keyword,mNextRequestPage, new com.qianyi.shine.callbcak.RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
               runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        customLoadingDialog.dismiss();
                        Log.i("tag",s);
                        Gson gson=new Gson();
                        CollegeBean collegeBean = gson.fromJson(s, CollegeBean.class);
                        List<UniversityInfo> universityList = collegeBean.getData().getInfo().getRecommendUniversityList();
                      /*  if (universityList.size()==0){
                            swipeRefreshLayout.setVisibility(View.GONE);
                            no_internet_rl.setVisibility(View.GONE);
                            no_data_rl.setVisibility(View.VISIBLE);
                        }*/
                        //数据不为空
                        if (universityList!=null && universityList.size()>0){
                            setData(true,universityList);
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
                        customLoadingDialog.dismiss();
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
        apiHome.getCollegeList(apiConstant.FIND_COLLEGE,order,area,level,is_type,school_type,keyword,mNextRequestPage, new com.qianyi.shine.callbcak.RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response,final  String s) {
               runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson=new Gson();
                        CollegeBean collegeBean = gson.fromJson(s, CollegeBean.class);
                        List<UniversityInfo> universityList = collegeBean.getData().getInfo().getRecommendUniversityList();
                        //数据不为空
                        //if (universityList!=null && universityList.size()>0){
                            setData(false,universityList);
                            mAdapter.setEnableLoadMore(true);
                            swipeRefreshLayout.setRefreshing(false);
                      //  }

                    }
                });
            }
            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.loadMoreFail();
                        Toast.makeText(CollegeListActivity.this, "网络错误", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }

    private void setData(boolean isRefresh, List data) {
       // mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            mAdapter.setNewData(data);
        } else {
            if (size > 0) {
                mAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            mAdapter.loadMoreEnd(isRefresh);
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
