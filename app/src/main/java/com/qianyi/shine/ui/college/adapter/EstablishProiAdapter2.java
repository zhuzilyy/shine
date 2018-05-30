package com.qianyi.shine.ui.college.adapter;

import android.content.Context;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qianyi.shine.R;
import com.qianyi.shine.ui.home.bean.ProfessionMajorJobPriorBean;
import com.qianyi.shine.ui.home.bean.ProfessionPriorBean;

/**
 * Created by Administrator on 2018/4/3.
 */

public class EstablishProiAdapter2 extends BaseQuickAdapter<ProfessionMajorJobPriorBean.ProfessionMajorJobPriorData.ProfessionMajorJobPriorInfo.ProfessionMajorJobPriorList.EnrollArr, BaseViewHolder> {
    private Context mContext;
    public EstablishProiAdapter2(Context MContext) {
        super(R.layout.priority_profession_item);
        this.mContext =MContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, ProfessionMajorJobPriorBean.ProfessionMajorJobPriorData.ProfessionMajorJobPriorInfo.ProfessionMajorJobPriorList.EnrollArr item) {
        TextView textView=helper.getView(R.id.tvv_collegeName);
        TextView tv_score = helper.getView(R.id.tv_score);
        TextView tv_luqu = helper.getView(R.id.tv_luqu);
        TextView tv_zhanshengjianzhang = helper.getView(R.id.tv_zhanshengjianzhang);
        TextView tv_2017plan = helper.getView(R.id.tv_2017plan);
        TextView tv_pici=helper.getView(R.id.tv_pici);

        TextView tv_2015renshu = helper.getView(R.id.tv_2015renshu);
        TextView tv_2015zuidi = helper.getView(R.id.tv_2015zuidi);
        TextView tv_2015zuigao = helper.getView(R.id.tv_2015zuigao);

        TextView tv_2016renshu = helper.getView(R.id.tv_2016renshu);
        TextView tv_2016zuidi = helper.getView(R.id.tv_2016zuidi);
        TextView tv_2016zuigao = helper.getView(R.id.tv_2016zuigao);

        TextView tv_2017renshu = helper.getView(R.id.tv_2017renshu);
        TextView tv_2017zuidi = helper.getView(R.id.tv_2017zuidi);
        TextView tv_2017zuigao = helper.getView(R.id.tv_2017zuigao);

        //赋值
//        textView.setText(item.getName());
//        tv_score.setText(item.getLevel());
//        tv_luqu.setText(item..getRecord_2017().getRate());
//        tv_zhanshengjianzhang.setText(item.getRecruit_students().getMajor_name());
//        tv_2017plan.setText(item.getRecruit_students().getRecord_2017().getRenshu());
//        tv_pici.setText("["+item.getRecruit_students().getPici()+"");


        tv_2015renshu.setText(item.getRecord_2015().getRenshu());
        tv_2015zuidi.setText(item.getRecord_2015().getDifen());
        tv_2015zuigao.setText(item.getRecord_2015().getGaofen());

        tv_2016renshu.setText(item.getRecord_2016().getRenshu());
        tv_2016zuidi.setText(item.getRecord_2016().getDifen());
        tv_2016zuigao.setText(item.getRecord_2016().getGaofen());

        tv_2017renshu.setText(item.getRecord_2017().getRenshu());
        tv_2017zuidi.setText(item.getRecord_2017().getDifen());
        tv_2017zuigao.setText(item.getRecord_2017().getGaofen());
    }
}
