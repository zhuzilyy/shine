package com.qianyi.shine.ui.home.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qianyi.shine.fragment.entity.TestEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/4/7.
 */

public class OccupationDetailsAdapter extends BaseQuickAdapter<TestEntity, BaseViewHolder> {
    public OccupationDetailsAdapter(int layoutResId, @Nullable List<TestEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestEntity item) {

    }
}
