package com.qianyi.shine.ui.gaokao_news.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qianyi.shine.R;
import com.qianyi.shine.ui.gaokao_news.entivity.ArticleBean;

/**
 * Created by Administrator on 2018/3/31.
 */

public class GaokaoAdapter extends BaseQuickAdapter<ArticleBean.AriticleData.AriticleInfo.AriticleList, BaseViewHolder> {
    private Context mContext;
    public GaokaoAdapter(Context MContext) {
        super(R.layout.layout_gaokao_item);
        this.mContext =MContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleBean.AriticleData.AriticleInfo.AriticleList item) {
        ImageView imageView = helper.getView(R.id.item_img);
        Glide.with(mContext).load(item.getImage()).into(imageView);
        helper.setText(R.id.item_desc, item.getTitle());
        helper.setText(R.id.time,item.getCreate_time());
        helper.setText(R.id.readtimes,item.getHits()+"阅读");


    }
}
