package com.wilshion.oa.ui.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.bean.MyDocumentRespBean;

/**
 * Created by Wilshion on 2018/8/7 20:21.
 * [description : ]
 * [version : 1.0]
 */
public class MyDocumentAdapter extends BaseQuickAdapter<MyDocumentRespBean.MyDocumentBean, BaseViewHolder> {
    public MyDocumentAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyDocumentRespBean.MyDocumentBean item) {
        int position = helper.getAdapterPosition() + 1;
        String title = String.format("[%s] - %s - %s", item.getRunId(), item.getFlowName(), item.getRunName());
        String subTitle = String.format("第%d步：%s", item.getFlowPrcs(), item.getPrcsName());
        helper.setText(R.id.tv_num, position + "")
                .setText(R.id.tv_title, title)
                .setText(R.id.tv_sub_title, subTitle)
                .addOnClickListener(R.id.tv_zhuban)
                .addOnClickListener(R.id.tv_deliver)
                .addOnClickListener(R.id.tv_sign);

        helper.getView(R.id.tv_form).setVisibility(View.GONE);
    }
}
