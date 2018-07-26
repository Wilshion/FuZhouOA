package com.wilshion.oa.ui.activity.email;

import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseTitleBarActivity;

/**
 * Created by Wilshion on 2018/7/26 15:12.
 * [description : 发送邮件]
 * [version : 1.0]
 */
public class EmailSendActivity extends BaseTitleBarActivity {
    @Override
    protected void setTitleBar() {
        setTitle("写邮件");
        
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_email_send;
    }
}
