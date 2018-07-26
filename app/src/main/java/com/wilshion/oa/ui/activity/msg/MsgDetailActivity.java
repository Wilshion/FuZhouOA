package com.wilshion.oa.ui.activity.msg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseTitleBarActivity;
import com.wilshion.oa.ui.bean.MsgBean;

/**
 * Created by Wilshion on 2018/7/26 09:44.
 * [description : 消息详情]
 * [version : 1.0]
 */
public class MsgDetailActivity extends BaseTitleBarActivity {
    private TextView tv_author_time;
    private TextView tv_content;
    private MsgBean mMsgBean;
    @Override
    protected void setTitleBar() {
        setTitle("短信");
        setRightText("回复");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_msg_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mMsgBean = getIntent().getParcelableExtra("data");
        if (null == mMsgBean){
            showToast("传值出错");
            return;
        }
        tv_author_time = findViewById(R.id.tv_author_time);
        tv_content = findViewById(R.id.tv_content);

        String author = mMsgBean.getUSER_NAME();
        String time = mMsgBean.getSEND_TIME();
        tv_author_time.setText(author +"  "+ time);
        tv_content.setText(mMsgBean.getCONTENT());
    }

    @Override
    protected void onRightClick() {
        Intent intent = new Intent(this, MsgSendActivity.class);
        intent.putExtra("data", mMsgBean);
        startActivityForResult(intent,1);
    }
}
