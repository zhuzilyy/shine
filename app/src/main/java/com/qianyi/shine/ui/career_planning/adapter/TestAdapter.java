package com.qianyi.shine.ui.career_planning.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qianyi.shine.fragment.entity.TestEntity;
import com.qianyi.shine.ui.career_planning.entity.SuitableForMeEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/4/2.
 */

public class TestAdapter extends BaseQuickAdapter<SuitableForMeEntity, BaseViewHolder> {
    public TestAdapter(int layoutResId, @Nullable List<SuitableForMeEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SuitableForMeEntity item) {

    }
}
