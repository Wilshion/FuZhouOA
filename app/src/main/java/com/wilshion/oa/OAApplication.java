package com.wilshion.oa;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.smtt.sdk.QbSdk;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.wilshion.common.utils.LogUtils;
import com.wilshion.common.utils.Utils;
import com.wilshion.oa.push.UMengPushUtil;
import com.wilshion.oa.ui.constant.ConstantKey;

/**
 * Created by Wilshion on 2018/7/25 14:54.
 * [description : Application,若想调用ApplicationContext，可使用 Utils.getContext 方法获取]
 * [version : 1.0]
 */
public class OAApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initUtils();
        initTencentTBS();
        initUMeng();
    }

    /**
     * 初始化友盟
     */
    private void initUMeng() {
        //初始化组件化基础库, 统计SDK/推送SDK/分享SDK都必须调用此初始化接口
        UMConfigure.init(this, ConstantKey.UMENG_APP_KEY, "Umeng", UMConfigure.DEVICE_TYPE_PHONE,
                ConstantKey.UMENG_MESSAGE_SECRET);
        //PushSDK初始化(如使用推送SDK，必须调用此方法)
        UMengPushUtil.getInstance().regist();

        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
    }

    /**
     * 初始化 腾讯TBS
     */
    private void initTencentTBS() {
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                LogUtils.d( " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    private void initUtils() {
        Utils.init(this);
    }

    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                ClassicsHeader header = new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);
                return header;
            }
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                ClassicsFooter footer = new ClassicsFooter(context);
                footer.setBackgroundResource(android.R.color.white);
                footer.setSpinnerStyle(SpinnerStyle.Translate);
                return footer;
            }
        });
    }
}
