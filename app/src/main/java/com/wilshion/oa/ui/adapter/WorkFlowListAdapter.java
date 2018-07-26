package com.wilshion.oa.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.bean.WorkFlowBean;

/**
 * Created by Wilshion on 2018/7/26 20:37.
 * [description : ]
 * [version : 1.0]
 */
public class WorkFlowListAdapter extends BaseQuickAdapter<WorkFlowBean,BaseViewHolder> {
    public WorkFlowListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkFlowBean item) {
        helper.setText(R.id.tv_title,item.getFLOW_NAME())
                .setText(R.id.tv_sub_title,item.getPRCS_NAME())
                .addOnClickListener(R.id.tv_form)
                .addOnClickListener(R.id.tv_zhuban)
                .addOnClickListener(R.id.tv_deliver)
                .addOnClickListener(R.id.tv_huiqian);
        
    }
}
