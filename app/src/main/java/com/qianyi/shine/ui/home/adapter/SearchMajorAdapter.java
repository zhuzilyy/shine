package com.qianyi.shine.ui.home.adapter;

import android.graphics.Color;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qianyi.shine.R;
import com.qianyi.shine.ui.home.bean.SearchMajorInfo;
import com.qianyi.shine.ui.home.bean.SearchSchoolListInfo;

/**
 * Created by Administrator on 2018/4/7.
 */

public class SearchMajorAdapter extends BaseQuickAdapter<SearchMajorInfo, BaseViewHolder> {
    private int mColors[] = {Color.parseColor("#141485"),Color.parseColor("#ffff00"),Color.parseColor("#0000ff")};
    public SearchMajorAdapter(int layoutResId) {
        super(layoutResId);
    }
    @Override
    protected void convert(BaseViewHolder helper, SearchMajorInfo item) {
       helper.setText(R.id.tv_name,item.getMajor_name());
    }
}
