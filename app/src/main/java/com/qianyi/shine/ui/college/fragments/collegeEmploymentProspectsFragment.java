package com.qianyi.shine.ui.college.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.PercentFormatter;
import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.fragment.entity.CollegeEntity;
import com.qianyi.shine.ui.college.adapter.Prospect_MoneyAdapter;
import com.qianyi.shine.ui.college.adapter.Prospect_allreportAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/31.
 */

public class collegeEmploymentProspectsFragment extends BaseFragment {
    @BindView(R.id.rv_money)
    public RecyclerView rv_money;
    @BindView(R.id.rv_allreport)
    public RecyclerView rv_allreport;
    private Prospect_MoneyAdapter moneyAdapter;
    private List<CollegeEntity> list;
    @BindView(R.id.chat_Industry)
    public PieChart mPieChartIndustry;
    @BindView(R.id.chat_Area)
    public PieChart mPieChartArea;
    private Prospect_allreportAdapter allreportAdapter;

     


    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        View layoutRes= inflater.inflate(R.layout.fragment_college_employmentprospects,null);
        return layoutRes;
    }

    @Override
    protected void initViews() {

        list=new ArrayList<>();
        list.add(new CollegeEntity(R.mipmap.icon01,"","111111",""));
        list.add(new CollegeEntity(R.mipmap.icon01,"","111111",""));
        list.add(new CollegeEntity(R.mipmap.icon01,"","111111",""));
        list.add(new CollegeEntity(R.mipmap.icon01,"","111111",""));
        list.add(new CollegeEntity(R.mipmap.icon01,"","111111",""));
        list.add(new CollegeEntity(R.mipmap.icon01,"","111111",""));
        list.add(new CollegeEntity(R.mipmap.icon01,"","111111",""));
        list.add(new CollegeEntity(R.mipmap.icon01,"","111111",""));
        list.add(new CollegeEntity(R.mipmap.icon01,"","111111",""));
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

        //学校下所有专业的就业报告
        rv_allreport.setLayoutManager(new LinearLayoutManager(getActivity()));
        allreportAdapter=new Prospect_allreportAdapter(getActivity(),null);
        rv_allreport.setAdapter(allreportAdapter);

    }

    @Override
    protected void initData() {


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
