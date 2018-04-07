package com.qianyi.shine.ui.home.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qianyi.shine.ui.home.bean.PriorityCollegeBean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/7.
 */

public class PriorityCollegeAdapter extends BaseQuickAdapter<PriorityCollegeBean, BaseViewHolder> {
    public PriorityCollegeAdapter(int layoutResId, @Nullable List<PriorityCollegeBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, PriorityCollegeBean item) {
        int layoutPosition = helper.getLayoutPosition();

    }
}
