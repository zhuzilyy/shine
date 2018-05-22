package com.qianyi.shine.ui.home.adapter;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qianyi.shine.R;
import com.qianyi.shine.ui.home.bean.MajorSchoolInfo;
import com.qianyi.shine.ui.mine.bean.UniversityInfo;

/**
 * Created by Administrator on 2018/4/3.
 */

public class MajorListAdapter extends BaseQuickAdapter<MajorSchoolInfo, BaseViewHolder> {
    public MajorListAdapter(int layoutResId) {
        super(layoutResId);
    }
    @Override
    protected void convert(BaseViewHolder helper, MajorSchoolInfo item) {
      /*  ImageView imageView = helper.getView(R.id.item_img);
        Glide.with(mContext).load(item.getImage()).into(imageView);
        helper.setText(R.id.item_desc, item.getTitle());
        helper.setText(R.id.time,item.getCreate_time());
        helper.setText(R.id.readtimes,item.getHits()+"阅读");*/
        //设置logo
        RoundedImageView iv_college=helper.getView(R.id.iv_college);
        Glide.with(mContext).load(item.getLogo()).placeholder(R.mipmap.logo).into(iv_college);
        helper.setText(R.id.collegeName,item.getName());
        helper.setText(R.id.tv_area,item.getLevel());
        helper.setText(R.id.tv_type,item.getSchool_type());
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
        if (item.getRank().equals("0")){
            helper.setText(R.id.tv_rank,"排名:暂无数据");
        }else{
            helper.setText(R.id.tv_rank,"排名:"+item.getRank());
        }
    }
}
