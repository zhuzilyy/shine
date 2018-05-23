package com.qianyi.shine.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qianyi.shine.R;
import com.qianyi.shine.ui.account.bean.AreaInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */

public class WillingSelectAreaAdapter extends BaseAdapter {
   private List<AreaInfo> areas;
   private Context context;
    public WillingSelectAreaAdapter(List<AreaInfo> areas, Context context) {
        this.context = context;
        this.areas = areas;
    }

    @Override
    public int getCount() {
        return areas.size();
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
        view=LayoutInflater.from(context).inflate(R.layout.item_select_area,null);
        TextView tv_area=view.findViewById(R.id.tv_area);
        tv_area.setText(areas.get(i).getArea());
        /*if (areas.get(i).getTag().equals("0")){
            tv_area.setBackgroundResource(R.drawable.bg_unselect_area);
        }else if(areas.get(i).getTag().equals("1")){
            tv_area.setBackgroundResource(R.drawable.bg_select_area);
        }*/
        return view;
    }
}
