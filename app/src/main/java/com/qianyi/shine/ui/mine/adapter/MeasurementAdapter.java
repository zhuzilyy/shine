package com.qianyi.shine.ui.mine.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qianyi.shine.ui.mine.bean.MeasurementBean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/3.
 */

public class MeasurementAdapter extends BaseQuickAdapter<MeasurementBean, BaseViewHolder> {
    public MeasurementAdapter(int layoutResId, @Nullable List<MeasurementBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MeasurementBean item) {

    }
}
