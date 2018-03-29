package com.qianyi.shine.fragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianyi.shine.R;
import com.qianyi.shine.fragment.entity.CollegeEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/3/29.
 */

public class GridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    private Context mContext;
    private List<CollegeEntity> datas;//数据

    //自定义监听事件
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view,int position);

        void onItemLongClick(View view,int position);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    //适配器初始化
    public GridAdapter(Context context, List<CollegeEntity> datas) {
        mContext = context;
        this.datas = datas;
    }

    @Override
    public int getItemViewType(int position) {
        //判断item类别，是图还是显示页数（图片有URL）
        if (!TextUtils.isEmpty(datas.get(position).getCollegeName())) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //根据item类别加载不同ViewHolder
        if (viewType == 0) {
            View view = LayoutInflater.from(mContext
            ).inflate(R.layout.grid_collegeentity_item, parent,
                    false);//这个布局就是一个imageview用来显示图片
            MyViewHolder holder = new MyViewHolder(view);

            //给布局设置点击和长点击监听
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);

            return holder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //将数据与item视图进行绑定，如果是MyViewHolder就加载网络图片，如果是MyViewHolder2就显示页数
        if (holder instanceof MyViewHolder) {

           // Picasso.with(mContext).load(datas.get(position).getUrl()).into(((MyViewHolder) holder).iv);//加载网络图片
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();//获取数据的个数
    }

    //点击事件回调
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v,0);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemLongClick(v,0);
        }
        return false;
    }

    //自定义ViewHolder，用于加载图片
    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;


        public MyViewHolder(View view) {
            super(view);
            iv = view.findViewById(R.id.collegeImg);
        }
    }


}
