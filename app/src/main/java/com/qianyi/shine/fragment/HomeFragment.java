package com.qianyi.shine.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.api.apiHome;
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.fragment.adapter.GridAdapter;
import com.qianyi.shine.fragment.adapter.PullToRefreshAdapter;
import com.qianyi.shine.fragment.entity.CollegeEntity;
import com.qianyi.shine.fragment.entity.TestEntity;
import com.qianyi.shine.ui.account.activity.WebviewActivity;
import com.qianyi.shine.ui.college.activity.CollegeActivity;
import com.qianyi.shine.ui.college.activity.MoreCollegeActivity;
import com.qianyi.shine.ui.gaokao_news.activity.GaoKaoNewsActivity;
import com.qianyi.shine.ui.home.activity.AcceptanceRateActivity;
import com.qianyi.shine.ui.home.activity.EmploymentActivity;
import com.qianyi.shine.ui.home.activity.FindCollegeActivity;
import com.qianyi.shine.ui.home.activity.FindMajorActivity;
import com.qianyi.shine.ui.home.activity.HomeSearchActivity;
import com.qianyi.shine.ui.home.activity.IntelligentFillCollegeActivity;
import com.qianyi.shine.ui.home.activity.PriorityCollegeActivity;
import com.qianyi.shine.ui.home.activity.SearchOccupationActivity;
import com.qianyi.shine.ui.home.bean.HomeBean;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;


/**
 * Created by NEUNB on 2018/3/19.
 */


public class HomeFragment extends BaseFragment implements View.OnClickListener {
    private static final int PAGE_SIZE = 6;
    @BindView(R.id.rv_list)
    public RecyclerView mRecyclerView;
    @BindView(R.id.swipeLayout)
    public SwipeRefreshLayout mSwipeRefreshLayout;
    List<HomeBean.HomeData.HomeInfo.Article> bigArticles = new ArrayList<>();

