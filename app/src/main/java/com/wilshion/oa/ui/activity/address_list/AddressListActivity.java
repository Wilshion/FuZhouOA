package com.wilshion.oa.ui.activity.address_list;

import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseTitleBarActivity;

/**
 * Created by Wilshion on 2018/7/26 09:56.
 * [description : 通讯博]
 * [version : 1.0]
 */
public class AddressListActivity extends BaseTitleBarActivity {
    @Override
    protected void setTitleBar() {
        setTitle("通讯簿");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address_list;
    }
}
