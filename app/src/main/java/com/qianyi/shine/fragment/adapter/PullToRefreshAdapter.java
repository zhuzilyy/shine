package com.qianyi.shine.fragment.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qianyi.shine.R;
import com.qianyi.shine.fragment.entity.Status;
import com.qianyi.shine.fragment.entity.TestEntity;
import com.qianyi.shine.ui.account.bean.LoginBean;
import com.qianyi.shine.ui.home.bean.HomeBean;
import com.qianyi.shine.utils.SpannableStringUtils;
import com.qianyi.shine.utils.ToastUtils;

/**
 * 文 件 名: PullToRefreshAdapter
 * 创 建 人: Allen
 * 创建日期: 16/12/24 19:55
 * 邮   箱: AllenCoder@126.com
 * 修改时间：
 * 修改备注：
 */
public class PullToRefreshAdapter extends BaseQuickAdapter<HomeBean.HomeData.HomeInfo.Article, BaseViewHolder> {
    private Context mContext;

    public PullToRefreshAdapter(FragmentActivity activity) {
        super(R.layout.layout_toutiao, null);
        mContext = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.HomeData.HomeInfo.Article item) {
        ImageView imageView = helper.getView(R.id.item_img);
        Glide.with(mContext).load(item.getImage()).into(imageView);
        helper.setText(R.id.item_desc, item.getTitle());
        helper.setText(R.id.time,item.getCreate_time());
        helper.setText(R.id.readtimes,item.getHits()+"阅读");


//        Toast.makeText(mContext, helper.getLayoutPosition()+"", Toast.LENGTH_SHORT).show();
//        Log.i("ppp",item+"");
//        helper.setImageResource(R.id.img,R.mipmap.animation_img1);
//        helper.setText(R.id.tweetName,"Hoteis in Rio de Janeiro");
//        String msg="\"He was one of Australia's most of distinguished artistes, renowned for his portraits\"";
//        ( (TextView)helper.getView(R.id.tweetText)).setText(SpannableStringUtils.getBuilder(msg).append("landscapes and nedes").setClickSpan(clickableSpan).create());
//        ( (TextView)helper.getView(R.id.tweetText)).setMovementMethod(LinkMovementMethod.getInstance());
    }

    ClickableSpan clickableSpan = new ClickableSpan() {
        @Override
        public void onClick(View widget) {
            ToastUtils.showShortToast("事件触发了 landscapes and nedes");
        }

        @Override
        public void updateDrawState(TextPaint ds) {
//            ds.setColor(Utils.getContext().getResources().getColor(R.color.clickspan_color));
//            ds.setUnderlineText(true);
        }
    };


}
