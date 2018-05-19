package com.qianyi.shine.ui.home.adapter;

import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qianyi.shine.R;
import com.qianyi.shine.ui.home.bean.PriorityCollegeBean;
import com.qianyi.shine.ui.home.bean.SchoolInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/4/7.
 */

public class PriorityCollegeAdapter extends BaseQuickAdapter<SchoolInfo, BaseViewHolder> {
    public PriorityCollegeAdapter(int layoutResId, @Nullable List<SchoolInfo> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, SchoolInfo item) {
        helper.setText(R.id.tv_collegeName, item.getName());
        helper.setText(R.id.tv_rank, item.getRank());
        helper.setText(R.id.tv_type, item.getSchool_type());
        helper.setText(R.id.tv_area, item.getArea());
        helper.setText(R.id.tv_rate, item.getRate());
        helper.setText(R.id.tv_risk, item.getRisk());
        String risk=item.getRisk();
        if (risk.equals("冲刺")){
            helper.setBackgroundRes(R.id.view_risk,R.drawable.circle_red);
        }else if(risk.equals("保守")){
            helper.setBackgroundRes(R.id.view_risk,R.drawable.circle_yellow);
        }else if(risk.equals("稳妥")){
            helper.setBackgroundRes(R.id.view_risk,R.drawable.circle_green);
        }
        /*helper.setText(R.id.collegeName,item.getName());
        //设置等级
        String is_211 = item.getIs_211();
        String is_985 = item.getIs_985();
        if (is_211.equals("1")){
            is_211="211";
        }else{
            is_211="非211";
        }
        if (is_985.equals("1")){
            is_985="985";
        }else{
            is_985="非985";
        }
        helper.setText(R.id.tv_level,is_211+"/"+is_985);
        //综合排名
        helper.setText(R.id.tv_rank,item.getRank());*/
    }
}
