package com.qianyi.shine.ui.mine.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qianyi.shine.R;
import com.qianyi.shine.ui.mine.bean.CollectionMajorListInfo;

/**
 * Created by Administrator on 2018/4/3.
 */

public class MajorAdapter extends BaseQuickAdapter<CollectionMajorListInfo, BaseViewHolder>{
    public MajorAdapter(int layoutResId) {
        super(layoutResId);
    }
    @Override
    protected void convert(BaseViewHolder helper, CollectionMajorListInfo item) {
        helper.setText(R.id.tv_name,item.getMajor_name());
    }
}
