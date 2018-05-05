package com.qianyi.shine.ui.home.bean;

import com.kanade.treeadapter.RvTree;
import com.qianyi.shine.R;

/**
 * Created by Administrator on 2018/4/6.
 */
public class Major implements RvTree {
    private long id;
    private long pid;
    private String title;
    private int resId;
    private String tag;
    public Major(){}

    public Major(long id, long pid, String title) {
        this.id = id;
        this.pid = pid;
        this.title = title;
    }
    public Major(long id, long pid, String title,int resId) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.resId = resId;
    }
    @Override
    public long getNid() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
    public long getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public int getImageResId() {
        return R.drawable.ic_detail;
    }
}
