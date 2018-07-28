package com.wilshion.oa.ui.activity.weather;

import android.os.Bundle;
import android.view.View;

import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseTitleBarActivity;

/**
 * Created by Wilshion on 2018/7/26 09:57.
 * [description : 天气]
 * [version : 1.0]
 */
public class WeatherActivity extends BaseTitleBarActivity implements View.OnClickListener {
    @Override
    protected void setTitleBar() {
        setTitle("天气预报");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_weather;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        findViewById(R.id.tv_search_weather).setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId){
            case R.id.tv_search_weather:
            {

            }
                break;
            default:

                break;
        }
    }
}
