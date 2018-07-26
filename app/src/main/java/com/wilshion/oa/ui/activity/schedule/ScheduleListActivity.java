package com.wilshion.oa.ui.activity.schedule;

import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseTitleBarActivity;

/**
 * Created by Wilshion on 2018/7/26 09:51.
 * [description : 今日日程列表]
 * [version : 1.0]
 */
public class ScheduleListActivity extends BaseTitleBarActivity {
    @Override
    protected void setTitleBar() {
        setTitle("今日日程");
        setRightText("新建日程");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_schedule_list;
    }
}
