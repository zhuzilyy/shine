package com.qianyi.shine.ui.home.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qianyi.shine.ui.home.bean.IntellgenceFillBean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/7.
 */

public class IntellgenceFillAdapter extends BaseQuickAdapter<IntellgenceFillBean, BaseViewHolder> {
    public IntellgenceFillAdapter(int layoutResId, @Nullable List<IntellgenceFillBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IntellgenceFillBean item) {

    }
}
