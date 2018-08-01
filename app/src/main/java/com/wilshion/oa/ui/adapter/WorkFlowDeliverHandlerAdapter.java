package com.wilshion.oa.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.bean.WorkFlowDeliverBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wilshion on 2018/8/1 21:35.
 * [description : 工作流转交---经办人适配器]
 * [version : 1.0]
 */
public class WorkFlowDeliverHandlerAdapter extends
        BaseQuickAdapter<WorkFlowDeliverBean.FlowProcessesBean.AssitHandlerBean, BaseViewHolder> {
    private List<Boolean> mSelectedList = new ArrayList<>();
    
    public WorkFlowDeliverHandlerAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    public void setNewData(@Nullable List<WorkFlowDeliverBean.FlowProcessesBean.AssitHandlerBean> data) {
        super.setNewData(data);
        if (data == null)
            return;

        mSelectedList.clear();
        for (int i = 0; i < data.size(); i++) {
            mSelectedList.add(false);
        }
    }

    @Override
    protected void convert(final BaseViewHolder helper, WorkFlowDeliverBean.FlowProcessesBean.AssitHandlerBean item) {
        helper.setText(R.id.tv_name,item.getUserName());
        final int position = helper.getAdapterPosition();
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               ImageView imageView = helper.getView(R.id.iv_select_flag);
               boolean isSelected = !mSelectedList.get(position);
               if (isSelected){
                   imageView.setImageResource(R.mipmap.ic_launcher);
               }else {
                   imageView.setImageResource(R.drawable.ic_work_flow_num);
               }
               mSelectedList.set(position,isSelected);
            }
        });
    }
    
    public List<WorkFlowDeliverBean.FlowProcessesBean.AssitHandlerBean> getSelectedData(){
        List<WorkFlowDeliverBean.FlowProcessesBean.AssitHandlerBean> result = new ArrayList<>();
        for (int i = 0; i < mSelectedList.size(); i++) {
            boolean selected = mSelectedList.get(i);
            if (selected){
                result.add(getData().get(i));
            }
        }
        return result;
    }
}
