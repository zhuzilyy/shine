package com.qianyi.shine.ui.college.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.PercentFormatter;
import com.google.gson.Gson;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.api.apiHome;
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.dialog.CustomLoadingDialog;
import com.qianyi.shine.ui.account.bean.LoginBean;
import com.qianyi.shine.ui.college.adapter.Prospect_MoneyAdapter;
import com.qianyi.shine.ui.college.adapter.Prospect_allreportAdapter;
import com.qianyi.shine.ui.college.view.MyScrollview;
import com.qianyi.shine.ui.home.activity.PriorityProfessionalDetailsActivity;
import com.qianyi.shine.ui.home.bean.CityListInfo;
import com.qianyi.shine.ui.home.bean.IndInfo;
import com.qianyi.shine.ui.home.bean.MajorListInfo;
import com.qianyi.shine.ui.home.bean.Prospect;
import com.qianyi.shine.ui.home.bean.ProspectBean;
import com.qianyi.shine.ui.home.bean.SalaryMajorInfo;
import com.qianyi.shine.ui.mine.activity.VipActivity;
import com.qianyi.shine.utils.SPUtils;
import com.qianyi.shine.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/3/31.
 */

public class collegeEmploymentProspectsFragment extends BaseFragment {
    @BindView(R.id.rv_money)
    public RecyclerView rv_money;
    @BindView(R.id.rv_allreport)
    public RecyclerView rv_allreport;
    private Prospect_MoneyAdapter moneyAdapter;
    private List<SalaryMajorInfo> list;
    @BindView(R.id.chat_Industry)
    public PieChart mPieChartIndustry;
    @BindView(R.id.chat_Area)
    public PieChart mPieChartArea;
    private Prospect_allreportAdapter allreportAdapter;
    @BindView(R.id.tv_rank)
    TextView tv_rank;
    @BindView(R.id.tv_salary)
    TextView tv_salary;
    @BindView(R.id.tv_industry)
    TextView tv_industry;
    @BindView(R.id.tv_city)
    TextView tv_city;
    @BindView(R.id.tv_major)
    TextView tv_major;
    @BindView(R.id.no_internet_rl)
    RelativeLayout no_internet_rl;
    @BindView(R.id.ll_openVip)
    LinearLayout ll_openVip;
    @BindView(R.id.rl_vip)
    RelativeLayout rl_vip;
    @BindView(R.id.reload)
    TextView reload;
    @BindView(R.id.myScrollview)
    MyScrollview myScrollview;
    @BindView(R.id.no_data_rl)
    RelativeLayout no_data_rl;
    private Intent intent;
    private String universityId;
    private CustomLoadingDialog customLoadingDialog;
    private String isVip;
    private MyReceiver myReceiver;
    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        View layoutRes= inflater.inflate(R.layout.fragment_college_employmentprospects,null);
        return layoutRes;
    }
    @Override
    protected void initViews() {
        customLoadingDialog=new CustomLoadingDialog(getActivity());
        intent = getActivity().getIntent();
        if (intent!=null){
            universityId=intent.getStringExtra("id");
        }
        list=new ArrayList<>();
        //高薪行业列表
        rv_money.setLayoutManager(new LinearLayoutManager(getActivity()));
        moneyAdapter=new Prospect_MoneyAdapter(getActivity(),list);
        rv_money.setAdapter(moneyAdapter);

        //注册广播
        myReceiver=new MyReceiver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("com.action.open.vip");
        getActivity().registerReceiver(myReceiver,intentFilter);
    }

    //**************************************************

    /**
     * 设置饼形图样式
     * @param chart
     */
    /**
     * 设置饼形图样式
     * @param chart
     * @param name
     */
    private void setPieChart(PieChart chart, String name) {
        // apply styling
        chart.setDescription("");
        chart.setHoleRadius(52f);
        chart.setTransparentCircleRadius(57f);
        chart.setCenterText("MPChart\nAndroid");
//        chart.setCenterTextTypeface(mTf);
        chart.setCenterTextSize(18f);
        chart.setUsePercentValues(true);

        //中心文字颜色
        chart.setCenterTextColor(Color.GREEN);
    }

    /***
     * 行业去向
     * @param
     * @param ind_info_list
     */
    private void loadPieIndChartData(PieChart chart, List<IndInfo> ind_info_list) {
        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int i = 0; i <ind_info_list.size(); i++) {
            String ratio = ind_info_list.get(i).getRatio();
            float nn = Float.parseFloat(ratio);
            float mm=nn*100+10;

            entries.add(new Entry((int)(mm), i));
        }



        PieDataSet mPieDataSet = new PieDataSet(entries, "");

        // space between slices
        mPieDataSet.setSliceSpace(2f);
        mPieDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);

        PieData mPieChartData = new PieData(getQuarters(ind_info_list),mPieDataSet);

        // set data
        chart.setData( mPieChartData);

        //设置动画
        chart.animateXY(900, 900);

        Legend l = chart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART_INSIDE);

        mPieChartData.setValueFormatter(new PercentFormatter());//设置显示成百分比
