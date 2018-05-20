package com.qianyi.shine.ui.mine.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qianyi.shine.R;
import com.qianyi.shine.ui.mine.bean.MeasurementBean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/3.
 */

public class MeasurementAdapter extends BaseQuickAdapter<MeasurementBean.MeasurementData.MeasurementInfo.CollectionJobList, BaseViewHolder> {
    public MeasurementAdapter(int layoutResId, @Nullable List<MeasurementBean.MeasurementData.MeasurementInfo.CollectionJobList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MeasurementBean.MeasurementData.MeasurementInfo.CollectionJobList item) {



        TextView tv_content = helper.getView(R.id.tv_content);

        helper.setText(R.id.tv_content, "");
        helper.setText(R.id.tv_name,item.getTest_type());
        helper.setText(R.id.tv_time,item.getCreate_time());



    }
}
