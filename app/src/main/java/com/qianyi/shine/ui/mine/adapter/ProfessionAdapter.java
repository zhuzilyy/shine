package com.qianyi.shine.ui.mine.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qianyi.shine.R;
import com.qianyi.shine.ui.mine.bean.CollectionJobListInfo;
import com.qianyi.shine.ui.mine.bean.ProfessionBean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/3.
 */

public class ProfessionAdapter extends BaseQuickAdapter<CollectionJobListInfo, BaseViewHolder> {
    public ProfessionAdapter(int layoutResId) {
        super(layoutResId);
    }
    @Override
    protected void convert(BaseViewHolder helper, CollectionJobListInfo item) {
            helper.setText(R.id.tv_name,item.getJob_name());
    }
}
