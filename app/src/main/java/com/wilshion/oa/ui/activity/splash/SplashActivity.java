package com.wilshion.oa.ui.activity.splash;

import android.os.Bundle;
import android.os.Handler;

import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.BaseUMengActivity;
import com.wilshion.oa.ui.activity.LoginActivity;

/**
 * Created by Wilshion on 2018/7/31 09:46.
 * [description : ]
 * [version : 1.0]
 */
public class SplashActivity extends BaseUMengActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                goToActivity(LoginActivity.class);
            }
        },2000);
    }
}
