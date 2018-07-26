package com.wilshion.oa.ui.activity.staff_search;

import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseTitleBarActivity;

/**
 * Created by Wilshion on 2018/7/26 09:55.
 * [description : 人员搜索]
 * [version : 1.0]
 */
public class StaffSearchActivity extends BaseTitleBarActivity {
    @Override
    protected void setTitleBar() {
        setTitle("人员查询");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_staff_search;
    }
}