//        mChartData.setValueTypeface(mTf);
        mPieChartData.setValueTextSize(11f);//设置文字大小
        mPieChartData.setValueTextColor(Color.BLACK);
        //设置中心数据
        chart.setCenterText("行业去向");
    }


    /***
     * 地区去向
     * @param chart
     * @param city_list
     */
    private void loadPieCityChartData(PieChart chart, List<CityListInfo> city_list) {
        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int i = 0; i <city_list.size(); i++) {
            String ratio = city_list.get(i).getRatio();
            float nn = Float.parseFloat(ratio);
            float mm=nn*100+10;

            entries.add(new Entry((int)(mm), i));
        }

        PieDataSet mPieDataSet = new PieDataSet(entries, "");

        // space between slices
        mPieDataSet.setSliceSpace(2f);
        mPieDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);

        PieData mPieChartData = new PieData(getAreaQuarters(city_list),mPieDataSet);

        // set data
        chart.setData(mPieChartData);

        //设置动画
        chart.animateXY(900, 900);

        Legend l = chart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART_INSIDE);

        mPieChartData.setValueFormatter(new PercentFormatter());//设置显示成百分比
//        mChartData.setValueTypeface(mTf);
        mPieChartData.setValueTextSize(11f);//设置文字大小
        mPieChartData.setValueTextColor(Color.BLACK);

        //设置中心数据
        chart.setCenterText("地区去向");
    }

    /**
     * 饼形图的划分
     * @return
     * @param ind_info_list
     */
    private ArrayList<String> getQuarters(List<IndInfo> ind_info_list) {



        ArrayList<String> q = new ArrayList<String>();

        for (int i = 1; i <=ind_info_list.size() ; i++) {
            q.add(i+""+ind_info_list.get(i-1).getIndustry_name());


        }

        return q;
    }



    /**
     * 饼形图的划分
     * @return
     */
    /**
     * 地区饼形图的划分
     * @return
     * @param city_list
     */
    private ArrayList<String> getAreaQuarters(List<CityListInfo> city_list) {

        ArrayList<String> q = new ArrayList<String>();

        for (int i = 1; i <=city_list.size() ; i++) {
            q.add(i+""+city_list.get(i-1).getLoc_name());
        }

        return q;
    }




    //***************************************************
    @Override
    protected void initData() {
        if (Utils.hasInternet()){
            getData();
        }else{
            myScrollview.setVisibility(View.GONE);
            no_internet_rl.setVisibility(View.VISIBLE);
            no_data_rl.setVisibility(View.GONE);
        }
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
    }
    //加载数据
    private void getData() {
        customLoadingDialog.show();
        String wmzyId= (String) SPUtils.get(getActivity(),"wmzyId","");
        apiHome.majorProspects(apiConstant.MAJOR_PROSPECTS, wmzyId,universityId, new RequestCallBack<String>() {
            @Override
            public void onSuccess(final Call call, Response response, final String s) {
                customLoadingDialog.dismiss();
                Log.i("tag",s);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LoginBean.LoginData.LoginInfo loginInfo = Utils.readUser(getActivity());
                        isVip =loginInfo.getIs_vip();
                        if (isVip.equals("0")){
                            myScrollview.setVisibility(View.GONE);
                            no_internet_rl.setVisibility(View.GONE);
                            no_data_rl.setVisibility(View.GONE);
                            ll_openVip.setVisibility(View.VISIBLE);
                            rl_vip.setVisibility(View.VISIBLE);
                        }else if (isVip.equals("1")){
                            ll_openVip.setVisibility(View.GONE);
                            rl_vip.setVisibility(View.GONE);
                            myScrollview.setVisibility(View.VISIBLE);
                            no_internet_rl.setVisibility(View.GONE);
                            no_data_rl.setVisibility(View.GONE);
                        }
                        try {
                            JSONObject jsonObject=new JSONObject(s);
                            String code = jsonObject.getString("code");
                            if (code.equals("0")){
                                Gson gson=new Gson();
                                ProspectBean prospectBean = gson.fromJson(s, ProspectBean.class);
                                if (code.equals("0")){
                                    Prospect prospect = prospectBean.getData().getInfo().getProspect();
                                    String salary_factor_rank_index = prospect.getSalary_factor_rank_index();
                                    String salary_factor = prospect.getSalary_factor();
                                    String most_industry = prospect.getMost_industry();
                                    String most_city = prospect.getMost_city();
                                    String most_major = prospect.getMost_major();
                                    tv_rank.setText(salary_factor_rank_index);
                                    tv_salary.setText(salary_factor);
                                    tv_industry.setText(most_industry);
                                    tv_city.setText(most_city);
                                    tv_major.setText(most_major);
                                    //高薪专业
                                    List<SalaryMajorInfo> salary_major_list = prospect.getSalary_major_list();
                                    if (salary_major_list!=null && salary_major_list.size()>0){
                                        list.addAll(salary_major_list);
                                        moneyAdapter.notifyDataSetChanged();
                                    }
                                    //就业报告
                                    List<MajorListInfo> majorList = prospect.getMajor_list();
                                    if (majorList!=null && majorList.size()>0){
                                        rv_allreport.setLayoutManager(new LinearLayoutManager(getActivity()));
                                        allreportAdapter=new Prospect_allreportAdapter(getActivity(),majorList);
                                        rv_allreport.setAdapter(allreportAdapter);
                                    }

                                    //饼图--就业方向
                                    setPieChart(mPieChartIndustry,"就业方向");
                                    loadPieIndChartData(mPieChartIndustry,prospect.getInd_info_list());
                                    //设置地区去向
                                    setPieChart(mPieChartArea,"");
                                    loadPieCityChartData(mPieChartArea,prospect.getCity_list());
                                }
                            }  else if(code.equals("-100")){
                                if (isVip.equals("0")){
                                    myScrollview.setVisibility(View.GONE);
                                    no_internet_rl.setVisibility(View.GONE);
                                    no_data_rl.setVisibility(View.GONE);
                                    ll_openVip.setVisibility(View.VISIBLE);
                                    rl_vip.setVisibility(View.VISIBLE);
                                }else if(isVip.equals("1")){
                                    myScrollview.setVisibility(View.GONE);
                                    no_internet_rl.setVisibility(View.GONE);
                                    no_data_rl.setVisibility(View.VISIBLE);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                customLoadingDialog.dismiss();
                myScrollview.setVisibility(View.GONE);
                no_internet_rl.setVisibility(View.VISIBLE);
                no_data_rl.setVisibility(View.GONE);
            }
        });
    }
    @Override
    protected void initListener() {

    }
    @OnClick(R.id.btn_openVip)
    public void click(View view){
        switch (view.getId()){
            case R.id.btn_openVip:
                Intent intent=new Intent(getActivity(), VipActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(myReceiver);
    }
    //接收支付成功的广播
    class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("com.action.open.vip")){
                ll_openVip.setVisibility(View.GONE);
                rl_vip.setVisibility(View.GONE);
                getData();
            }
        }
    }
}
