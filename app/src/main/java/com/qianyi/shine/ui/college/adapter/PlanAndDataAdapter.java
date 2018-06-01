package com.qianyi.shine.ui.college.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qianyi.shine.R;
import com.qianyi.shine.ui.college.entivity.CollegeScoreBean;
import com.qianyi.shine.ui.home.bean.PrefessionBean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/4.
 */

public class PlanAndDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private Context mContext;
    private List<PrefessionBean.PrefessionData.PrefessionInfo.EnrollArr> datas;//数据

    //自定义监听事件
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);


    }

    private PlanAndDataAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(PlanAndDataAdapter.OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    //适配器初始化
    public PlanAndDataAdapter(Context context, List<PrefessionBean.PrefessionData.PrefessionInfo.EnrollArr> datas) {
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
            ).inflate(R.layout.lay_plan_and_data_item, parent,
                    false);//这个布局就是一个imageview用来显示图片
            PlanAndDataAdapter.MyViewHolder holder = new PlanAndDataAdapter.MyViewHolder(view);

            return holder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //将数据与item视图进行绑定，如果是MyViewHolder就加载网络图片，如果是MyViewHolder2就显示页数
        if (holder instanceof PlanAndDataAdapter.MyViewHolder) {

            PrefessionBean.PrefessionData.PrefessionInfo.EnrollArr record =datas.get(position);
            ((MyViewHolder) holder).tv_title.setText(record.getZhuanye());
            ((MyViewHolder) holder).tv_renshu.setText(record.getRecord_2017().getRenshu());
            ((MyViewHolder) holder).tv_pici.setText("[ "+record.getPici()+" ]");
            //2015
            ((MyViewHolder) holder).tv_2015score.setText(record.getRecord_2015().getRenshu());
            ((MyViewHolder) holder).tv_2015di.setText(record.getRecord_2015().getDifen());
            ((MyViewHolder) holder).tv_2015gao.setText(record.getRecord_2015().getWeici());

            //2016
            ((MyViewHolder) holder).tv_2016score.setText(record.getRecord_2016().getRenshu());
            ((MyViewHolder) holder).tv_2016di.setText(record.getRecord_2016().getDifen());
            ((MyViewHolder) holder).tv_2016gao.setText(record.getRecord_2016().getWeici());

            //2017
            ((MyViewHolder) holder).tv_2017score.setText(record.getRecord_2017().getRenshu());
            ((MyViewHolder) holder).tv_2017di.setText(record.getRecord_2017().getDifen());
            ((MyViewHolder) holder).tv_2017gao.setText(record.getRecord_2017().getWeici());




            // Picasso.with(mContext).load(datas.get(position).getUrl()).into(((MyViewHolder) holder).iv);//加载网络图片
            if(mOnItemClickListener!=null){
//                ((PlanAndDataAdapter.MyViewHolder) holder).pic_item.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        //把条目的位置回调回去
//                        mOnItemClickListener.onItemClick(position);
//                    }
//                });
            }

        }

    }

    @Override
    public int getItemCount() {
        return this.datas.size();//获取数据的个数
    }





    //自定义ViewHolder，用于加载图片
    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title;
        private TextView tv_renshu;
        private TextView tv_pici;

        private TextView tv_2015score;
        private TextView tv_2015di;
        private TextView tv_2015gao;

        private TextView tv_2016score;
        private TextView tv_2016di;
        private TextView tv_2016gao;

        private TextView tv_2017score;
        private TextView tv_2017di;
        private TextView tv_2017gao;




        public MyViewHolder(View view) {
            super(view);
            tv_title = view.findViewById(R.id.tv_title);
            tv_renshu = view.findViewById(R.id.tv_renshu);
            tv_pici = view.findViewById(R.id.tv_pici);

            tv_2015score=view.findViewById(R.id.tv_2015score);
            tv_2015di=view.findViewById(R.id.tv_2015di);
            tv_2015gao=view.findViewById(R.id.tv_2015gao);

            tv_2016score=view.findViewById(R.id.tv_2016score);
            tv_2016di=view.findViewById(R.id.tv_2016di);
            tv_2016gao=view.findViewById(R.id.tv_2016gao);

            tv_2017score=view.findViewById(R.id.tv_2017score);
            tv_2017di=view.findViewById(R.id.tv_2017di);
            tv_2017gao=view.findViewById(R.id.tv_2017gao);

        }
    }
}
