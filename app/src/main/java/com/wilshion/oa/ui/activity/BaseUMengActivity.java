package com.wilshion.oa.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;
import com.wilshion.common.base.BaseUIActivity;

/**
 * Created by Wilshion on 2018/8/15 21:16.
 * [description : ]
 * [version : 1.0]
 */
public abstract class BaseUMengActivity extends BaseUIActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PushAgent.getInstance(this).onAppStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this); // 基础指标统计，不能遗漏
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this); // 基础指标统计，不能遗漏
    }
}
