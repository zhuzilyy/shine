package com.qianyi.shine.ui.college.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.qianyi.shine.ui.college.adapter.Prospect_MoneyAdapter;
import com.qianyi.shine.ui.college.adapter.Prospect_allreportAdapter;
import com.qianyi.shine.ui.college.view.MyScrollview;
import com.qianyi.shine.ui.home.bean.MajorListInfo;
import com.qianyi.shine.ui.home.bean.Prospect;
import com.qianyi.shine.ui.home.bean.ProspectBean;
import com.qianyi.shine.ui.home.bean.SalaryMajorInfo;
import com.qianyi.shine.utils.SPUtils;
import com.qianyi.shine.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
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
    @BindView(R.id.reload)
    TextView reload;
    @BindView(R.id.myScrollview)
    MyScrollview myScrollview;
    @BindView(R.id.no_data_rl)
    RelativeLayout no_data_rl;
    private Intent intent;
    private String universityId;
    private CustomLoadingDialog customLoadingDialog;
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
        //设置行业去向
        setPieChart(mPieChartIndustry);
        loadPieChartData(mPieChartIndustry);
        //设置地区去向
        setPieChart(mPieChartArea);
        loadPieChartData(mPieChartArea);

        //高薪行业列表
        rv_money.setLayoutManager(new LinearLayoutManager(getActivity()));
        moneyAdapter=new Prospect_MoneyAdapter(getActivity(),list);
        rv_money.setAdapter(moneyAdapter);
    }
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
        Log.i("tag",wmzyId+"=========wmzyId==========");
        apiHome.majorProspects(apiConstant.MAJOR_PROSPECTS, wmzyId,universityId, new RequestCallBack<String>() {
            @Override
            public void onSuccess(final Call call, Response response, final String s) {
                customLoadingDialog.dismiss();
                Log.i("tag",s);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myScrollview.setVisibility(View.VISIBLE);
                        no_internet_rl.setVisibility(View.GONE);
                        no_data_rl.setVisibility(View.GONE);
                        Gson gson=new Gson();
                        ProspectBean prospectBean = gson.fromJson(s, ProspectBean.class);
                        String code = prospectBean.getCode();
                        Log.i("tag",code+"======code========");
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
                        }else {
                            myScrollview.setVisibility(View.GONE);
                            no_internet_rl.setVisibility(View.GONE);
                            no_data_rl.setVisibility(View.VISIBLE);
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
    /**
     * 设置饼形图样式
     * @param chart
     */
    private void setPieChart(PieChart chart) {
        // apply styling
        chart.setDescription("这是对饼图的描述");
        chart.setHoleRadius(52f);//孔的半径
        chart.setTransparentCircleRadius(57f);//透明圆环的半径
        chart.setCenterText("MPChart\nAndroid");

//        chart.setCenterTextTypeface(mTf);
        chart.setCenterTextSize(18f);
        chart.setUsePercentValues(true);

        //中心文字颜色
        chart.setCenterTextColor(Color.GREEN);
    }

    private void loadPieChartData(PieChart chart) {
        ArrayList<Entry> entries = new ArrayList<Entry>();
        for (int i = 0; i < 5; i++) {
            entries.add(new Entry((int) (Math.random() * 70) + 30, i));
        }
        PieDataSet mPieDataSet = new PieDataSet(entries, "");

        // space between slices
        mPieDataSet.setSliceSpace(2f);
        mPieDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);

        PieData mPieChartData = new PieData(getQuarters(),mPieDataSet);

        // set data
        chart.setData( mPieChartData);

        //设置动画
        chart.animateXY(900, 900);

        Legend l = chart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);

        mPieChartData.setValueFormatter(new PercentFormatter());//设置显示成百分比
//        mChartData.setValueTypeface(mTf);
        mPieChartData.setValueTextSize(11f);//设置文字大小
        mPieChartData.setValueTextColor(Color.WHITE);

        //设置中心数据
        chart.setCenterText("各种去向");
    }
    /**
     * 饼形图的划分
     * @return
     */
    private ArrayList<String> getQuarters() {
        ArrayList<String> q = new ArrayList<String>();
        q.add("1st Quarter");
        q.add("2nd Quarter");
        q.add("3rd Quarter");
        q.add("4th Quarter");
        q.add("5th xzy");
        return q;
    }
}
