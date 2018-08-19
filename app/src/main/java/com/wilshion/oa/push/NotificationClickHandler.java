package com.wilshion.oa.push;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;
import com.wilshion.common.utils.EmptyUtils;
import com.wilshion.oa.ui.activity.email.EmailDetailActivity;
import com.wilshion.oa.ui.activity.main.MainActivity;
import com.wilshion.oa.ui.activity.msg.MsgDetailActivity;
import com.wilshion.oa.ui.bean.EmailBean;
import com.wilshion.oa.ui.bean.MsgBean;
import com.wilshion.oa.ui.constant.Constant;

import org.json.JSONObject;

/**
 * Created by Wilshion on 2018/8/18 20:44.
 * [description : 友盟推送 点击事件处理]
 * [version : 1.0]
 * <p>
 * <p>
 * 自定义行为的回调处理，参考文档：高级功能-通知的展示及提醒-自定义通知打开动作
 * UmengNotificationClickHandler是在BroadcastReceiver中被调用，故
 * 如果需启动Activity，需添加Intent.FLAG_ACTIVITY_NEW_TASK
 */
public class NotificationClickHandler extends UmengNotificationClickHandler {
    @Override
    public void launchApp(Context context, UMessage msg) {
        super.launchApp(context, msg);
    }

    @Override
    public void openUrl(Context context, UMessage msg) {
        super.openUrl(context, msg);
    }

    @Override
    public void openActivity(Context context, UMessage msg) {
        super.openActivity(context, msg);
    }

    @Override
    public void dealWithCustomAction(Context context, UMessage msg) {
        
        JSONObject jsonObject = msg.getRaw();
        PushMsgBean pushMsgBean = new Gson().fromJson(jsonObject.toString(), PushMsgBean.class);
        if (pushMsgBean != null) {
            PushMsgBean.BodyBean bodyBean = pushMsgBean.getBody();
            if (bodyBean!=null){
                String msgType = bodyBean.getMsg_type();
                if (!EmptyUtils.isEmpty(msg)) {
                    int msgTypeInt = Integer.parseInt(msgType);
                    switch (msgTypeInt) {
                        case 1://短信
                            MsgBean msgBean = new Gson().fromJson(msg.custom, MsgBean.class);
                            startActivity(context, MsgDetailActivity.class,msgBean);
                            break;
                        case 2://邮件
                            EmailBean emailBean = new Gson().fromJson(msg.custom, EmailBean.class);
                            startActivity(context, EmailDetailActivity.class,emailBean);
                            break;
                        default:
                            startActivity(context, MainActivity.class,null);
                            break;
                    }
                } 
            }
           
        }

    }



    private void startActivity(Context context,Class targetClazz,Parcelable data){
        Intent intent = new Intent(context,targetClazz);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(Constant.INTENT_PARAM_DATA,data);
        intent.setClass(context.getApplicationContext(), targetClazz);
        context.startActivity(intent);
    }
}
