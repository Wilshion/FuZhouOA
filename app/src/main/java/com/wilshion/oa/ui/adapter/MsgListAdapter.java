package com.wilshion.oa.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.bean.MsgBean;

/**
 * Created by Wilshion on 2018/7/26 11:10.
 * [description : ]
 * [version : 1.0]
 */
public class MsgListAdapter extends BaseQuickAdapter<MsgBean, BaseViewHolder> {
    public MsgListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MsgBean item) {
        String author = item.getUSER_NAME();
        String time = item.getSEND_TIME();
        int position = helper.getAdapterPosition() + 1;
        helper.setText(R.id.tv_title, position + "." + item.getCONTENT());
        helper.setText(R.id.tv_author_time, author + "  " + time);
    }
}
