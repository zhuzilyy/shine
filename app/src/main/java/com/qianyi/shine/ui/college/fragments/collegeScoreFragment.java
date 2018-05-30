package com.qianyi.shine.ui.college.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.gson.Gson;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.api.apiHome;
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.fragment.entity.CollegeEntity;
import com.qianyi.shine.ui.account.bean.LoginBean;
import com.qianyi.shine.ui.college.activity.CollegeActivity;
import com.qianyi.shine.ui.college.adapter.PlanAndDataAdapter;
import com.qianyi.shine.ui.college.adapter.Prospect_MoneyAdapter;
import com.qianyi.shine.ui.college.adapter.ScoreAdapter;
import com.qianyi.shine.ui.college.entivity.CollegeScoreBean;
import com.qianyi.shine.ui.home.activity.PriorityCollegeDetailsActivity;
import com.qianyi.shine.ui.home.bean.PrefessionBean;
import com.qianyi.shine.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

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



//        //折线图(毕业生平均月薪)
//        setLineChart(mLineChart);
//        loadLineChartData(mLineChart);

        //招生分数线列表
        rv_list.setLayoutManager(new LinearLayoutManager(getActivity()));


        //专业招生计划及历年数据列表
        rv_plan.setLayoutManager(new LinearLayoutManager(getActivity()));


    }

    @Override
    protected void initData() {
        mInitData();



    }

    private void mInitData() {
        LoginBean.LoginData.LoginInfo user = Utils.readUser(getActivity());
        if(user==null){
            return;

        }
        apiHome.collegeScroe(apiConstant.COLLEGE_SCORE, user.getId(), CollegeActivity.collegeId, new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, String s) {
                Log.i("wwww",s);
                Gson gson = new Gson();
                PrefessionBean prefessionBean= gson.fromJson(s,PrefessionBean.class);
                if(prefessionBean!=null){
                    PrefessionBean.PrefessionData prefessionData=prefessionBean.getData();
                    if(prefessionData!=null){
                        PrefessionBean.PrefessionData.PrefessionInfo prefessionInfo= prefessionData.getInfo();
                        if(prefessionInfo!=null){
                            //折线图(录取分数线波动图)
                            setLineChart(mLineChart,prefessionInfo);
                            loadLineChartData(mLineChart,prefessionInfo);
                            //赋值分数线
                            setDataScore(prefessionInfo);
                            //赋值招生计划
                            setDataPlan(prefessionInfo);
                        }
                    }

                }

            }

            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                Log.i("wwww",e.getMessage());
            }
        });
    }

    private void setDataPlan(PrefessionBean.PrefessionData.PrefessionInfo majorRecords) {

        planAndDataAdapter=new PlanAndDataAdapter(getActivity(),majorRecords.getEnroll_arr());
        rv_plan.setAdapter(planAndDataAdapter);
        rv_plan.setAdapter(planAndDataAdapter);
    }

    /***
     * 赋值分数线
     * @param allRecord
     */
    private void setDataScore(PrefessionBean.PrefessionData.PrefessionInfo allRecord) {

        moneyAdapter=new ScoreAdapter(getActivity(),allRecord);
        rv_list.setAdapter(moneyAdapter);



    }

    @Override
    protected void initListener() {

    }
    //**************折线统计图开始*******************************************
    /**
     * 设置折现图的样式
     * @param chart
     * @param allRecord
     */
    private void setLineChart(LineChart chart, PrefessionBean.PrefessionData.PrefessionInfo allRecord) {

        chart.setDescription("");
        chart.setDrawGridBackground(false);//设置网格背景
        chart.setScaleEnabled(false);//设置缩放
        chart.setDoubleTapToZoomEnabled(false);//设置双击不进行缩放

        //设置X轴
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴的位置
//        xAxis.setTypeface(mTf);//设置字体
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        //x轴不要间隔显示
        xAxis.setAdjustXLabels(false);





        //获得左侧侧坐标轴
        YAxis leftAxis = chart.getAxisLeft();
//        leftAxis.setTypeface(mTf);

        leftAxis.setLabelCount(5);//数字表示左侧有几个分段
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
     * @param allRecord
     */
    private void loadLineChartData(LineChart chart, PrefessionBean.PrefessionData.PrefessionInfo allRecord){
        //所有线的List
        ArrayList<LineDataSet> allLinesList = new ArrayList<LineDataSet>();

        ArrayList<Entry> entryList1 = new ArrayList<Entry>();
        ArrayList<Entry> entryList2 = new ArrayList<Entry>();

//        for(int i=0;i<3;i++){
//            //Entry(yValue,xIndex);一个Entry表示一个点，第一个参数为y值，第二个为X轴List的角标
//            entryList1.add(new Entry((int)(Math.random()*65)+40,i));
//        }
//        for(int i=0;i<3;i++){
//            //Entry(yValue,xIndex);一个Entry表示一个点，第一个参数为y值，第二个为X轴List的角标
//            entryList2.add(new Entry((int)(Math.random()*3)+10,i));
//        }


        String m2015H=allRecord.getScoreinfo().getRecord_2015().getGaofen();
        String m2016H=allRecord.getScoreinfo().getRecord_2016().getGaofen();
        String m2017H=allRecord.getScoreinfo().getRecord_2017().getGaofen();
        if("--".equals(m2015H)){
            m2015H="0";
        }
        if("--".equals(m2016H)){
            m2016H="0";
        }
        if("--".equals(m2017H)){
            m2017H="0";
        }


        String m2015L=allRecord.getScoreinfo().getRecord_2015().getDifen();
        String m2016L=allRecord.getScoreinfo().getRecord_2016().getDifen();
        String m2017L=allRecord.getScoreinfo().getRecord_2017().getDifen();
        if("--".equals(m2015L)){
            m2015L="0";
        }
        if("--".equals(m2016L)){
            m2016L="0";
        }
        if("--".equals(m2017L)){
            m2017L="0";
        }


        //高分线
        entryList1.add(new Entry((Integer.parseInt(m2015H)),0));
        entryList1.add(new Entry((Integer.parseInt(m2016H)),1));
        entryList1.add(new Entry((Integer.parseInt(m2017H)),2));
        //低分线
        entryList2.add(new Entry((Integer.parseInt(m2015L)),0));
        entryList2.add(new Entry((Integer.parseInt(m2016L)),1));
        entryList2.add(new Entry((Integer.parseInt(m2017L)),2));


        //LineDataSet可以看做是一条线
        LineDataSet dataSet1 = new LineDataSet(entryList1,"最高分");
        dataSet1.setLineWidth(2.5f);
        dataSet1.setCircleSize(4.5f);
        dataSet1.setCircleColor(R.color.yellow);
        dataSet1.setColor(R.color.yellow);
        dataSet1.setHighLightColor(Color.RED);//设置点击某个点时，横竖两条线的颜色
        dataSet1.setDrawValues(true);//是否在点上绘制Value

        LineDataSet dataSet2 = new LineDataSet(entryList2,"最低分");
        dataSet2.setLineWidth(2.5f);
        dataSet2.setCircleSize(4.5f);
        dataSet2.setHighLightColor(Color.GREEN);//设置点击某个点时，横竖两条线的颜色
        dataSet2.setDrawValues(true);//是否在点上绘制Value

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

        m.add("2015");
        m.add("2016");
        m.add("2017");
        m.add("2018");
        m.add("2019");
        m.add("2020");


        return m;
    }
//**************折线统计图结束*******************************************
}
