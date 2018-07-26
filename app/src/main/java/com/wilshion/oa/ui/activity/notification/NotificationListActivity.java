package com.wilshion.oa.ui.activity.notification;

import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseTitleBarActivity;

/**
 * Created by Wilshion on 2018/7/26 09:45.
 * [description : 通知公告列表]
 * [version : 1.0]
 */
public class NotificationListActivity extends BaseTitleBarActivity {
    @Override
    protected void setTitleBar() {
        setTitle("公告通知");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notification_list;
    }
}
