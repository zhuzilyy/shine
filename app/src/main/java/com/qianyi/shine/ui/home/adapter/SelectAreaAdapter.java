package com.qianyi.shine.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qianyi.shine.R;

/**
 * Created by Administrator on 2018/4/8.
 */

public class SelectAreaAdapter extends BaseAdapter {
   private String[] areas;
   private Context context;
    public SelectAreaAdapter(String[] areas,Context context) {
        this.context = context;
        this.areas = areas;
    }

    @Override
    public int getCount() {
        return areas.length;
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
        tv_area.setText(areas[i]);
        return view;
    }
}
