package com.wilshion.oa.ui.activity.email;

import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseTitleBarActivity;

/**
 * Created by Wilshion on 2018/7/26 09:45.
 * [description : 邮件详情]
 * [version : 1.0]
 */
public class EmailDetailActivity extends BaseTitleBarActivity {
    @Override
    protected void setTitleBar() {
        setTitle("邮件");
        setRightText("回复");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_email_detail;
    }
}