    List<HomeBean.HomeData.HomeInfo.RecommendUniversity> universities;
    private View view_home;
    private PullToRefreshAdapter mAdapter;
    private int mNextRequestPage = 1;
    private RecyclerView main_headRv;
    private GridAdapter CollegeAdapter;
    private List<CollegeEntity> listCollege = new ArrayList<>();
    private TextView editText;
    //==========定位============
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    //初始化AMapLocationClientOption对象
    private TextView cityname;

    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        view_home = inflater.inflate(R.layout.fragment_home, null);
        return view_home;
    }

    @Override
    protected void initViews() {

        Log.i("loc", sHA1(getActivity()));
        //获取定位
        getCityName();
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //上拉加载
        initAdapter();
        //添加head
        addHeadView();
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
        //高考头条条目点击
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                List<HomeBean.HomeData.HomeInfo.Article> articles = adapter.getData();
                Intent intent = new Intent(getActivity(), WebviewActivity.class);
                intent.putExtra("title", "高考头条");
                intent.putExtra("url", articles.get(position).getWeburl());
                startActivity(intent);

            }
        });
    }

    private void initAdapter() {
        mAdapter = new PullToRefreshAdapter(getActivity());
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
//        mAdapter.setPreLoadNumber(3);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void addHeadView() {
        Typeface typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/bebas.ttf");
        View headView = getLayoutInflater().inflate(R.layout.head_view, (ViewGroup) mRecyclerView.getParent(), false);
        TextView count = headView.findViewById(R.id.suitCount);
        TextView count2 = headView.findViewById(R.id.count2);
        TextView count3 = headView.findViewById(R.id.count3);
        TextView count4 = headView.findViewById(R.id.count4);
        LinearLayout ll_findCollege = headView.findViewById(R.id.ll_findCollege);
        LinearLayout ll_employment = headView.findViewById(R.id.ll_employment);
        LinearLayout ll_findMajor = headView.findViewById(R.id.ll_findMajor);
        LinearLayout ll_search_occupation = headView.findViewById(R.id.ll_search_occupation);
        RelativeLayout rl_priorityCollege = headView.findViewById(R.id.rl_priorityCollege);
        RelativeLayout rl_integenceFill = headView.findViewById(R.id.rl_integenceFill);
        RelativeLayout rl_majorPriority = headView.findViewById(R.id.rl_majorPriority);
        //主页搜索
        LinearLayout homeSearchLin = headView.findViewById(R.id.homeSearch_ll);
        //goto院校优先填报
        LinearLayout gotoCollege01 = headView.findViewById(R.id.goto_college01);
        LinearLayout gotoCollege02 = headView.findViewById(R.id.goto_college02);
        //测试录取率
        LinearLayout ll_acceptanceRate = headView.findViewById(R.id.ll_acceptanceRate);

        cityname = headView.findViewById(R.id.cityName);
        //点击事件
        ll_findCollege.setOnClickListener(this);
        ll_employment.setOnClickListener(this);
        ll_findMajor.setOnClickListener(this);
        ll_search_occupation.setOnClickListener(this);
        rl_priorityCollege.setOnClickListener(this);
        rl_integenceFill.setOnClickListener(this);
        rl_majorPriority.setOnClickListener(this);
        gotoCollege01.setOnClickListener(this);
        gotoCollege02.setOnClickListener(this);
        homeSearchLin.setOnClickListener(this);
        ll_acceptanceRate.setOnClickListener(this);

        count.setTypeface(typeface1);
        count2.setTypeface(typeface1);
        count3.setTypeface(typeface1);
        count4.setTypeface(typeface1);
        //大学推荐[横向滑动的recyclerView]
        main_headRv = headView.findViewById(R.id.main_headRv);
        editText = headView.findViewById(R.id.main_search);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false);
        main_headRv.setFocusable(false);
        main_headRv.setLayoutManager(gridLayoutManager);


        //更多大学
        TextView moreCollege = headView.findViewById(R.id.moreCollege);
        View flag2 = headView.findViewById(R.id.flag2);
        moreCollege.setOnClickListener(this);
        flag2.setOnClickListener(this);
        //高考头条
        TextView gaokao_tv = headView.findViewById(R.id.gaokao_tv);
        View flag4 = headView.findViewById(R.id.flag4);
        gaokao_tv.setOnClickListener(this);
        flag4.setOnClickListener(this);
        mAdapter.addHeaderView(headView);
    }

    private void initRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
        apiHome.refresh(apiConstant.HOME, mNextRequestPage, new com.qianyi.shine.callbcak.RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        HomeBean homeBean = gson.fromJson(s, HomeBean.class);
                        if (homeBean != null) {
                            String code = homeBean.getCode();
                            if ("0".equals(code)) {
                                HomeBean.HomeData homeData = homeBean.getData();
                                if (homeData != null) {
                                    HomeBean.HomeData.HomeInfo homeInfo = homeData.getInfo();
                                    if (homeInfo != null) {
                                        //   List<HomeBean.HomeData.HomeInfo.Article>  articles = homeInfo.getArticleList();
//                                        bigArticles.addAll(articles);
                                        universities = homeInfo.getRecommendUniversityList();
                                        setData(true, homeInfo.getArticleList());
                                        mAdapter.setEnableLoadMore(true);
                                        mSwipeRefreshLayout.setRefreshing(false);
                                        /**
                                         * 推荐大学适配器
                                         */
                                        CollegeAdapter = new GridAdapter(getActivity(), universities);
                                        main_headRv.setAdapter(CollegeAdapter);
                                        //推荐大学点击事件
                                        CollegeAdapter.setOnItemClickListener(new GridAdapter.OnRecyclerViewItemClickListener() {
                                            @Override
                                            public void onItemClick(int position) {
                                                Intent intent = new Intent(getActivity(), CollegeActivity.class);
                                                startActivity(intent);
                                            }
                                        });
                                    }
                                }
                            } else {
                                Toast.makeText(mActivity, "" + homeBean.getInfo(), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(mActivity, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }

    //加载
    private void loadMore() {
        mNextRequestPage++;
        apiHome.loadMore(apiConstant.HOME, mNextRequestPage, new com.qianyi.shine.callbcak.RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        HomeBean homeBean = gson.fromJson(s, HomeBean.class);
                        if (homeBean != null) {
                            String code = homeBean.getCode();
                           // if ("0".equals(code)) {
                                HomeBean.HomeData homeData = homeBean.getData();
                                if (homeData != null) {
                                    HomeBean.HomeData.HomeInfo homeInfo = homeData.getInfo();
                                    if (homeInfo != null) {
                                        setData(false, homeInfo.getArticleList());
                                        mAdapter.setEnableLoadMore(true);
                                        mSwipeRefreshLayout.setRefreshing(false);
                                    }
                                }
//                            } else {
//                                Toast.makeText(mActivity, "" + homeBean.getInfo(), Toast.LENGTH_SHORT).show();
//                            }
                        }
                    }
                });

            }

            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                getActivity().runOnUiThread(new Runnable() {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gaokao_tv:
            case R.id.flag4:
                Intent intent_news = new Intent(getActivity(), GaoKaoNewsActivity.class);
                startActivity(intent_news);
                break;
            case R.id.flag2:
            case R.id.moreCollege:
                startActivity(new Intent(getActivity(), MoreCollegeActivity.class));
                break;
            //找大学
            case R.id.ll_findCollege:
                startActivity(new Intent(getActivity(), FindCollegeActivity.class));
                break;
            //看就业
            case R.id.ll_employment:
                startActivity(new Intent(getActivity(), EmploymentActivity.class));
                break;
            //查专业
            case R.id.ll_findMajor:
                startActivity(new Intent(getActivity(), FindMajorActivity.class));
                break;
            //查专业
            case R.id.ll_search_occupation:
                startActivity(new Intent(getActivity(), SearchOccupationActivity.class));
                break;
            //查专业
            case R.id.rl_priorityCollege:
                startActivity(new Intent(getActivity(), PriorityCollegeActivity.class));
                break;
            //智能填报
            case R.id.rl_integenceFill:
                startActivity(new Intent(getActivity(), IntelligentFillCollegeActivity.class));
                break;
            //专业优先
            case R.id.rl_majorPriority:
                startActivity(new Intent(getActivity(), FindMajorActivity.class));
                break;
            case R.id.goto_college01:
            case R.id.goto_college02:
                Intent intent = new Intent(getActivity(), PriorityCollegeActivity.class);
                startActivity(intent);
                break;
            case R.id.homeSearch_ll:
                startActivity(new Intent(getActivity(), HomeSearchActivity.class));
                break;
            //测试录取率
            case R.id.ll_acceptanceRate:
                startActivity(new Intent(getActivity(), AcceptanceRateActivity.class));
                break;
        }
    }

    private void getCityName() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getActivity());
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位模式为AMapLocationMode.Device_Sensors，仅设备模式。
        // mLocationOption.setLocationMode(AMapLocationMode.Device_Sensors);
        mLocationOption.setInterval(1000);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //启动定位
        mLocationClient.startLocation();

        if (null != mLocationClient) {
            mLocationClient.setLocationOption(mLocationOption);
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            mLocationClient.stopLocation();
            mLocationClient.startLocation();
        }

        //设置定位回调监听
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                Log.i("xzy", "errcode==" + aMapLocation.getErrorCode());
                if (aMapLocation.getErrorCode() == 0) {
                    double lat = aMapLocation.getLatitude();//维度
                    double log = aMapLocation.getLongitude();//经度
                    if (!TextUtils.isEmpty(aMapLocation.getCity())) {
                        cityname.setText(aMapLocation.getCity());
                    }
//                    LatLng latLng=new LatLng(lat,log);
//                    changeLocation(latLng);
                    Log.i("xzy", "111111111111111111111111");
                } else {
                    //定位失败
                    Log.i("xzy", "err+" + aMapLocation.getErrorCode() + "  info=" + aMapLocation.getErrorInfo());
                }
            }
        });
    }

    public static String sHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
                hexString.append(":");
            }
            String result = hexString.toString();
            return result.substring(0, result.length() - 1);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}


