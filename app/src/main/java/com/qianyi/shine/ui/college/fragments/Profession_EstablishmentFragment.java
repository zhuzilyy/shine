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
import com.qianyi.shine.ui.college.activity.CollegeActivity;
import com.qianyi.shine.ui.college.adapter.AreaAdapter;
import com.qianyi.shine.ui.college.adapter.EstablishAdapter;
import com.qianyi.shine.ui.college.adapter.GirdDropDownAdapter;
import com.qianyi.shine.ui.college.entivity.ProfessionBean;
import com.qianyi.shine.ui.home.activity.CollegeListActivity;
import com.qianyi.shine.ui.home.bean.MajorSchoolInfo;
import com.qianyi.shine.ui.home.bean.SchoolInfo;
import com.qianyi.shine.ui.home.bean.SchoolMajorBean;
import com.qianyi.shine.ui.mine.bean.CollegeBean;
import com.qianyi.shine.ui.mine.bean.UniversityInfo;
import com.qianyi.shine.utils.Utils;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/3.
 */

public class Profession_EstablishmentFragment extends BaseFragment {
    private String headers[] = {"省份", "类型"};
    private List<View> popupViews = new ArrayList<>();


    private GirdDropDownAdapter orderAdapter;
    private GirdDropDownAdapter typeAdapter;
    private AreaAdapter areaAdapter;
   // private String orderDatas[] = {"概率", "分数线", "排名"};
   private String[] types={"默认","985","211"};
    private String[] citys = {"全国","北京","天津","上海","重庆","河北","山西","辽宁","吉林","黑龙江",
            "江苏","浙江","安徽","福建","江西","山东","河南","湖北","湖南","广东","海南","四川","贵州",
            "云南","陕西","甘肃","青海","内蒙古","广西","宁夏","新疆"};
    private int constellationPosition = 0;
    @BindView(R.id.dropDownMenu)
    public DropDownMenu mDropDownMenu;

    //**************
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private EstablishAdapter mAdapter;
    public List<SchoolInfo> list_temp;
    private int mNextRequestPage = 1;
    private static final int PAGE_SIZE = 6;
    private ProfessionBean.ProfessionData.ProfessionInfo.MajorInfo majorInfo;
    private String  major_name;
    private RelativeLayout no_internet_rl,no_data_rl;
    private TextView reload;
    private CustomLoadingDialog customLoadingDialog;
    private String area="",is_type="";
    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_profession_establish,null);
    }
    public ProfessionBean.ProfessionData.ProfessionInfo.MajorInfo getMajorInfo() {
        return majorInfo;
    }

    public void setMajorInfo(ProfessionBean.ProfessionData.ProfessionInfo.MajorInfo majorInfo) {
        this.majorInfo = majorInfo;
        initMdata(majorInfo);
    }

    private void initMdata(ProfessionBean.ProfessionData.ProfessionInfo.MajorInfo majorInfo) {
        major_name= majorInfo.getMajor_name();
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
    protected void initViews() {
        customLoadingDialog=new CustomLoadingDialog(getActivity());
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
                String province=citys[constellationPosition];
                if (province.equals("全国")){
                    area="";
                }else{
                    area=province;
                }
                refresh();
            }
        });
        //类型
        final ListView typeView = new ListView(getActivity());
        typeView.setDividerHeight(0);
        typeAdapter = new GirdDropDownAdapter(getActivity(), Arrays.asList(types));
        typeView.setAdapter(typeAdapter);

        //init popupViews{添加的顺序决定展示的条目类型}
       // popupViews.add(orderView);
        popupViews.add(areaView);
        popupViews.add(typeView);
        typeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                typeAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[1] : types[position]);
                mDropDownMenu.closeMenu();
                String type = types[position];
                if (type.equals("默认")){
                    is_type="";
                }else{
                    is_type=type;
                }
                refresh();

            }
        });
        constellation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                areaAdapter.setCheckItem(position);
                constellationPosition = position;
            }
        });
//填充布局
        View contentView= LayoutInflater.from(getActivity()).inflate(R.layout.layout_refresh,null);
        swipeRefreshLayout=contentView.findViewById(R.id.swipeLayout);
        recyclerView= contentView.findViewById(R.id.rv_list);
        no_data_rl=contentView.findViewById(R.id.no_data_rl);
        no_internet_rl=contentView.findViewById(R.id.no_internet_rl);
        reload=contentView.findViewById(R.id.reload);
        initContentView();
        //init dropdownview
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
        //清空popupviews,否则报tab的数量和popupviews的数量不相等的错
        popupViews.clear();

    }

    /***
     * 处理加载的布局
     *
     */
    private void initContentView() {
        swipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //上拉加载
        initAdapter();

        //下拉刷新
        initRefreshLayout();
        swipeRefreshLayout.setRefreshing(true);
        //refresh();

    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initListener() {

    }

    private void initAdapter() {
        mAdapter = new EstablishAdapter(R.layout.establish_item,list_temp);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mAdapter.setPreLoadNumber(3);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(mActivity, "专业设置", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(),CollegeActivity.class);
                getActivity().startActivity(intent);
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
        apiHome.majorSchoolList(apiConstant.MAJOR_SCHOOL_LIST, major_name,area,is_type, new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        customLoadingDialog.dismiss();
                        Log.i("tag",s);
                        Gson gson=new Gson();
                        SchoolMajorBean schoolMajorBean = gson.fromJson(s, SchoolMajorBean.class);
                        List<MajorSchoolInfo> universityList = schoolMajorBean.getData().getMajorSchoolList();
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
                getActivity().runOnUiThread(new Runnable() {
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
        apiHome.majorSchoolList(apiConstant.MAJOR_SCHOOL_LIST, major_name,area,is_type, new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson=new Gson();
                        SchoolMajorBean schoolMajorBean = gson.fromJson(s, SchoolMajorBean.class);
                        List<MajorSchoolInfo> universityList = schoolMajorBean.getData().getMajorSchoolList();
                        //数据不为空
                        setData(false,universityList);
                        mAdapter.setEnableLoadMore(true);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                getActivity(). runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.loadMoreFail();
                        Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_LONG).show();
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
        } else {
            mAdapter.loadMoreComplete();
        }
    }




}
