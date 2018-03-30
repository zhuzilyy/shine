package com.qianyi.shine.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiHome;
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.data.DataServer;
import com.qianyi.shine.fragment.adapter.GridAdapter;
import com.qianyi.shine.fragment.adapter.PullToRefreshAdapter;
import com.qianyi.shine.fragment.entity.CollegeEntity;
import com.qianyi.shine.fragment.entity.Status;
import com.qianyi.shine.fragment.entity.TestEntity;
import com.qianyi.shine.loadmore.CustomLoadMoreView;
import com.qianyi.shine.ui.account.view.MEditText;
import com.qianyi.shine.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;


/**
 * Created by NEUNB on 2018/3/19.
 */



public class HomeFragment extends BaseFragment {
    private View view_home;
    @BindView(R.id.rv_list)
    public RecyclerView mRecyclerView;
    @BindView(R.id.swipeLayout)
    public SwipeRefreshLayout mSwipeRefreshLayout;
    private PullToRefreshAdapter mAdapter;
    private int mNextRequestPage = 1;
    private static final int PAGE_SIZE = 6;
    private RecyclerView main_headRv;
    List<TestEntity> testEntities;
    private GridAdapter CollegeAdapter;
    private List<CollegeEntity> listCollege=new ArrayList<>();
    private MEditText editText;

    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        view_home=inflater.inflate(R.layout.fragment_home,null);
        ButterKnife.bind(this,view_home);
        return view_home;
    }

    @Override
    protected void initViews() {
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //上拉加载
        initAdapter();
        //添加head
        addHeadView();
        //下拉刷新
        initRefreshLayout();
        mSwipeRefreshLayout.setRefreshing(true);
        refresh();
    }

    @Override
    protected void initData() {
        testEntities=new ArrayList<>();
        testEntities.add(new TestEntity(R.mipmap.toutiao,"福克斯的回复回复康师傅ISO花花覅会滴啊上花覅胡覅武器哈佛付款后覅if刚切完就看你看了 一和覅偶"));
        testEntities.add(new TestEntity(R.mipmap.tou2,"佛菩萨的反馈ljkfosj jfoisjefoskjfpos 一和覅偶"));
        testEntities.add(new TestEntity(R.mipmap.tou3,"FJSKLJFOFJOWIJOIJFOJFLSJFOSEJFOSJDLFOSFUOWEJOF JFOWJFOWJFOJHFKSHFIUHFIOWEHJO"));
        testEntities.add(new TestEntity(R.mipmap.toutiao,"福克斯的回复回复康师傅ISO花花覅会滴啊上花覅胡覅武器哈佛付款后覅if刚切完就看你看了 一和覅偶"));
        testEntities.add(new TestEntity(R.mipmap.tou2,"佛菩萨的反馈ljkfosj jfoisjefoskjfpos 一和覅偶"));
        testEntities.add(new TestEntity(R.mipmap.tou3,"FJSKLJFOFJOWIJOIJFOJFLSJFOSEJFOSJDLFOSFUOWEJOF JFOWJFOWJFOJHFKSHFIUHFIOWEHJO"));
        testEntities.add(new TestEntity(R.mipmap.toutiao,"福克斯的回复回复康师傅ISO花花覅会滴啊上花覅胡覅武器哈佛付款后覅if刚切完就看你看了 一和覅偶"));
        testEntities.add(new TestEntity(R.mipmap.tou2,"佛菩萨的反馈ljkfosj jfoisjefoskjfpos 一和覅偶"));
        testEntities.add(new TestEntity(R.mipmap.tou3,"FJSKLJFOFJOWIJOIJFOJFLSJFOSEJFOSJDLFOSFUOWEJOF JFOWJFOWJFOJHFKSHFIUHFIOWEHJO"));

        //*********************************************
        listCollege.add(new CollegeEntity(R.mipmap.college_logo01,"","北京大学","北京市/综合/211"));
        listCollege.add(new CollegeEntity(R.mipmap.college_logo02,"","清华大学","北京市/综合/211"));
        listCollege.add(new CollegeEntity(R.mipmap.college_logo01,"","西京大学","北京市/综合/211"));
        listCollege.add(new CollegeEntity(R.mipmap.college_logo02,"","东北农业大学","北京市/综合/211"));
        listCollege.add(new CollegeEntity(R.mipmap.college_logo02,"","齐齐哈尔大学","北京市/综合/211"));
        listCollege.add(new CollegeEntity(R.mipmap.college_logo01,"","西北农林大学","北京市/综合/211"));
        listCollege.add(new CollegeEntity(R.mipmap.college_logo02,"","长春大学","北京市/综合/211"));
        listCollege.add(new CollegeEntity(R.mipmap.college_logo01,"","吉林动画学院","北京市/综合/211"));

    }

    @Override
    protected void initListener() {

    }
    private void initAdapter() {
        mAdapter = new PullToRefreshAdapter();
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mAdapter.setPreLoadNumber(3);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                Toast.makeText(getActivity(), Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });
        /**
         * 推荐大学适配器
         */
        CollegeAdapter=new GridAdapter(getActivity(),listCollege);

    }
    private void addHeadView() {

        Typeface typeface1=Typeface.createFromAsset(getActivity().getAssets(),"fonts/bebas.ttf");
        View headView = getLayoutInflater().inflate(R.layout.head_view, (ViewGroup) mRecyclerView.getParent(), false);
        TextView count= headView.findViewById(R.id.suitCount);
        TextView count2=headView.findViewById(R.id.count2);
        TextView count3=headView.findViewById(R.id.count3);
        TextView count4=headView.findViewById(R.id.count4);
        count.setTypeface(typeface1);
        count2.setTypeface(typeface1);
        count3.setTypeface(typeface1);
        count4.setTypeface(typeface1);
        //大学推荐[横向滑动的recyclerView]
        main_headRv= headView.findViewById(R.id.main_headRv);
        editText=headView.findViewById(R.id.main_search);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),1,GridLayoutManager.HORIZONTAL,false);
        main_headRv.setFocusable(false);
        main_headRv.setLayoutManager(gridLayoutManager);
        main_headRv.setAdapter(CollegeAdapter);
        //推荐大学点击事件
        CollegeAdapter.setOnItemClickListener(new GridAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(mActivity, "position"+position, Toast.LENGTH_SHORT).show();
            }
        });


        /***
         * 监听软键盘右下角的搜索
         */
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Toast.makeText(mActivity, EditorInfo.IME_ACTION_SEARCH+" ==", Toast.LENGTH_SHORT).show();
                /*判断是否是“search”键*/
                if(actionId == EditorInfo.IME_ACTION_NEXT){

                    /*隐藏软键盘*/
                    InputMethodManager imm = (InputMethodManager) v
                            .getContext().getSystemService(
                                    Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(
                                v.getApplicationWindowToken(), 0);
                    }

                    Toast.makeText(mActivity, editText.getText().toLowerCase(), Toast.LENGTH_SHORT).show();


                    return true;
                }
                return false;
            }
        });

