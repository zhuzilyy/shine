package com.qianyi.shine.ui.home.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kanade.treeadapter.Node;
import com.kanade.treeadapter.TreeAdapter;
import com.kanade.treeadapter.TreeItemClickListener;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.api.apiHome;
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.dialog.CustomLoadingDialog;
import com.qianyi.shine.fragment.entity.FirstLevel;
import com.qianyi.shine.fragment.entity.MajorBean;
import com.qianyi.shine.fragment.entity.SecondLevel;
import com.qianyi.shine.fragment.entity.ThirdLevel;
import com.qianyi.shine.ui.home.activity.PriorityProfessionalDetailsActivity;
import com.qianyi.shine.ui.home.bean.Major;
import com.qianyi.shine.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/6.
 */
public class MajorBenkeFragment extends BaseFragment {
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.no_internet_rl)
    RelativeLayout no_internet_rl;
    @BindView(R.id.no_data_rl)
    RelativeLayout no_data_rl;
    private View view_benke;
    private List<Major> majorList;
    private Intent intent;
    private String tag;
    private CustomLoadingDialog customLoadingDialog;
    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        view_benke=inflater.inflate(R.layout.fragment_major_benke,null);
        return view_benke;
    }
    @Override
    protected void initViews() {
        //majorList=new ArrayList<>();
        majorList=new ArrayList<>();
        intent=getActivity().getIntent();
        if (intent!=null){
            tag=intent.getStringExtra("tag");
        }
        customLoadingDialog=new CustomLoadingDialog(getActivity());
    }
    @Override
    protected void initData() {
        getData();
    }
    private void getData() {
        //有网络
        if (Utils.hasInternet()){
            customLoadingDialog.show();
            apiHome.getMajorList(apiConstant.MAJOR_LIST, "7", new RequestCallBack<String>() {
                @Override
                public void onSuccess(Call call, Response response, final String s) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            recyclerView.setVisibility(View.VISIBLE);
                            no_internet_rl.setVisibility(View.GONE);
                            no_data_rl.setVisibility(View.GONE);
                            Gson gson=new Gson();
                            MajorBean majorBean = gson.fromJson(s, MajorBean.class);
                            List<FirstLevel> firstLevelList = majorBean.getData().getInfo().getMajorList();
                            if (firstLevelList.size()>0){
                                //第一层数据
                                for (int i = 0; i < firstLevelList.size(); i++) {
                                    FirstLevel firstLevel = firstLevelList.get(i);
                                    String major_cate =firstLevel .getMajor_cate();
                                    String major_cateId=firstLevel.getMajor_cate_id();
                                    long firstLongId=Long.parseLong(major_cateId);
                                    Major firstMajor=new Major(firstLongId,0,major_cate);
                                    majorList.add(firstMajor);
                                    //Log.i("tag","111"+major_cate);
                                    List<SecondLevel> secondList = firstLevel.getMajor_second_list();
                                    if (secondList!=null && secondList.size()>0){
                                        for (int j = 0; j <secondList.size() ; j++) {
                                            //第二层数据
                                            SecondLevel secondLevel = secondList.get(j);
                                            String secondLevelName = secondLevel.getMajor_second_cate();
                                            String secondLevleId = secondLevel.getMajor_second_cate_id();
                                            long secondLongId=Long.parseLong(secondLevleId);
                                            Major secondMajor=new Major(secondLongId,firstLongId,secondLevelName);
                                            majorList.add(secondMajor);
                                            Log.i("tag","222"+secondLevelName);
                                            List<ThirdLevel> thirdList = secondList.get(j).getMajor_list();
                                            if (thirdList!=null && thirdList.size()>0){
                                                for (int k = 0; k <thirdList.size() ; k++) {
                                                    ThirdLevel thirdLevel = thirdList.get(k);
                                                    String name = thirdLevel.getName();
                                                    String id = thirdLevel.getId();
                                                    long thirdLongId=Long.parseLong(id);
                                                    Major thirdMajor=new Major(thirdLongId,secondLongId,name);
                                                    majorList.add(thirdMajor);
                                                    Log.i("tag","333"+name);
                                                }
                                            }
                                        }
                                    }
                                }
                            }else{
                                recyclerView.setVisibility(View.GONE);
                                no_internet_rl.setVisibility(View.GONE);
                                no_data_rl.setVisibility(View.VISIBLE);
                            }
                            //设置适配器
                            setAdapter(majorList);
                        }
                    });
                }
                @Override
                public void onEror(Call call, int statusCode, Exception e) {
                    customLoadingDialog.dismiss();
                    recyclerView.setVisibility(View.GONE);
                    no_internet_rl.setVisibility(View.VISIBLE);
                    no_data_rl.setVisibility(View.GONE);
                }
            });
        }else{
            recyclerView.setVisibility(View.GONE);
            no_internet_rl.setVisibility(View.VISIBLE);
            no_data_rl.setVisibility(View.GONE);
        }
    }

    //加载适配器
    private void setAdapter(List<Major> majorList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final TreeAdapter<Major> adapter = new TreeAdapter<>(getActivity());
        adapter.setNodes(majorList);
        recyclerView.setAdapter(adapter);
        customLoadingDialog.dismiss();
        adapter.setListener(new TreeItemClickListener() {
            @Override
            public void OnClick(Node node) {
                int level = node.getLevel();
                if (level==2){
                    //startActivity(new Intent(getActivity(), ProfessionalActivity.class));
                    //意愿设置里面的专业设置
                    if (!TextUtils.isEmpty(tag)){
                        Intent intent=null;
                        if (tag.equals("willingSetting")){
                            intent=new Intent();
                            intent.setAction("com.action.setwilling");
                            intent.putExtra("majorName",node.getName());
                            getActivity().sendBroadcast(intent);
                            //专业优先跳转的界面  或是  查专业跳转过来的界面
                        }else if(tag.equals("majorPriority") ||tag.equals("searchMajor")){
                            intent=new Intent(getActivity(), PriorityProfessionalDetailsActivity.class);
                            startActivity(intent);
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void initListener() {

    }
    @OnClick({R.id.reload})
    public void click(View view){
        switch (view.getId()){
            case R.id.reload:
                getData();
                break;
        }
    }
}
