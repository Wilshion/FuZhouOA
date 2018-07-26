package com.wilshion.oa.ui.activity.zip_code;

import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.BaseTitleBarActivity;

/**
 * Created by Wilshion on 2018/7/26 09:56.
 * [description : 区号邮编]
 * [version : 1.0]
 */
public class ZipCodeActivity extends BaseTitleBarActivity {
    @Override
    protected void setTitleBar() {
        setTitle("区号邮编查询");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zip_code;
    }
}
