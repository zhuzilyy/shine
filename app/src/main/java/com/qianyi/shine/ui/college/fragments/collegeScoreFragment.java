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

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.fragment.entity.CollegeEntity;
import com.qianyi.shine.ui.college.adapter.PlanAndDataAdapter;
import com.qianyi.shine.ui.college.adapter.Prospect_MoneyAdapter;
import com.qianyi.shine.ui.college.adapter.ScoreAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/31.
 */

public class collegeScoreFragment extends BaseFragment {
    @BindView(R.id.line_chart)
    public LineChart mLineChart;
    @BindView(R.id.rv_list)
    public RecyclerView rv_list;
    private List<CollegeEntity> list;
    private ScoreAdapter moneyAdapter;

    //
    @BindView(R.id.rv_plan)
    public RecyclerView rv_plan;
    private PlanAndDataAdapter planAndDataAdapter;



    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        View layoutRes= inflater.inflate(R.layout.fragment_college_score,null);
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



        //折线图(毕业生平均月薪)
        setLineChart(mLineChart);
        loadLineChartData(mLineChart);

        //招生分数线列表
        rv_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        moneyAdapter=new ScoreAdapter(getActivity(),list);
        rv_list.setAdapter(moneyAdapter);

        //专业招生计划及历年数据列表
        rv_plan.setLayoutManager(new LinearLayoutManager(getActivity()));
        planAndDataAdapter=new PlanAndDataAdapter(getActivity(),list);
        rv_plan.setAdapter(planAndDataAdapter);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
    //**************折线统计图开始*******************************************
    /**
     * 设置折现图的样式
     * @param chart
     */
    private void setLineChart(LineChart chart) {

        chart.setDescription("Glan");
        chart.setDrawGridBackground(false);//设置网格背景
        chart.setScaleEnabled(false);//设置缩放
        chart.setDoubleTapToZoomEnabled(false);//设置双击不进行缩放

        //设置X轴
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴的位置
//        xAxis.setTypeface(mTf);//设置字体
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);

        //获得左侧侧坐标轴
        YAxis leftAxis = chart.getAxisLeft();
//        leftAxis.setTypeface(mTf);
        leftAxis.setLabelCount(5);
//        leftAxis.setAxisLineWidth(1.5f);

        //设置右侧坐标轴
        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setDrawAxisLine(false);//右侧坐标轴线
        rightAxis.setDrawLabels(false);//右侧坐标轴数组Lable
//        rightAxis.setTypeface(mTf);
//        rightAxis.setLabelCount(5);
//        rightAxis.setDrawGridLines(false);
    }

    /**
     * 为折线图设置数据
     * @param chart
     */
    private void loadLineChartData(LineChart chart){
        //所有线的List
        ArrayList<LineDataSet> allLinesList = new ArrayList<LineDataSet>();

        ArrayList<Entry> entryList1 = new ArrayList<Entry>();
        ArrayList<Entry> entryList2 = new ArrayList<Entry>();
        for(int i=0;i<12;i++){
            //Entry(yValue,xIndex);一个Entry表示一个点，第一个参数为y值，第二个为X轴List的角标
            entryList1.add(new Entry((int)(Math.random()*65)+40,i));
        }
        for(int i=0;i<12;i++){
            //Entry(yValue,xIndex);一个Entry表示一个点，第一个参数为y值，第二个为X轴List的角标
            entryList2.add(new Entry((int)(Math.random()*3)+10,i));
        }


        //LineDataSet可以看做是一条线
        LineDataSet dataSet1 = new LineDataSet(entryList1,"dataLine1");
        dataSet1.setLineWidth(2.5f);
        dataSet1.setCircleSize(4.5f);
        dataSet1.setHighLightColor(Color.RED);//设置点击某个点时，横竖两条线的颜色
        dataSet1.setDrawValues(false);//是否在点上绘制Value

        LineDataSet dataSet2 = new LineDataSet(entryList2,"dataLine2");
        dataSet1.setLineWidth(2.5f);
        dataSet1.setCircleSize(4.5f);
        dataSet1.setHighLightColor(Color.GREEN);//设置点击某个点时，横竖两条线的颜色
        dataSet1.setDrawValues(false);//是否在点上绘制Value

        allLinesList.add(dataSet1);
        allLinesList.add(dataSet2);

        //LineData表示一个LineChart的所有数据(即一个LineChart中所有折线的数据)
        LineData mChartData = new LineData(getXAxisShowLable(),allLinesList);

        // set data
        chart.setData((LineData) mChartData);
        chart.animateX(1500);//设置动画
    }
    //提供x轴数据
    private ArrayList<String> getXAxisShowLable() {
        ArrayList<String> m = new ArrayList<String>();
        m.add("Jan");
        m.add("Feb");
        m.add("Mar");
        m.add("Apr");
        m.add("May");
        m.add("Jun");
        m.add("Jul");
        m.add("Aug");
        m.add("Sep");
        m.add("Okt");
        m.add("Nov");
        m.add("Dec");
        return m;
    }
//**************折线统计图结束*******************************************
}
