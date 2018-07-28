package com.wilshion.oa.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.bean.DiaryBean;

/**
 * Created by Wilshion on 2018/7/28 09:35.
 * [description : ]
 * [version : 1.0]
 */
public class DiaryListAdapter extends BaseQuickAdapter<DiaryBean,BaseViewHolder>{
    public DiaryListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, DiaryBean item) {
        String time = item.getDIA_DATE();
        int position = helper.getAdapterPosition() + 1;
        helper.setText(R.id.tv_title, position + "." + item.getSUBJECT() + "  " + item.getCONTENT());
        helper.setText(R.id.tv_sub_title,  time);
    }
}
