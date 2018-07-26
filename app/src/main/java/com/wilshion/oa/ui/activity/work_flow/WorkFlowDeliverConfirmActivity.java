package com.wilshion.oa.ui.activity.work_flow;

import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseTitleBarActivity;

/**
 * Created by Wilshion on 2018/7/26 21:22.
 * [description : 转交提交页面]
 * [version : 1.0]
 */
public class WorkFlowDeliverConfirmActivity extends BaseTitleBarActivity{
    @Override
    protected void setTitleBar() {
        setTitle("工作转交");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_work_flow_deliver_confirm;
    }
}
