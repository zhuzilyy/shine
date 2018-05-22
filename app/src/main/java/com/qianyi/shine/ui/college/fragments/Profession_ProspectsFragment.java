package com.qianyi.shine.ui.college.fragments;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.PercentFormatter;
import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.ui.college.entivity.ProfessionBean;

import java.math.BigDecimal;
import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/3.
 */

public class Profession_ProspectsFragment extends BaseFragment {
    @BindView(R.id.line_chart)
    public LineChart mLineChart;
    @BindView(R.id.chart_01)
    public PieChart chat01;
    @BindView(R.id.chart_02)
    public PieChart chat02;
    @BindView(R.id.chart_03)
    public PieChart chat03;

    @BindView(R.id.tv_over) public TextView tv_over;
    @BindView(R.id.tv_moreCity) public TextView tv_moreCity;
    @BindView(R.id.tv_fiveyearMoney) public TextView tv_fiveyearMoney;
    @BindView(R.id.tv_moreIndutory) public TextView tv_moreIndutory;
    @BindView(R.id.tv_moneyOrder) public TextView tv_moneyOrder;

    public ProfessionBean.ProfessionData.ProfessionInfo.MajorInfo getMajorInfo() {
        return majorInfo;
    }

    public void setMajorInfo(ProfessionBean.ProfessionData.ProfessionInfo.MajorInfo majorInfo) {
        this.majorInfo = majorInfo;
        initMdata(majorInfo);

    }

    /***
     * 赋值数据
     * @param majorInfo
     */
    private void initMdata(ProfessionBean.ProfessionData.ProfessionInfo.MajorInfo majorInfo) {
        if(getMajorInfo() == null){
            return;
        }
        //毕业超过N的工资
        Float num=Float.parseFloat(majorInfo.getSalary_over_ratio());
        BigDecimal bg = new BigDecimal(num);
        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()*100;
        tv_over.setText("毕业薪资超过"+f1+"%的专业");
        //薪酬排名
        tv_moneyOrder.setText(majorInfo.getSalary_factor_rank_index());
        //毕业5年的月薪
        String money5 = majorInfo.getSalary5();
        tv_fiveyearMoney.setText("￥"+money5);
        //最多去向的行业
        String industry = majorInfo.getIndustry_name();
        tv_moreIndutory.setText(industry);
        //最多去向的城市
        String city =majorInfo.getLoc_name();
        tv_moreCity.setText(city);


        //折线图(毕业生平均月薪)
        setLineChart(mLineChart);
        loadLineChartData(mLineChart);
        //饼图--就业方向
        setPieChart(chat01,"就业方向");
        loadPieIndChartData(chat01);
        //饼图--职能方向
        setPieChart(chat02,"职能方向");
        loadPieZhiNengChartData(chat02);
        //饼图--地区方向
        setPieChart(chat03, "地区方向");
        loadPieCityChartData(chat03);






    }

    /***
     * 地区去向
     * @param chart
     */
    private void loadPieCityChartData(PieChart chart) {
        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int i = 0; i <getMajorInfo().getCity_list().size(); i++) {
            String ratio = getMajorInfo().getCity_list().get(i).getRatio();
            float nn = Float.parseFloat(ratio);
            float mm=nn*100+10;

            entries.add(new Entry((int)(mm), i));
        }

        PieDataSet mPieDataSet = new PieDataSet(entries, "");

        // space between slices
        mPieDataSet.setSliceSpace(2f);
        mPieDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);

        PieData mPieChartData = new PieData(getAreaQuarters(),mPieDataSet);

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

    /***
     * 职能方向
     * @param
     */
    private void loadPieZhiNengChartData(PieChart chart) {
        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int i = 0; i <getMajorInfo().getInd_info_list().size(); i++) {
            String ratio = getMajorInfo().getZhineng_list().get(i).getRatio();
            float nn = Float.parseFloat(ratio);
            float mm=nn*100+10;

            entries.add(new Entry((int)(mm), i));

        }



        PieDataSet mPieDataSet = new PieDataSet(entries, "");

        // space between slices
        mPieDataSet.setSliceSpace(2f);
        mPieDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);

        PieData mPieChartData = new PieData(getZhiNengQuarters(),mPieDataSet);

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
        chart.setCenterText("职能方向");
    }


    public ProfessionBean.ProfessionData.ProfessionInfo.MajorInfo majorInfo;


    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_profession_prospects,null);
    }
    @Override
    protected void initViews() {



    }

    /***
     * 行业去向
     * @param
     */
    private void loadPieIndChartData(PieChart chart) {
        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int i = 0; i <getMajorInfo().getInd_info_list().size(); i++) {
            String ratio = getMajorInfo().getInd_info_list().get(i).getRatio();
            float nn = Float.parseFloat(ratio);
            float mm=nn*100+10;

            entries.add(new Entry((int)(mm), i));
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
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART_INSIDE);

        mPieChartData.setValueFormatter(new PercentFormatter());//设置显示成百分比
//        mChartData.setValueTypeface(mTf);
        mPieChartData.setValueTextSize(11f);//设置文字大小
        mPieChartData.setValueTextColor(Color.BLACK);
        //设置中心数据
        chart.setCenterText("行业去向");
    }
    /***
     * 职能饼图
     * @param chart
     */
    private void setPieZhiNengChart(PieChart chart) {

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
//      xAxis.setTypeface(mTf);//设置字体
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);

        //获得左侧侧坐标轴
        YAxis leftAxis = chart.getAxisLeft();
//      leftAxis.setTypeface(mTf);
        leftAxis.setLabelCount(5);
//      leftAxis.setAxisLineWidth(1.5f);

        //设置右侧坐标轴
        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setDrawAxisLine(false);//右侧坐标轴线
        rightAxis.setDrawLabels(false);//右侧坐标轴数组Lable
//      rightAxis.setTypeface(mTf);
//      rightAxis.setLabelCount(5);
//      rightAxis.setDrawGridLines(false);
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

//**************饼图开始***************************************
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

    private void loadPieChartData(PieChart chart) {

        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int i = 0; i <4; i++) {
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
        l.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);

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
     */
    private ArrayList<String> getQuarters() {



        ArrayList<String> q = new ArrayList<String>();

        for (int i = 1; i <=getMajorInfo().getInd_info_list().size() ; i++) {
            q.add(i+""+getMajorInfo().getInd_info_list().get(i-1).getIndustry_name());


        }

        return q;
    }
    /**
     * 职能饼形图的划分
     * @return
     */
    private ArrayList<String> getZhiNengQuarters() {



        ArrayList<String> q = new ArrayList<String>();

        for (int i = 1; i <=getMajorInfo().getZhineng_list().size() ; i++) {
            q.add(i+""+getMajorInfo().getZhineng_list().get(i-1).getZhineng_name());


        }

        return q;
    }

    /**
     * 地区饼形图的划分
     * @return
     */
    private ArrayList<String> getAreaQuarters() {

        ArrayList<String> q = new ArrayList<String>();

        for (int i = 1; i <=getMajorInfo().getCity_list().size() ; i++) {
            q.add(i+""+getMajorInfo().getCity_list().get(i-1).getLoc_name());
        }

        return q;
    }



//**************饼图结束***************************************

}
