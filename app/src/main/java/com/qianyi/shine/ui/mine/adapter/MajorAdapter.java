package com.qianyi.shine.ui.mine.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qianyi.shine.ui.mine.bean.CollegeBean;
import com.qianyi.shine.ui.mine.bean.MajorBean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/3.
 */

public class MajorAdapter extends BaseQuickAdapter<MajorBean, BaseViewHolder>{
    public MajorAdapter(int layoutResId, @Nullable List<MajorBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, MajorBean item) {

    }
}
