package com.wilshion.oa.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.main.HomeDataSource;

import java.util.List;

/**
 * Created by Wilshion on 2018/7/25 21:52.
 * [description : ]
 * [version : 1.0]
 */
public class MainAdapter extends BaseQuickAdapter<HomeDataSource.HomeItemBean, BaseViewHolder> {
    public MainAdapter(int layoutResId, @Nullable List<HomeDataSource.HomeItemBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeDataSource.HomeItemBean item) {
        helper.setImageResource(R.id.iv_icon, item.resId);
        helper.setText(R.id.tv_desc, item.title);

        int position = helper.getAdapterPosition();

//        int color;
//        switch (position) {
//            case 0:
//            case 2:
//            case 5:
//            case 7:
//            case 8:
//            case 10:
//                color = R.color.system_view_gray;
//                break;
//            default:
//                color = R.color.blue;
//                break;
//        }
//        helper.getView(R.id.ll_item).setBackgroundColor(color);
    }
}
