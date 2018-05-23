package com.qianyi.shine.ui.home.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qianyi.shine.R;
import com.qianyi.shine.ui.home.bean.SchoolInfo;
import com.qianyi.shine.ui.home.bean.SearchSchoolListInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/4/7.
 */

public class SearchCollegeAdapter extends BaseQuickAdapter<SearchSchoolListInfo, BaseViewHolder> {
    private int mColors[] = {Color.parseColor("#141485"),Color.parseColor("#ffff00"),Color.parseColor("#0000ff")};
    public SearchCollegeAdapter(int layoutResId) {
        super(layoutResId);
    }
    @Override
    protected void convert(BaseViewHolder helper, SearchSchoolListInfo item) {
        RoundedImageView iv_college=helper.getView(R.id.roundedImageView);
        Glide.with(mContext).load(item.getLogo()).placeholder(R.mipmap.logo).into(iv_college);
        helper.setText(R.id.tv_rank, "综合排名:"+item.getRank());
        helper.setText(R.id.tv_name, item.getName());
        String is_985 = item.getIs_985();
        String is_211 = item.getIs_211();
        String level_985="",level_211="";
        if (is_985.equals("0")){
            level_985="非985";
        }else if(is_985.equals("1")){
            level_985="985";
        }
        if (is_211.equals("0")){
            level_211="非211";
        }else if(is_211.equals("1")){
            level_211="211";
        }
        helper.setText(R.id.tv_level, level_985+"/"+level_211);
    }
}
