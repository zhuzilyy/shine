package com.qianyi.shine.ui.mine.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qianyi.shine.ui.mine.bean.ProfessionBean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/3.
 */

public class ProfessionAdapter extends BaseQuickAdapter<ProfessionBean, BaseViewHolder> {
    public ProfessionAdapter(int layoutResId, @Nullable List<ProfessionBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProfessionBean item) {

    }
}
