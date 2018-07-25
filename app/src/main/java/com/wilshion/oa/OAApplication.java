package com.wilshion.oa;

import android.app.Application;

import com.wilshion.common.utils.Utils;

/**
 * Created by Wilshion on 2018/7/25 14:54.
 * [description : Application,若想调用ApplicationContext，可使用 Utils.getContext 方法获取]
 * [version : 1.0]
 */
public class OAApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
    
    
}
