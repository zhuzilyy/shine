package com.qianyi.shine.ui.home.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.fragment.entity.CollegeEntity;
import com.qianyi.shine.ui.account.bean.LoginBean;
import com.qianyi.shine.ui.college.activity.CollegeActivity;
import com.qianyi.shine.ui.college.adapter.PlanAndDataAdapter;
import com.qianyi.shine.ui.college.adapter.ScoreAdapter;
import com.qianyi.shine.ui.college.entivity.CollegeScoreBean;
import com.qianyi.shine.ui.gaokao_news.view.XTitleView;
import com.qianyi.shine.ui.mine.adapter.ProfessionAdapter;
import com.qianyi.shine.utils.Utils;

import java.nio.charset.IllegalCharsetNameException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/9.
 */

public class PriorityCollegeDetailsActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.title)
    public XTitleView title;
    @BindView(R.id.line_chart)
    public LineChart mLineChart;
    @BindView(R.id.line_chart02)
    public LineChart mLineChart02;

    @BindView(R.id.rv_list)
    public RecyclerView rv_list;
    private ScoreAdapter moneyAdapter;
    private List<CollegeEntity> list;

    //专业招生的历年数据
    @BindView(R.id.rv_plan)
    public RecyclerView rv_plan;
    private PlanAndDataAdapter planAndDataAdapter;

    @BindView(R.id.collegeDetails_tv)
    public TextView collegeDetails_tv;
    @BindView(R.id.addAtention_tv)
    public TextView addTention_tv;

    @BindView(R.id.tv_RiskRate) public TextView tv_RiskRate;
    @BindView(R.id.tv_2017renshuplan) public TextView tv_2017renshuplan;


    private String CollegeNameStr="";
    private String LuqulvStr="";
    private String CollegeIdStr="";



    @Override
    protected void initViews() {

        BaseActivity.addActivity(this);

        CollegeNameStr = getIntent().getStringExtra("collegeName");
        LuqulvStr = getIntent().getStringExtra("luqulv");

        if(LuqulvStr.endsWith("%")){
            LuqulvStr= LuqulvStr.replace("%","");
        }

        CollegeIdStr = getIntent().getStringExtra("collegeid");


        title.xSetTitle(CollegeNameStr);
        tv_RiskRate.setText(LuqulvStr);



        title.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        //录取分数线波动图
//        setLineChart(mLineChart);
//        loadLineChartData(mLineChart);
        //位次波动图
//        setLineChart(mLineChart02);
//        loadLineChartData(mLineChart02);

        //招生分数线列表
        rv_list.setLayoutManager(new LinearLayoutManager(PriorityCollegeDetailsActivity.this));
//        moneyAdapter=new ScoreAdapter(PriorityCollegeDetailsActivity.this,list);
//        rv_list.setAdapter(moneyAdapter);

        //专业招生计划及历年数据列表
        rv_plan.setLayoutManager(new LinearLayoutManager(PriorityCollegeDetailsActivity.this));
//        planAndDataAdapter=new PlanAndDataAdapter(PriorityCollegeDetailsActivity.this,list);
//        rv_plan.setAdapter(planAndDataAdapter);
    }

    @Override
    protected void initData() {
        mInitData();
    }
    private void mInitData() {
        LoginBean.LoginData.LoginInfo user = Utils.readUser(PriorityCollegeDetailsActivity.this);
        if(user==null){
            return;
        }
        apiHome.collegeScroe(apiConstant.COLLEGE_SCORE, user.getId(), CollegeIdStr, new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, String s) {
                Log.i("wwww",s);
                Gson gson = new Gson();
                CollegeScoreBean collegeScoreBean =gson.fromJson(s,CollegeScoreBean.class);
                if(collegeScoreBean!=null){
                    String code = collegeScoreBean.getCode();
                    if("0".equals(code)){
                        CollegeScoreBean.CollegeScoreData collegeScoreData=collegeScoreBean.getData();
                        if(collegeScoreData!=null){
                            CollegeScoreBean.CollegeScoreData.CollegeScoreInfo collegeScoreInfo=collegeScoreData.getInfo();
                            if(collegeScoreInfo!=null){
                                CollegeScoreBean.CollegeScoreData.CollegeScoreInfo.AllRecord allRecord=   collegeScoreInfo.getAll_record();
                                List<CollegeScoreBean.CollegeScoreData.CollegeScoreInfo.MajorRecord> majorRecords=collegeScoreInfo.getMajor_record();
                                //赋值分数线
                                setDataScore(allRecord);
                                //赋值招生计划
                                setDataPlan(majorRecords);
                                //折线图(录取分数线波动图)
                                setLineChart(mLineChart,allRecord);
                                loadLineChartData(mLineChart,allRecord);
                                //赋值其他
                                setOtherData(allRecord);


                            }
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

    /***
     * 赋值界面其他数据
     * @param allRecord
     */
    private void setOtherData(CollegeScoreBean.CollegeScoreData.CollegeScoreInfo.AllRecord allRecord) {
        tv_2017renshuplan.setText("2017年招生计划："+allRecord.getRecord_2017().getRenshu());
    }

    /***
     * 赋值分数线
     * @param allRecord
     */
    private void setDataScore(CollegeScoreBean.CollegeScoreData.CollegeScoreInfo.AllRecord allRecord) {
        moneyAdapter=new ScoreAdapter(PriorityCollegeDetailsActivity.this,allRecord);
        rv_list.setAdapter(moneyAdapter);
    }

    /***
     * 计划招生
     * @param majorRecords
     */
    private void setDataPlan(List<CollegeScoreBean.CollegeScoreData.CollegeScoreInfo.MajorRecord> majorRecords) {
        planAndDataAdapter=new PlanAndDataAdapter(PriorityCollegeDetailsActivity.this,majorRecords);
        rv_plan.setAdapter(planAndDataAdapter);
    }



    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_priority_details_college);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }

    //**************折线统计图开始*******************************************
    /**
     * 设置折现图的样式
     * @param chart
     * @param allRecord
     */
    private void setLineChart(LineChart chart, CollegeScoreBean.CollegeScoreData.CollegeScoreInfo.AllRecord allRecord) {

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
    private void loadLineChartData(LineChart chart, CollegeScoreBean.CollegeScoreData.CollegeScoreInfo.AllRecord allRecord){
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


        try {

            String m2015H=allRecord.getRecord_2015().getGaofen();
            String m2016H=allRecord.getRecord_2016().getGaofen();
            String m2017H=allRecord.getRecord_2017().getGaofen();
            if("--".equals(m2015H)){
                m2015H="0";
            }
            if("--".equals(m2016H)){
                m2016H="0";
            }
            if("--".equals(m2017H)){
                m2017H="0";
            }


            String m2015L=allRecord.getRecord_2015().getDifen();
            String m2016L=allRecord.getRecord_2016().getDifen();
            String m2017L=allRecord.getRecord_2017().getDifen();
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

        }catch (Exception e){
            Log.i("e",e.getMessage());
        }


        //LineDataSet可以看做是一条线
        LineDataSet dataSet1 = new LineDataSet(entryList1,"最高分");
        dataSet1.setLineWidth(2.5f);
        dataSet1.setCircleSize(4.5f);
        dataSet1.setHighLightColor(Color.RED);//设置点击某个点时，横竖两条线的颜色
        dataSet1.setDrawValues(true);//是否在点上绘制Value

        LineDataSet dataSet2 = new LineDataSet(entryList2,"最低分");
        dataSet1.setLineWidth(2.5f);
        dataSet1.setCircleSize(4.5f);
        dataSet1.setHighLightColor(Color.GREEN);//设置点击某个点时，横竖两条线的颜色
        dataSet1.setDrawValues(true);//是否在点上绘制Value

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



    @OnClick({R.id.addAtention_tv,R.id.collegeDetails_tv})
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.addAtention_tv:
                Toast.makeText(this, "加关注", Toast.LENGTH_SHORT).show();
            break;
            case R.id.collegeDetails_tv:
                Intent intent=new Intent(PriorityCollegeDetailsActivity.this, CollegeActivity.class);
                intent.putExtra("id",CollegeIdStr);
                intent.putExtra("name",CollegeNameStr);
                startActivity(intent);
                break;

            default:
            break;


        }
    }
//**************折线统计图结束*******************************************
}
