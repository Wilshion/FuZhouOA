package com.wilshion.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Wilshion on 2018/7/25 15:08.
 * [description : ]
 * [version : 1.0]
 */
public abstract class BaseUIActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        initView(savedInstanceState);
    }

    /**
     * 获取页面布局id
     *
     * @return
     */
    protected abstract int getLayoutId();


    /**
     * 初始化控件,页面逻辑代码从这里开始
     */
    protected void initView(Bundle savedInstanceState) {

    }
}
