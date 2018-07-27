package com.wilshion.oa.ui.activity.notification;

import android.os.Bundle;
import android.widget.TextView;

import com.wilshion.common.utils.EmptyUtils;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseTitleBarActivity;
import com.wilshion.oa.ui.bean.NotificationBean;
import com.wilshion.oa.ui.constant.Constant;

/**
 * Created by Wilshion on 2018/7/27 21:32.
 * [description : 通知公告详情]
 * [version : 1.0]
 */
public class NotificationDetailActivity extends BaseTitleBarActivity {

    private TextView tv_notify_title;
    private TextView tv_sub_title;
    private TextView tv_content;

    @Override
    protected void setTitleBar() {
        setTitle("通知公告");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notification_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        NotificationBean notificationBean = getIntent().getParcelableExtra(Constant.INTENT_PARAM_DATA);
        if (notificationBean == null){
            showToast("暂无数据");
            finish();
            return;
        }

        tv_notify_title = findViewById(R.id.tv_notify_title);
         tv_sub_title = findViewById(R.id.tv_sub_title);
         tv_content = findViewById(R.id.tv_content);

        String title = notificationBean.getSUBJECT();
        
        String beginTime = notificationBean.getBEGIN_DATE();
        String time = "";
        if (!EmptyUtils.isEmpty(beginTime)) {
            time = notificationBean.getBEGIN_DATE().substring(0, 10);
        }
        String subTitle = notificationBean.getUSER_NAME() + " " + time;

        tv_notify_title.setText(title);
        tv_sub_title.setText(subTitle);
        tv_content.setText(notificationBean.getCONTENT());
    }
}