//        headView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mAdapter.setNewData(null);
//                mAdapter.setLoadMoreView(new CustomLoadMoreView());
//                mRecyclerView.setAdapter(mAdapter);
//                Toast.makeText(getActivity(), "change complete", Toast.LENGTH_LONG).show();
//
//                mSwipeRefreshLayout.setRefreshing(true);
//                refresh();
//            }
//        });
        mAdapter.addHeaderView(headView);
    }
    private void initRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               refresh();
            }
        });
    }
    //刷新
    private void refresh() {
        mNextRequestPage = 1;
        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        apiHome.refresh("http://www.baidu.com", mNextRequestPage, new com.qianyi.shine.callbcak.RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, String s) {
                Log.i("ppp","131"+s);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setData(true,testEntities);
                        mAdapter.setEnableLoadMore(true);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                Log.i("ppp","132"+e);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setData(true,testEntities);
                        mAdapter.setEnableLoadMore(true);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });


            }
        });

    }
    //加载
    private void loadMore() {

        apiHome.loadMore("http://www.baidu.com", mNextRequestPage, new com.qianyi.shine.callbcak.RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, String s) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setData(false, testEntities);
                    }
                });

            }

            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.loadMoreFail();
                        Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }

    private void setData(boolean isRefresh, List data) {
        mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            mAdapter.setNewData(data);
        } else {
            if (size > 0) {
                mAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            mAdapter.loadMoreEnd(isRefresh);
            Toast.makeText(getActivity(), "第一页如果不够一页就不显示没有更多数据布局", Toast.LENGTH_SHORT).show();
        } else {
            mAdapter.loadMoreComplete();
        }
    }


}
