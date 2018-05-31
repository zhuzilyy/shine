package com.qianyi.shine.ui.college.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qianyi.shine.R;
import com.qianyi.shine.ui.home.bean.ProfessionMajorJobPriorBean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/31.
 */

public class MajorAndJobAdapter extends BaseAdapter {
    private Context mContext;

    public MajorAndJobAdapter(Context mContext, List<ProfessionMajorJobPriorBean.ProfessionMajorJobPriorData.ProfessionMajorJobPriorInfo.ProfessionMajorJobPriorList.EnrollArr> list) {
        this.mContext = mContext;
        this.list = list;
    }

    private List<ProfessionMajorJobPriorBean.ProfessionMajorJobPriorData.ProfessionMajorJobPriorInfo.ProfessionMajorJobPriorList.EnrollArr> list;
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProfessionMajorJobPriorBean.ProfessionMajorJobPriorData.ProfessionMajorJobPriorInfo.ProfessionMajorJobPriorList.EnrollArr item =list.get(position);
        convertView = LayoutInflater.from(mContext).inflate(R.layout.major_job_item,null);
        TextView tv_major_luqu = convertView.findViewById(R.id.tv_major_luqu);

       // TextView textView=convertView.findViewById(R.id.tv_collegeName);
        TextView tv_score = convertView.findViewById(R.id.tv_score);
        TextView tv_luqu = convertView.findViewById(R.id.tv_luqu);
        TextView tv_zhanshengjianzhang = convertView.findViewById(R.id.tv_zhanshengjianzhang);
        TextView tv_2017plan = convertView.findViewById(R.id.tv_2017plan);
        TextView tv_pici=convertView.findViewById(R.id.tv_pici);

        TextView tv_2015renshu = convertView.findViewById(R.id.tv_2015renshu);
        TextView tv_2015zuidi = convertView.findViewById(R.id.tv_2015zuidi);
        TextView tv_2015zuigao = convertView.findViewById(R.id.tv_2015zuigao);

        TextView tv_2016renshu = convertView.findViewById(R.id.tv_2016renshu);
        TextView tv_2016zuidi = convertView.findViewById(R.id.tv_2016zuidi);
        TextView tv_2016zuigao = convertView.findViewById(R.id.tv_2016zuigao);

        TextView tv_2017renshu = convertView.findViewById(R.id.tv_2017renshu);
        TextView tv_2017zuidi = convertView.findViewById(R.id.tv_2017zuidi);
        TextView tv_2017zuigao = convertView.findViewById(R.id.tv_2017zuigao);

        //赋值
        tv_zhanshengjianzhang.setText(item.getZhuanye());
        tv_major_luqu.setText(item.getRate().getRate()+"%");
        tv_2017plan.setText(item.getRecord_2017().getRenshu());
        tv_pici.setText("[ "+item.getRate().getPici()+" ]");


        tv_2015renshu.setText(item.getRecord_2015().getRenshu());
        tv_2015zuidi.setText(item.getRecord_2015().getDifen());
        tv_2015zuigao.setText(item.getRecord_2015().getGaofen());

        tv_2016renshu.setText(item.getRecord_2016().getRenshu());
        tv_2016zuidi.setText(item.getRecord_2016().getDifen());
        tv_2016zuigao.setText(item.getRecord_2016().getGaofen());

        tv_2017renshu.setText(item.getRecord_2017().getRenshu());
        tv_2017zuidi.setText(item.getRecord_2017().getDifen());
        tv_2017zuigao.setText(item.getRecord_2017().getGaofen());
        return convertView;
    }
}
