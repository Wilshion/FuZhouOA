package com.wilshion.oa.ui.activity.work_log;

import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.BaseTitleBarActivity;

/**
 * Created by Wilshion on 2018/7/26 09:52.
 * [description : 工作日志列表]
 * [version : 1.0]
 */
public class WorkLogListActivity extends BaseTitleBarActivity {
    @Override
    protected void setTitleBar() {
        setTitle("工作日志");
        setRightText("写日志");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_work_log_list;
    }
}
