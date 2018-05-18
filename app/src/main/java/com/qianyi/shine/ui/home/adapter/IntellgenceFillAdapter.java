package com.qianyi.shine.ui.home.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qianyi.shine.R;
import com.qianyi.shine.ui.home.bean.IntellgenceFillBean;
import com.qianyi.shine.ui.home.bean.SchoolInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/4/7.
 */

public class IntellgenceFillAdapter extends BaseQuickAdapter<SchoolInfo, BaseViewHolder> {
    private int mColors[] = {Color.parseColor("#141485"),Color.parseColor("#ffff00"),Color.parseColor("#0000ff")};
    public IntellgenceFillAdapter(int layoutResId, @Nullable List<SchoolInfo> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, SchoolInfo item) {
        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_levelAndScore, item.getLevel()+"/17年最低分:"+item.getDifen());
        helper.setText(R.id.tv_acceptanceValue, item.getRate());
    }
}
