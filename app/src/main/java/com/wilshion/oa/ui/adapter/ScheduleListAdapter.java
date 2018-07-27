package com.wilshion.oa.ui.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.bean.ScheduleBean;

/**
 * Created by Wilshion on 2018/7/26 11:10.
 * [description : 今日日程 adapter]
 * [version : 1.0]
 */
public class ScheduleListAdapter extends BaseQuickAdapter<ScheduleBean, BaseViewHolder> {
    public ScheduleListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ScheduleBean item) {
        String startTime = item.getCAL_TIME();
        String endTime = item.getEND_TIME();
        String content = item.getCONTENT();

        helper.setText(R.id.tv_title, startTime + "-" + endTime + " " + content);
        helper.getView(R.id.tv_sub_title).setVisibility(View.GONE);
    }
}
