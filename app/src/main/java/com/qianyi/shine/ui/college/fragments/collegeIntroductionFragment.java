package com.qianyi.shine.ui.college.fragments;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hedgehog.ratingbar.RatingBar;
import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.fragment.adapter.GridAdapter;
import com.qianyi.shine.fragment.entity.CollegeEntity;
import com.qianyi.shine.ui.account.activity.WebviewActivity;
import com.qianyi.shine.ui.college.activity.BigImgActivity;
import com.qianyi.shine.ui.college.activity.CollegePicBean;
import com.qianyi.shine.ui.college.adapter.PicCollegeAdapter;
import com.qianyi.shine.ui.college.view.MyScrollview;
import com.qianyi.shine.ui.college.view.Star;
import com.qianyi.shine.ui.home.bean.CollegeDetailsBean;
import com.qianyi.shine.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/31.
 */

public class collegeIntroductionFragment extends BaseFragment implements View.OnClickListener{
    @BindView(R.id.PicCollege_rv)
    public RecyclerView PicCollege_rv;
    private PicCollegeAdapter adapter;
    //空数据
    private List<CollegePicBean> listCollege=new ArrayList<>();
    //食宿条件
    @BindView(R.id.Accommodation_re)
    public RelativeLayout Accommodation_re;
    //奖学金设置
    @BindView(R.id.Scholarship_re)
    public RelativeLayout Scholarship_re;
    @BindView(R.id.myScrollview)
    MyScrollview myScrollview;
    private CollegeDetailsBean.CollegeDetailsData.CollegeDetailsInfo collegeDetailsInfo;
    //*****************************
    @BindView(R.id.tv_collegeName) public TextView tv_collegeName;
    @BindView(R.id.tv_introduce) public TextView tv_introduce;
    @BindView(R.id.tv_college_tel) public TextView tv_college_tel;
    @BindView(R.id.tv_college_webset) public TextView tv_college_webset;
    @BindView(R.id.tv_college_address) public TextView tv_college_address;
    @BindView(R.id.tv_nan) public TextView tv_nan;
    @BindView(R.id.tv_nv) public TextView tv_nv;
    @BindView(R.id.id_progress) public ProgressBar progress;
    @BindView(R.id.tv_collegeSitisfied) public TextView tv_collegeSitisfied;
    @BindView(R.id.tv_collegeArea) public TextView tv_collegeArea;
    @BindView(R.id.tv_collegeType) public TextView tv_collegeType;
    @BindView(R.id.tv_collegePici) public TextView tv_collegePici;
    @BindView(R.id.tv_collegeRecommed) public TextView tv_collegeRecommed;

