package com.qianyi.shine.ui.home.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.api.apiHome;
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.dialog.CustomLoadingDialog;
import com.qianyi.shine.receiver.MyReceiver;
import com.qianyi.shine.ui.home.adapter.HomeSearchCollegeAdapter;
import com.qianyi.shine.ui.home.bean.SearchSchoolBean;
import com.qianyi.shine.ui.home.bean.SearchSchoolListInfo;
import com.qianyi.shine.ui.mine.bean.CollegeBean;
import com.qianyi.shine.ui.mine.bean.UniversityInfo;
import com.qianyi.shine.utils.SPUtils;
import com.qianyi.shine.utils.ToastUtils;
import com.qianyi.shine.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/10.
 */

public class SearchHomeCollegeFragment extends BaseFragment {
    @BindView(R.id.listView)
    public ListView listView;
    @BindView(R.id.no_data_rl)
    RelativeLayout no_data_rl;
    @BindView(R.id.no_internet_rl)
    RelativeLayout no_internet_rl;
    @BindView(R.id.reload)
    TextView reload;
    private String keyWord;
    private HomeSearchCollegeAdapter collegeAdapter;
    private CustomLoadingDialog customLoadingDialog;
    private List<SearchSchoolListInfo> infoList;
    private MyReceiver myReceiver;
    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_search_homecollege,null);
    }
    @Override
    protected void initViews() {
        infoList=new ArrayList<>();
        collegeAdapter = new HomeSearchCollegeAdapter(getActivity(),infoList);
        listView.setAdapter(collegeAdapter);
        customLoadingDialog=new CustomLoadingDialog(getContext());

        myReceiver=new MyReceiver();
        String keyWord = myReceiver.getKeyWord();
        Toast.makeText(getActivity(), keyWord, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void initData() {
        if (!Utils.hasInternet()){
            listView.setVisibility(View.GONE);
            no_internet_rl.setVisibility(View.VISIBLE);
            no_data_rl.setVisibility(View.GONE);
        }else{
            customLoadingDialog.show();
            getData(keyWord);
        }
        //点击重新加载数据
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData(keyWord);
            }
        });

    }
    @Override
    protected void initListener() {

    }
    @Override
    public void onResume() {
        super.onResume();
        keyWord= (String) SPUtils.get(getActivity(),"keyWord","");
        getData(keyWord);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    //获取数据
    private void getData(String keyWord) {
        Toast.makeText(getActivity(), "1111111111111", Toast.LENGTH_SHORT).show();
        apiHome.searchCollege(apiConstant.SEARCH_COLLEGE, keyWord, 1, new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        customLoadingDialog.dismiss();
                        Log.i("tag",s);
                        Gson gson=new Gson();
                        SearchSchoolBean searchSchoolBean = gson.fromJson(s, SearchSchoolBean.class);
                        if(searchSchoolBean.getCode().equals("0")){
                            List<SearchSchoolListInfo> universityList = searchSchoolBean.getData().getInfo().getRecommendUniversityList();
                            //数据不为空
                            if (universityList!=null && universityList.size()>0){
                                infoList.addAll(universityList);
                                collegeAdapter.notifyDataSetChanged();
                                listView.setVisibility(View.VISIBLE);
                                no_internet_rl.setVisibility(View.GONE);
                                no_data_rl.setVisibility(View.GONE);
                            }
                        }else{
                            listView.setVisibility(View.GONE);
                            no_internet_rl.setVisibility(View.GONE);
                            no_data_rl.setVisibility(View.VISIBLE);
                        }

                    }
                });
            }
            @Override
            public void onEror(Call call, int statusCode, Exception e) {

            }
        });
    }
}
