package com.wilshion.oa.ui.activity.weather;

import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseTitleBarActivity;

/**
 * Created by Wilshion on 2018/7/26 09:57.
 * [description : 天气]
 * [version : 1.0]
 */
public class WeatherActivity extends BaseTitleBarActivity {
    @Override
    protected void setTitleBar() {
        setTitle("天气预报");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_weather;
    }
}
