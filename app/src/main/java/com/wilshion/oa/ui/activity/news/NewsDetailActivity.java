package com.wilshion.oa.ui.activity.news;

import android.os.Bundle;
import android.widget.TextView;

import com.wilshion.common.utils.EmptyUtils;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseTitleBarActivity;
import com.wilshion.oa.ui.bean.NewsBean;
import com.wilshion.oa.ui.constant.Constant;

/**
 * Created by Wilshion on 2018/7/27 22:24.
 * [description : ]
 * [version : 1.0]
 */
public class NewsDetailActivity extends BaseTitleBarActivity {
    private TextView tv_notify_title;
    private TextView tv_sub_title;
    private TextView tv_content;
    private NewsBean mNewsBean;
    
    @Override
    protected void setTitleBar() {
        setTitle("新闻详情");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notification_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mNewsBean = getIntent().getParcelableExtra(Constant.INTENT_PARAM_DATA);
        if (mNewsBean == null){
            showToast("暂无数据");
            finish();
            return;
        }

        tv_notify_title = findViewById(R.id.tv_notify_title);
        tv_sub_title = findViewById(R.id.tv_sub_title);
        tv_content = findViewById(R.id.tv_content);

        String title = mNewsBean.getSUBJECT();

        String beginTime = mNewsBean.getNEWS_TIME();
        String time = "";
        if (!EmptyUtils.isEmpty(beginTime)) {
            time = beginTime.substring(0, 10);
        }
        String subTitle = mNewsBean.getUSER_NAME() + " " + time;

        tv_notify_title.setText(title);
        tv_sub_title.setText(subTitle);
        tv_content.setText(mNewsBean.getCONTENT());
    }
}
