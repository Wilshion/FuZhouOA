package com.wilshion.oa.ui.activity.work_flow;

import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseTitleBarActivity;

/**
 * Created by Wilshion on 2018/7/26 09:54.
 * [description : 工作流列表]
 * [version : 1.0]
 */
public class WorkFlowListActivity extends BaseTitleBarActivity {
    @Override
    protected void setTitleBar() {
        setTitle("工作流");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_work_flow_list;
    }
}
