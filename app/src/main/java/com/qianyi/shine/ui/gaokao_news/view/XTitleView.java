package com.qianyi.shine.ui.gaokao_news.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qianyi.shine.R;


/**
 * Created by Administrator on 2018/1/24.
 */

public class XTitleView extends RelativeLayout {
    private TextView leftTv,rightTv;
    private ImageView ivback,ivRight;
    private RelativeLayout x_title_re;
    public XTitleView(Context context) {
        super(context);
        initViews(null,context);
    }

    public XTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(attrs,context);
    }

    public XTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(attrs,context);
    }


    private void initViews(AttributeSet attrSet,Context mContext){
        LayoutInflater.from(getContext()).inflate(R.layout.lay_x_titleview,this);
        x_title_re=findViewById(R.id.x_title_re);
        leftTv=findViewById(R.id.x_title_tv_left);
        rightTv=findViewById(R.id.x_title_tv_right);
        ivback=findViewById(R.id.x_title_ivback);
        ivRight=findViewById(R.id.x_title_ivRight);

        TypedArray ta=getContext().obtainStyledAttributes(attrSet,R.styleable.XTitleView);
        int N= ta.getIndexCount();
        for (int i = 0; i <N ; i++) {
            int index=ta.getIndex(i);
            switch(index){
                case R.styleable.XTitleView_tv_left:
                    //设置左边文字
                    leftTv.setText(ta.getString(index));
                break;
                case R.styleable.XTitleView_tv_right:
                    //设置右边文字
                    rightTv.setText(ta.getString(index));
                    break;
                case R.styleable.XTitleView_isshow_back:
                    //设置back是否显示
                    setViewVisable(ivback,ta.getInt(index,0));

                    break;
                case R.styleable.XTitleView_isshow_right_iv:
                    //设置右边图标是否显示
                    setViewVisable(ivRight,ta.getInt(index,0));
                    break;
                case R.styleable.XTitleView_isshow_right_tv:
                    //设置右边文字是否显示
                    setViewVisable(rightTv,ta.getInt(index,0));
                    break;
                case R.styleable.XTitleView_back_src:
                    //设置back图标来源
                    Drawable drawable=ta.getDrawable(index);
                    BitmapDrawable bitmapDrawable= (BitmapDrawable) drawable;
                    Bitmap bitmap=bitmapDrawable.getBitmap();
                    ivback.setImageBitmap(bitmap);
                    break;
                case R.styleable.XTitleView_right_iv_scr:
                    //设置右边图标的来源
                    Drawable drawable1=ta.getDrawable(index);
                    BitmapDrawable drawable2= (BitmapDrawable) drawable1;
                    Bitmap bitmap1=drawable2.getBitmap();
                    ivRight.setImageBitmap(bitmap1);
                    break;
                case R.styleable.XTitleView_title_background_color:
                    //设置title的背景颜色
                    x_title_re.setBackgroundColor(ta.getColor(index,0));
                    break;
                case R.styleable.XTitleView_graty_title:
                    setTitleGravity(ta.getInt(index,0));
                    break;
                case R.styleable.XTitleView_titleSize:
                    leftTv.setTextSize(ta.getDimension(index,mContext.getResources().getDimension(R.dimen.textSize_14)));
                    break;
                case R.styleable.XTitleView_titleColor:
                    leftTv.setTextColor(ta.getColor(index,0x000000));
                    break;

                default:
                break;
            }
        }
    }

    /***
     * title文字显示左边还是中部
     * @param anInt
     */
    private void setTitleGravity(int anInt) {
        if(0 == anInt){
            //左边


        }else if(1 == anInt){
            //中间
            RelativeLayout.LayoutParams params=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
           leftTv.setLayoutParams(params);



        }
    }


    /****
     * 设置title
     */
    public void xSetTitle(String titleCotent){
        leftTv.setText(titleCotent);
    }

    /***
     * back的的
     * @param l
     */
    public void setBackListener(OnClickListener l){
        ivback.setOnClickListener(l);
    }
    /***
     * 右边的图标和文字
     */
    public void setRightObjectListener(OnClickListener l){
        ivRight.setOnClickListener(l);
        rightTv.setOnClickListener(l);
    }


    /***
     * 显示隐藏某个view
     * @param v
     * @param visable
     */
    private void setViewVisable(View v, int visable) {
        switch(visable){
            case 0:
                v.setVisibility(VISIBLE);
            break;
            case 1:
                v.setVisibility(INVISIBLE);
                break;
            case 2:
                v.setVisibility(GONE);
                break;

            default:
            break;


        }
    }


}
