package com.wilshion.oa.ui.activity.msg;

import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.BaseTitleBarActivity;

/**
 * Created by Wilshion on 2018/7/26 09:39.
 * [description : 短信列表]
 * [version : 1.0]
 */
public class MsgListActivity extends BaseTitleBarActivity {
    @Override
    protected void setTitleBar() {
        setTitle("短信");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_msg_list;
    }
}
