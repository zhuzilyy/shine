package com.qianyi.shine.ui.home.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qianyi.shine.R;
import com.qianyi.shine.ui.home.bean.IntellgenceFillBean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/7.
 */

public class IntellgenceFillAdapter extends BaseQuickAdapter<IntellgenceFillBean, BaseViewHolder> {

    private int mColors[] = {Color.parseColor("#141485"),Color.parseColor("#ffff00"),Color.parseColor("#0000ff")};

    public IntellgenceFillAdapter(int layoutResId, @Nullable List<IntellgenceFillBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IntellgenceFillBean item) {

//        Toast.makeText(mContext, ""+(int)(Math.random()*3+1), Toast.LENGTH_SHORT).show();
//        helper.setTextColor(R.id.AcceptanceValue, mColors[(int)(Math.random()*2+1)]);
//        helper.setTextColor(R.id.AcceptanceIcon,mColors[(int)(Math.random()*2+1)] );



    }
}
