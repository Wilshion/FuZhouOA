package com.wilshion.oa.ui.activity.msg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.wilshion.common.network.HttpCallBack;
import com.wilshion.common.utils.StringUtils;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseTitleBarActivity;
import com.wilshion.oa.ui.activity.common.PersonListActivity;
import com.wilshion.oa.ui.bean.PersonListRespBean;
import com.wilshion.oa.ui.bean.ResponseBean;
import com.wilshion.oa.ui.constant.Constant;
import com.wilshion.oa.ui.utils.HttpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Wilshion on 2018/7/26 13:55.
 * [description : 短信发送]
 * [version : 1.0]
 */
public class MsgSendActivity extends BaseTitleBarActivity implements View.OnClickListener {
    private EditText et_content;
    private TextView tv_receiver;
    private TextView tv_send;
    private List<PersonListRespBean.PersonBean> mTargetPersonList = new ArrayList<>();
    

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
        // 如果是回复别人的短信，则此对象不为空
        PersonListRespBean.PersonBean personBean = getIntent().getParcelableExtra(Constant.INTENT_PARAM_DATA);

        tv_receiver = findViewById(R.id.tv_receiver);
        et_content = findViewById(R.id.et_content);
        tv_send = findViewById(R.id.tv_send);
       
        if (personBean != null) {
            mTargetPersonList.add(personBean);
            tv_receiver.setText(personBean.getUSER_NAME());
        }
        tv_receiver.setOnClickListener(this);
        tv_send.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            mTargetPersonList = data.getParcelableArrayListExtra(Constant.INTENT_RESULT);
            String selectedPersonsName = getSelectedPersonsParams(1);
            tv_receiver.setText(selectedPersonsName);
        }
    }

    /**
     * 从收件人信息中获取指定信息
     *
     * @param paramType 1-姓名 2-id
     * @return
     */
    private String getSelectedPersonsParams(int paramType) {
        StringBuilder stringBuilder = new StringBuilder();
        for (PersonListRespBean.PersonBean personBean : mTargetPersonList) {
            String name = personBean.getUSER_NAME();
            int userId = personBean.getSEQ_ID();
            stringBuilder.append(paramType == 1 ? name : userId).append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        String selectedPersonsParamStr = stringBuilder.toString();
        return selectedPersonsParamStr;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.tv_receiver:
                PersonListActivity.showPersonList(this, false);
                break;
            case R.id.tv_send:
                if (checkParams()) {
                    requestSend();
                }
                break;
        }
    }

    private void requestSend() {
        showWating("正在发送中...");
        String content = et_content.getText().toString();
        String selectedPersonsIdStr = getSelectedPersonsParams(2);
        HashMap<String, String> params = new HashMap<>();
        params.put("toName", "");
        params.put("toId", selectedPersonsIdStr);
        params.put("content", content);
        HttpUtil.requestPost(this, "sendSms", params, new HttpCallBack<ResponseBean>() {
            @Override
            public void onSuccess(int statusCode, String rawJsonResponse, ResponseBean response) {
                if (response.isSuccess()) {
                    closeDialog();
                    showToast("发送成功");
                    setResult(RESULT_OK);
                    finish();
                } else {
                    showError(response.getResultNote());
                }
            }

            @Override
            public void onFailure(int statusCode, String rawJsonResponse, ResponseBean response) {
                showError(rawJsonResponse);
            }
        });
    }

    private boolean checkParams() {
        if (null == mTargetPersonList || mTargetPersonList.size() == 0) {
            showToast("请选择收信人");
            return false;
        }
        String content = et_content.getText().toString();
        if (StringUtils.isEmpty(content)) {
            showToast("请填写短信内容");
            return false;
        }
        return true;
    }
}
