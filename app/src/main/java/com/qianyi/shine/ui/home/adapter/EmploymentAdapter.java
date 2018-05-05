package com.qianyi.shine.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qianyi.shine.R;
import com.qianyi.shine.ui.home.bean.ArticleInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NEUNB on 2018/4/2.
 */

public class EmploymentAdapter extends BaseAdapter {
    private Context context;
    private List<ArticleInfo> infoList;
    public EmploymentAdapter(Context context, List<ArticleInfo> infoList) {
        this.context = context;
        this.infoList = infoList;
    }

    @Override
    public int getCount() {
        return infoList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view==null){
            view=LayoutInflater.from(context).inflate(R.layout.item_employment,null);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.content.setText(infoList.get(i).getTitle());
        return view;
    }
    static class ViewHolder{
        @BindView(R.id.content)
        TextView content;
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
