package com.wilshion.oa.ui.activity.msg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseTitleBarActivity;
import com.wilshion.oa.ui.bean.MsgBean;
import com.wilshion.oa.ui.bean.PersonListRespBean;
import com.wilshion.oa.ui.constant.Constant;

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
        mMsgBean = getIntent().getParcelableExtra(Constant.INTENT_PARAM_DATA);
        if (null == mMsgBean){
            showToast("传值出错");
            finish();
            return;
        }
        tv_author_time = findViewById(R.id.tv_sub_title);
        tv_content = findViewById(R.id.tv_content);

        String author = mMsgBean.getUSER_NAME();
        String time = mMsgBean.getSEND_TIME();
        tv_author_time.setText(author +"  "+ time);
        tv_content.setText(mMsgBean.getCONTENT());
    }

    @Override
    protected void onRightClick() {
        PersonListRespBean.PersonBean personBean = new PersonListRespBean.PersonBean();
        personBean.setSEQ_ID(mMsgBean.getFROM_ID());
        personBean.setUSER_NAME(mMsgBean.getUSER_NAME());
        
        Intent intent = new Intent(this, MsgSendActivity.class);
        intent.putExtra(Constant.INTENT_PARAM_DATA, personBean);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            setResult(RESULT_OK);
            finish();
        }
    }
}
