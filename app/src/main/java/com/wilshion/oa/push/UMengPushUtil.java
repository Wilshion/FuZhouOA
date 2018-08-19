package com.wilshion.oa.push;

import android.content.Intent;
import android.os.Handler;

import com.umeng.message.IUmengCallback;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.wilshion.common.utils.LogUtils;
import com.wilshion.common.utils.Utils;

/**
 * Created by Wilshion on 2018/8/19 09:09.
 * [description : 友盟推送 工具类，单例]
 * [version : 1.0]
 */
public class UMengPushUtil {
    public static final String UPDATE_STATUS_ACTION = "com.wilshion.oa.message.action.UPDATE_STATUS";
    private static UMengPushUtil mInstance;
    private final Handler mHandler;
    private final PushAgent mPushAgent;

    public static UMengPushUtil getInstance() {
        if (mInstance == null) {
            synchronized (UMengPushUtil.class) {
                if (mInstance == null) {
                    mInstance = new UMengPushUtil();
                }
            }
        }
        return mInstance;
    }

    public UMengPushUtil() {
        mPushAgent = PushAgent.getInstance(Utils.getContext());
        mHandler = new Handler(Utils.getContext().getMainLooper());

        //sdk开启通知声音
        mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);
        // sdk关闭通知声音
        // mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_DISABLE);
        // 通知声音由服务端控制
        // mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SERVER);

        // mPushAgent.setNotificationPlayLights(MsgConstant.NOTIFICATION_PLAY_SDK_DISABLE);
        // mPushAgent.setNotificationPlayVibrate(MsgConstant.NOTIFICATION_PLAY_SDK_DISABLE);

        UmengMessageHandler messageHandler = new OaMessageHandler();
        mPushAgent.setMessageHandler(messageHandler);

        UmengNotificationClickHandler notificationClickHandler = new NotificationClickHandler();
        //使用自定义的NotificationHandler
        mPushAgent.setNotificationClickHandler(notificationClickHandler);


        //使用完全自定义处理
        //mPushAgent.setPushIntentServiceClass(UmengNotificationService.class);

        //小米通道
        //MiPushRegistar.register(this, XIAOMI_ID, XIAOMI_KEY);
        //华为通道
        //HuaWeiRegister.register(this);
        //魅族通道
        //MeizuRegister.register(this, MEIZU_APPID, MEIZU_APPKEY);
    }

    /**
     * 初始化 必须调用
     */
    public void regist() {
        //注册推送服务 每次调用register都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                LogUtils.d("device token: " + deviceToken);
                Utils.getContext().sendBroadcast(new Intent(UPDATE_STATUS_ACTION));
            }

            @Override
            public void onFailure(String s, String s1) {
                LogUtils.d("register failed: " + s + " " + s1);
                Utils.getContext().sendBroadcast(new Intent(UPDATE_STATUS_ACTION));
            }
        });
    }

    /**
     * 若调用关闭推送后，想要再次开启推送，则需要调用以下代码（请在Activity内调用）：
     */
    public void resumePush() {
        mPushAgent.enable(new IUmengCallback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onFailure(String s, String s1) {

            }
        });
    }

    /**
     * 关闭推送
     */
    public void stopPush() {
        mPushAgent.disable(new IUmengCallback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onFailure(String s, String s1) {

            }
        });
    }

    /**
     * 通知免打扰模式
     * 为免过度打扰用户，SDK默认在“23:00”到“7:00”之间收到通知消息时不响铃，不振动，不闪灯。如果需要改变默认的静音时间，可以使用
     * 可以通过置 mPushAgent.setNoDisturbMode(0, 0, 0, 0);， 来关闭免打扰模式
     * mPushAgent.setNoDisturbMode(23, 0, 7, 0)
     *
     * @param startHour
     * @param startMinute
     * @param endHour
     * @param endMinute
     */
    public void setNoDisturbMode(int startHour, int startMinute, int endHour, int endMinute) {
        mPushAgent.setNoDisturbMode(startHour, startMinute, endHour, endMinute);
    }


    /**
     * 通知栏按数量显示
     * 通知栏可以设置最多显示通知的条数，当有新通知到达时，会把旧的通知隐藏
     * 例如设置通知栏最多显示两条通知（当通知栏已经有两条通知，此时若第三条通知到达，则会把第一条通知隐藏）
     *
     * @param number
     */
    public void setDisplayNotificationNumber(int number) {
        mPushAgent.setDisplayNotificationNumber(number);
    }
}
