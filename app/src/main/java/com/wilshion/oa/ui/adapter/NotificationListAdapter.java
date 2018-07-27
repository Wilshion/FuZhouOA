package com.wilshion.oa.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wilshion.common.utils.EmptyUtils;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.bean.NotificationBean;

/**
 * Created by Wilshion on 2018/7/27 21:24.
 * [description : ]
 * [version : 1.0]
 */
public class NotificationListAdapter extends BaseQuickAdapter<NotificationBean, BaseViewHolder> {
    public NotificationListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, NotificationBean item) {
        String beginTime = item.getBEGIN_DATE();
        String time = "";
        if (!EmptyUtils.isEmpty(beginTime)) {
            time = item.getBEGIN_DATE().substring(0, 10);
        }
        String title = (helper.getAdapterPosition() + 1) + "." + item.getSUBJECT();
        String subTitle = item.getUSER_NAME() + " " + time;
        helper.setText(R.id.tv_title, title)
                .setText(R.id.tv_sub_title, subTitle);
    }
}
