package com.qianyi.shine.ui.college.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qianyi.shine.R;
import com.qianyi.shine.ui.home.bean.ProfessionPriorBean;
import com.qianyi.shine.ui.home.bean.SchoolInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/4/3.
 */

public class EstablishProiAdapter extends BaseQuickAdapter<ProfessionPriorBean.ProfessionPriorData.ProfessionPriorInfo.ProfessionInfoList, BaseViewHolder> {
    private Context mContext;
    public EstablishProiAdapter(Context MContext) {
        super(R.layout.priority_profession_item);
        this.mContext =MContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, ProfessionPriorBean.ProfessionPriorData.ProfessionPriorInfo.ProfessionInfoList item) {
        TextView textView=helper.getView(R.id.tvv_collegeName);
        textView.setText(item.getName());

    }
}
