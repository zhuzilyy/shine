package com.qianyi.shine.ui.home.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kanade.treeadapter.Node;
import com.kanade.treeadapter.TreeAdapter;
import com.kanade.treeadapter.TreeItemClickListener;
import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.ui.college.activity.ProfessionalActivity;
import com.qianyi.shine.ui.home.bean.Major;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/7.
 */

public class SearchOccupationActivity extends BaseActivity {
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.et_searchOccupation)
    EditText et_searchOccupation;
    private List<Major> list;
    private String tag;
    private Intent intent;
    @Override
    protected void initViews() {
        BaseActivity.addActivity(this);
        intent=getIntent();
        if (intent!=null){
            tag=intent.getStringExtra("tag");
        }
    }
    @Override
    protected void initData() {
        list=new ArrayList<>();
        Major A=new Major(1,0,"经济学");
        Major B=new Major(2,0,"经济学");
        Major C=new Major(3,0,"经济学");
        Major D=new Major(4,0,"经济学");
        Major E=new Major(5,0,"经济学");
        list.add(A);
        list.add(B);
        list.add(C);
        list.add(D);
        list.add(E);
        //第二层数据
        for (int i = 6; i <7 ; i++) {
            Major secondMajor=new Major(i,1,"工程经济学",1);
            list.add(secondMajor);
        }
        //第三层数据
        for (int i = 26; i <30 ; i++) {
            Major thirdMajor=new Major(i,6,"土木工程经济学");
            list.add(thirdMajor);
        }
        //第二层数据
        for (int i = 10; i <14 ; i++) {
            Major secondMajor=new Major(i,2,"工程经济学");
            list.add(secondMajor);
        }
        //第二层数据
        for (int i = 14; i <18 ; i++) {
            Major secondMajor=new Major(i,3,"工程经济学");
            list.add(secondMajor);
        }
        //第二层数据
        for (int i = 18; i <22 ; i++) {
            Major secondMajor=new Major(i,4,"工程经济学");
            list.add(secondMajor);
        }
        //第二层数据
        for (int i = 22; i <26 ; i++) {
            Major secondMajor=new Major(i,5,"工程经济学");
            list.add(secondMajor);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final TreeAdapter<Major> adapter = new TreeAdapter<>(this);
        adapter.setNodes(list);
        adapter.setListener(new TreeItemClickListener() {
            @Override
            public void OnClick(Node node) {
               /* if (node.getId() == 3) {
                    adapter.addChildrenById(3, childs);
                }*/
                int level = node.getLevel();
                if (level==2){
                    if (!TextUtils.isEmpty(tag)){
                        //意愿设置里面的查专业
                        if (tag.equals("willingSetting")){
                            Intent intent=new Intent();
                            intent.putExtra("cccupationName",node.getName());
                            setResult(3,intent);
                            finish();
                         //搜职业
                        }else if (tag.equals("searchOccupation")){
                            jumpActivity(SearchOccupationActivity.this,OccupationDetailActivity.class);
                        }
                    }
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }
    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_search_occpation);
    }
    @Override
    protected void initListener() {
        et_searchOccupation.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                String keyWord=et_searchOccupation.getText().toString().trim();
                if (!TextUtils.isEmpty(keyWord)){
                    jumpActivity(SearchOccupationActivity.this,OccupationDetailActivity.class);
                }
                return false;
            }
        });
    }
    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.iv_back})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
