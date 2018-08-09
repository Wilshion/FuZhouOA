package com.wilshion.oa.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wilshion.common.utils.LogUtils;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.bean.DeliverAssistHandlerBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wilshion on 2018/8/1 21:35.
 * [description : 工作流转交---经办人适配器]
 * [version : 1.0]
 */
public class WorkFlowDeliverHandlerAdapter extends
        BaseQuickAdapter<DeliverAssistHandlerBean, BaseViewHolder> {
    private List<Boolean> mSelectedList = new ArrayList<>();

    public WorkFlowDeliverHandlerAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    public void setNewData(@Nullable List<DeliverAssistHandlerBean> data) {
        super.setNewData(data);
        if (data == null)
            return;

        mSelectedList.clear();
        for (int i = 0; i < data.size(); i++) {
            mSelectedList.add(false);
        }
    }

    @Override
    protected void convert(final BaseViewHolder helper, DeliverAssistHandlerBean item) {
        helper.setText(R.id.tv_name, item.getUserName());
        final int position = helper.getAdapterPosition();
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView imageView = helper.getView(R.id.iv_select_flag);
                boolean isSelected = !mSelectedList.get(position);
                if (isSelected) {
                    imageView.setImageResource(R.drawable.ic_select);
                } else {
                    imageView.setImageResource(R.drawable.ic_unselect);
                }
                mSelectedList.set(position, isSelected);
            }
        });
    }

    public String getSelectedUserIds() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < mSelectedList.size(); i++) {
            boolean selected = mSelectedList.get(i);
            if (selected) {
                DeliverAssistHandlerBean assitHandlerBean = getItem(i);
                LogUtils.d(assitHandlerBean.getUserName());
                result.append(assitHandlerBean.getSeqId()).append(",");
            }
        }
        result.delete(result.length() - 1, result.length());
        return result.toString();
    }

    public List<DeliverAssistHandlerBean> getSelectedData() {
        List<DeliverAssistHandlerBean> result = new ArrayList<>();
        for (int i = 0; i < mSelectedList.size(); i++) {
            boolean selected = mSelectedList.get(i);
            if (selected) {
                result.add(getData().get(i));
            }
        }
        return result;
    }
}