    @BindView(R.id.tv_labs_count) public TextView tv_labs_count; //重点实验室
    @BindView(R.id.tv_masterPoints) public TextView tv_masterPoints;  //硕士点
    @BindView(R.id.tv_doctorPoints) public TextView tv_doctorPoints;  //博士点
    @BindView(R.id.tv_advantage_majors_count)
    public TextView tv_advantage_majors_count;  //重点专业
    @BindView(R.id.no_internet_rl)
    RelativeLayout no_internet_wrong;
    @BindView(R.id.reload)
    TextView reload;
    private MyReceiver myReceiver;
    @BindView(R.id.star) public Star star;


    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        View layoutRes= inflater.inflate(R.layout.fragment_college_introduction,null);
        return layoutRes;
    }

    @Override
    protected void initViews() {
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),3,GridLayoutManager.VERTICAL,false);
        PicCollege_rv.setLayoutManager(gridLayoutManager);
        PicCollege_rv.setFocusable(false);
        adapter=new PicCollegeAdapter(getActivity(),listCollege);
        PicCollege_rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new PicCollegeAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });
        myReceiver=new MyReceiver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("com.action.introduce.success");
        getActivity().registerReceiver(myReceiver,intentFilter);




    }

    @Override
    protected void initData() {
        if (Utils.hasInternet()){

        }else{

        }
    }

    @Override
    protected void initListener() {

    }
    @OnClick({R.id.Accommodation_re,R.id.Scholarship_re})
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.Accommodation_re:
                //住宿条件
                Intent intent_Accommodation = new Intent(getActivity(), WebviewActivity.class);
                intent_Accommodation.putExtra("title","住宿条件");
                intent_Accommodation.putExtra("url","http://www.baidu.com");
                getActivity().startActivity(intent_Accommodation);
                break;
            case R.id.Scholarship_re:
                //奖学金设置
                Intent intent_Scholarship = new Intent(getActivity(), WebviewActivity.class);
                intent_Scholarship.putExtra("title","奖学金设置");
                intent_Scholarship.putExtra("url","http://www.baidu.com");
                getActivity().startActivity(intent_Scholarship);
                break;
            case R.id.acamy_re:
                //院系设置
                Intent intent_Acamy= new Intent(getActivity(), WebviewActivity.class);
                intent_Acamy.putExtra("title","院系设置");
                intent_Acamy.putExtra("url","http://www.baidu.com");
                getActivity().startActivity(intent_Acamy);
                break;

            default:
                break;
        }
    }

    /**
     * 从根部获取数据
     * @param collegeDetailsInfo
     */
    public void setCollegeDataFromRoot(CollegeDetailsBean.CollegeDetailsData.CollegeDetailsInfo collegeDetailsInfo){
        this.collegeDetailsInfo = collegeDetailsInfo;
        if(this.collegeDetailsInfo != null){
            initTransData(this.collegeDetailsInfo);
        }

    }

    /**
     * 赋值数据
     * @param info
     */
    private void initTransData(final CollegeDetailsBean.CollegeDetailsData.CollegeDetailsInfo info) {
        //地区
        tv_collegeArea.setText(info.getArea());
        //类型
        tv_collegeType.setText(info.getType());
        //批次
        tv_collegePici.setText(info.getLevel());
        //推荐指数
        star.setMark(Float.parseFloat(info.getUniversity_satisfaction()));
        //学校名称
        tv_collegeName.setText(info.getName());
        //学校简介
        tv_introduce.setText(info.getIntroduction());
        //学校图片
        listCollege= getSchoolPic(info.getSchool_scenery());
        adapter=new PicCollegeAdapter(getActivity(),listCollege);
        PicCollege_rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new PicCollegeAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), BigImgActivity.class);
                intent.putExtra("bigUrl",listCollege.get(position).getImage_url());
                startActivity(intent);
            }
        });
        //学校电话
        tv_college_tel.setText(info.getPhone());
        //学校网址
        tv_college_webset.setText(info.getWebsite());
        //学校地址
        tv_college_address.setText(info.getAddress());
        //男女比例
        String[]  nannv = getRatil(info.getSexual_ratio());
        if(nannv.length>=2){
            tv_nan.setText(nannv[0]);
            tv_nv.setText(nannv[1]);
        }

        if(nannv.length>=2){
            progress.setProgress(Integer.parseInt(nannv[0]));
        }
         tv_labs_count.setText(info.getUniversity_research().getLabs_count()); //重点实验室
         tv_masterPoints.setText(info.getUniversity_research().getMaster_points());  //硕士点
         tv_doctorPoints.setText(info.getUniversity_research().getDoctor_points());  //博士点
         tv_advantage_majors_count.setText(info.getUniversity_research().getAdvantage_majors_count());  //重点专业




    }

    /***
     * 获取男女人数
     * @param sexual_ratio
     * @return
     */
    private String[] getRatil(String sexual_ratio) {

       return sexual_ratio.split(":");
    }

    private List<CollegePicBean> getSchoolPic(List<CollegeDetailsBean.CollegeDetailsData.CollegeDetailsInfo.SchoolScenery> school_scenery) {
        List<CollegePicBean> collegeEntities = new ArrayList<>();
        for (int i = 0; i <school_scenery.size() ; i++) {
            CollegePicBean bean = new CollegePicBean(school_scenery.get(i).getThumbnail_url(),school_scenery.get(i).getImage_url());
            collegeEntities.add(bean);
        }
        return collegeEntities;
    }
    class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("com.action.introduce.success")){
                myScrollview.setVisibility(View.VISIBLE);
            }
        }
    }

}
