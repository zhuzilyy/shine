package com.qianyi.shine.ui.mine.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qianyi.shine.R;
import com.qianyi.shine.ui.mine.bean.MessageBean;
import com.qianyi.shine.ui.mine.bean.MessageListInfo;

import java.util.List;

/**
 * Created by NEUNB on 2018/4/4.
 */

public class MessageAdapter extends BaseQuickAdapter<MessageListInfo, BaseViewHolder> {
    public MessageAdapter(int layoutResId) {
        super(layoutResId);
    }
    @Override
    protected void convert(BaseViewHolder helper, MessageListInfo item) {
        String status = item.getStatus();
        if (status.equals("1")){
            helper.setImageResource(R.id.iv_read_state,R.mipmap.message_read);
        }else if(status.equals("0")){
            helper.setImageResource(R.id.iv_read_state,R.mipmap.message_unread);
        }
        helper.setText(R.id.tv_title,item.getTitle());
        helper.setText(R.id.tv_content,item.getDescription());
    }
}
