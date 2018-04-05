package com.qianyi.shine.ui.college.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.qianyi.shine.R;
import com.qianyi.shine.fragment.entity.CollegeEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/4/4.
 */

public class RightProfessionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private Context mContext;
    private List<CollegeEntity> datas;//数据

    //自定义监听事件
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);


    }

    private RightProfessionAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(RightProfessionAdapter.OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    //适配器初始化
    public RightProfessionAdapter(Context context, List<CollegeEntity> datas) {
        mContext = context;
        this.datas = datas;
    }

    @Override
    public int getItemViewType(int position) {
        //判断item类别，是图还是显示页数（图片有URL）
        if (1==1) {
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
            ).inflate(R.layout.lay_rightprofession_item, parent,
                    false);//这个布局就是一个imageview用来显示图片
            RightProfessionAdapter.MyViewHolder holder = new RightProfessionAdapter.MyViewHolder(view);



            return holder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //将数据与item视图进行绑定，如果是MyViewHolder就加载网络图片，如果是MyViewHolder2就显示页数
        if (holder instanceof RightProfessionAdapter.MyViewHolder) {

            // Picasso.with(mContext).load(datas.get(position).getUrl()).into(((MyViewHolder) holder).iv);//加载网络图片
            if(mOnItemClickListener!=null){
                ((RightProfessionAdapter.MyViewHolder) holder).pic_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //把条目的位置回调回去
                        mOnItemClickListener.onItemClick(position);
                    }
                });
            }

        }

    }

    @Override
    public int getItemCount() {
        return 7;//获取数据的个数
    }





    //自定义ViewHolder，用于加载图片
    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView pic_item;



        public MyViewHolder(View view) {
            super(view);
            pic_item = view.findViewById(R.id.pic_item);

        }
    }
}
