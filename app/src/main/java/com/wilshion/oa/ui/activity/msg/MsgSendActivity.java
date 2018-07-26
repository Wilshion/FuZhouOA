package com.wilshion.oa.ui.activity.msg;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseTitleBarActivity;

/**
 * Created by Wilshion on 2018/7/26 13:55.
 * [description : 短信发送]
 * [version : 1.0]
 */
public class MsgSendActivity extends BaseTitleBarActivity implements View.OnClickListener {
    private EditText et_content;
    private TextView tv_send;
    
    @Override
    protected void setTitleBar() {
        setTitle("写短信");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_msg_answer;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        et_content = findViewById(R.id.et_content);
        tv_send = findViewById(R.id.tv_send);
        
        tv_send.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        
    }
}
