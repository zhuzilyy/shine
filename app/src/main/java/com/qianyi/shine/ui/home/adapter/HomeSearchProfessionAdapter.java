package com.qianyi.shine.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.qianyi.shine.R;

/**
 * Created by Administrator on 2018/4/10.
 */

public class HomeSearchProfessionAdapter extends BaseAdapter {
    private Context mContext;

    public HomeSearchProfessionAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override

    public int getCount() {
        return 10;
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
        convertView = LayoutInflater.from(mContext).inflate(R.layout.lay_home_search_profession_item,null);
        return convertView;
    }
}
