package com.wilshion.oa.ui.activity.email;

import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.BaseTitleBarActivity;

/**
 * Created by Wilshion on 2018/7/26 09:44.
 * [description : 邮件列表]
 * [version : 1.0]
 */
public class EmailListActivity extends BaseTitleBarActivity {
    @Override
    protected void setTitleBar() {
        setTitle("邮件");
        setRightText("写邮件");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_email_list;
    }
}
