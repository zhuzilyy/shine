package com.qianyi.shine.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qianyi.shine.R;
import com.qianyi.shine.ui.home.bean.SearchSchoolListInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/10.
 */

public class HomeSearchCollegeAdapter extends BaseAdapter {
    private Context mContext;
    private List<SearchSchoolListInfo> infoList;
    public HomeSearchCollegeAdapter(Context mContext, List<SearchSchoolListInfo> infoList) {
        this.mContext = mContext;
        this.infoList = infoList;
    }
    @Override

    public int getCount() {
        return infoList.size();
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
        ViewHolder viewHolder=null;
        if (convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.lay_home_search_college_item,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        SearchSchoolListInfo searchSchoolListInfo = infoList.get(position);
        Glide.with(mContext).load(searchSchoolListInfo.getLogo()).placeholder(R.mipmap.logo).into(viewHolder.roundedImageView);
        viewHolder.tv_name.setText("综合排名:"+searchSchoolListInfo.getName());

        String is_985 = searchSchoolListInfo.getIs_985();
        String is_211 = searchSchoolListInfo.getIs_211();
        String level_985="",level_211="";
        if (is_985.equals("0")){
            level_985="非985";
        }else if(is_985.equals("1")){
            level_985="985";
        }
        if (is_211.equals("0")){
            level_211="非211";
        }else if(is_211.equals("1")){
            level_211="211";
        }
        viewHolder.tv_level.setText(searchSchoolListInfo.getIs_211()+searchSchoolListInfo.getIs_985());
        viewHolder.tv_level.setText(level_985+"/"+level_211);
        viewHolder.tv_rank.setText("综合排名:"+searchSchoolListInfo.getRank());
        return convertView;
    }
    static class ViewHolder{
        @BindView(R.id.roundedImageView)
        RoundedImageView roundedImageView;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_rank)
        TextView tv_rank;
        @BindView(R.id.tv_level)
        TextView tv_level;
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
