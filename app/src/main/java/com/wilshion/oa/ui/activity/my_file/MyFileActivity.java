package com.wilshion.oa.ui.activity.my_file;

import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseTitleBarActivity;

/**
 * Created by Wilshion on 2018/7/26 09:53.
 * [description : 我的文件]
 * [version : 1.0]
 */
public class MyFileActivity extends BaseTitleBarActivity {
    @Override
    protected void setTitleBar() {
        setTitle("我的文件");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_file_list;
    }
}
