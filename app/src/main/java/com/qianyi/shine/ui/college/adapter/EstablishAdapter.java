package com.qianyi.shine.ui.college.adapter;

import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qianyi.shine.R;
import com.qianyi.shine.ui.career_planning.entity.SuitableForMeEntity;
import com.qianyi.shine.ui.home.bean.SchoolInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/4/3.
 */

public class EstablishAdapter extends BaseQuickAdapter<SchoolInfo, BaseViewHolder> {
    public EstablishAdapter(int layoutResId, @Nullable List<SchoolInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SchoolInfo item) {
        //设置logo
        RoundedImageView iv_college=helper.getView(R.id.iv_college);
        Glide.with(mContext).load(item.getLogo()).placeholder(R.mipmap.logo).into(iv_college);
        helper.setText(R.id.collegeName,item.getName());
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
        helper.setText(R.id.tv_rank,item.getRank());
    }
}
