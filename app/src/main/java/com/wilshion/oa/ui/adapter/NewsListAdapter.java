package com.wilshion.oa.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wilshion.common.utils.EmptyUtils;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.bean.NewsBean;

/**
 * Created by Wilshion on 2018/7/27 22:21.
 * [description : ]
 * [version : 1.0]
 */
public class NewsListAdapter extends BaseQuickAdapter<NewsBean,BaseViewHolder> {
    public NewsListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsBean item) {
        String beginTime = item.getNEWS_TIME();
        String time = "";
        if (!EmptyUtils.isEmpty(beginTime)) {
            time = beginTime.substring(0, 10);
        }
        String title = (helper.getAdapterPosition() + 1) + "." + item.getSUBJECT();
        String subTitle = item.getUSER_NAME() + " " + time;
        helper.setText(R.id.tv_title, title)
                .setText(R.id.tv_sub_title, subTitle);
    }
}
