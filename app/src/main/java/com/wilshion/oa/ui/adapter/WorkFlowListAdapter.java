package com.wilshion.oa.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.bean.WorkFlowBean;

/**
 * Created by Wilshion on 2018/7/26 20:37.
 * [description : 工作流列表适配器]
 * [version : 1.0]
 */
public class WorkFlowListAdapter extends BaseQuickAdapter<WorkFlowBean, BaseViewHolder> {
    public WorkFlowListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkFlowBean item) {
        String title = String.format("[%s]-%s-%s", item.getRUN_ID(), item.getFLOW_NAME(), item.getRUN_NAME());
        String subTitle = String.format("第%d步：%s", item.getFLOW_PRCS(), item.getPRCS_NAME());
        helper.setText(R.id.tv_title, title)
                .setText(R.id.tv_sub_title, subTitle)
                .addOnClickListener(R.id.tv_form)
                .addOnClickListener(R.id.tv_zhuban)
                .addOnClickListener(R.id.tv_deliver)
                .addOnClickListener(R.id.tv_sign);

    }
}
