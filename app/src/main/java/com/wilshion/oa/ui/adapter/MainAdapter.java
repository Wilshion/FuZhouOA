package com.wilshion.oa.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wilshion.oa.ui.activity.MainActivity;

import java.util.List;

/**
 * Created by Wilshion on 2018/7/25 21:52.
 * [description : ]
 * [version : 1.0]
 */
public class MainAdapter  extends BaseQuickAdapter<MainActivity.HomeItemBean,BaseViewHolder>{
    public MainAdapter(int layoutResId, @Nullable List<MainActivity.HomeItemBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainActivity.HomeItemBean item) {
        
    }
}
