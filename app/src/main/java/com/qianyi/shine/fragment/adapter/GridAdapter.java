package com.qianyi.shine.fragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qianyi.shine.R;
import com.qianyi.shine.fragment.entity.CollegeEntity;
import com.qianyi.shine.ui.home.bean.HomeBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/29.
 */

public class GridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private Context mContext;
    private List<HomeBean.HomeData.HomeInfo.RecommendUniversity> datas;//数据

    //自定义监听事件
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);


    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    //适配器初始化
    public GridAdapter(Context context, List<HomeBean.HomeData.HomeInfo.RecommendUniversity> datas) {
        mContext = context;
        this.datas = datas;
    }

    @Override
    public int getItemViewType(int position) {
        //判断item类别，是图还是显示页数（图片有URL）
        if (!TextUtils.isEmpty(datas.get(position).getId())) {
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



            return holder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //将数据与item视图进行绑定，如果是MyViewHolder就加载网络图片，如果是MyViewHolder2就显示页数
        if (holder instanceof MyViewHolder) {

           // Picasso.with(mContext).load(datas.get(position).getUrl()).into(((MyViewHolder) holder).iv);//加载网络图片
            if(mOnItemClickListener!=null){
                ((MyViewHolder) holder).item_ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //把条目的位置回调回去
                        mOnItemClickListener.onItemClick(position);
                    }
                });
            }
            Glide.with(mContext).load(datas.get(position).getLogo()).into(((MyViewHolder) holder).collegeLogo);
            ((MyViewHolder) holder).collegeName.setText(datas.get(position).getName());
            ((MyViewHolder) holder).collegeDesc.setText(datas.get(position).getAttrtext());

        }

    }

    @Override
    public int getItemCount() {
        return datas.size();//获取数据的个数
    }





    //自定义ViewHolder，用于加载图片
    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView collegeLogo;
        private LinearLayout item_ll;
        private TextView collegeName;
        private TextView collegeDesc;


        public MyViewHolder(View view) {
            super(view);
            collegeLogo = view.findViewById(R.id.collegeImg);
            item_ll = view.findViewById(R.id.item_ll);
            collegeDesc = view.findViewById(R.id.college_desc);
            collegeName = view.findViewById(R.id.college_name);
        }
    }


}
