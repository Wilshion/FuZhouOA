package com.wilshion.oa.ui.activity.email;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.wilshion.common.network.HttpCallBack;
import com.wilshion.common.utils.EmptyUtils;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseTitleBarActivity;
import com.wilshion.oa.ui.activity.common.PersonListActivity;
import com.wilshion.oa.ui.bean.PersonListRespBean;
import com.wilshion.oa.ui.bean.ResponseBean;
import com.wilshion.oa.ui.constant.Constant;
import com.wilshion.oa.ui.utils.HttpUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Wilshion on 2018/7/26 15:12.
 * [description : 发送邮件]
 * [version : 1.0]
 */
public class EmailSendActivity extends BaseTitleBarActivity implements View.OnClickListener {
    private TextView tv_inner_receiver;
    private TextView tv_out_receiver;
    private EditText et_subject;
    private EditText et_content;
    /**
     * 当前选择 收信人模式 1-内部 2-外部
     */
    private int mCurSelectMode;

    private List<PersonListRespBean.PersonBean> mInPersonList;
    private List<PersonListRespBean.PersonBean> mOutPersonList;

    @Override
    protected void setTitleBar() {
        setTitle("写邮件");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_email_send;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        tv_inner_receiver = findViewById(R.id.tv_inner_receiver);
        tv_out_receiver = findViewById(R.id.tv_out_receiver);
        et_subject = findViewById(R.id.et_subject);
        et_content = findViewById(R.id.et_content);

        tv_inner_receiver.setOnClickListener(this);
        tv_out_receiver.setOnClickListener(this);
        findViewById(R.id.tv_send).setOnClickListener(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            List<PersonListRespBean.PersonBean> personBeanList = data.getParcelableArrayListExtra(Constant.INTENT_RESULT);
            String names = getSelectedPersonsParams(1, personBeanList);
            if (mCurSelectMode == 1) {
                mInPersonList = personBeanList;
                tv_inner_receiver.setText(names);
            } else {
                mOutPersonList = personBeanList;
                tv_out_receiver.setText(names);
            }
        }
    }

    /**
     * 从收件人信息中获取指定信息
     *
     * @param paramType 1-姓名 2-id
     * @return
     */
    private String getSelectedPersonsParams(int paramType, List<PersonListRespBean.PersonBean> originPersonList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (PersonListRespBean.PersonBean personBean : originPersonList) {
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
            case R.id.tv_inner_receiver:
                mCurSelectMode = 1;
                PersonListActivity.showPersonList(this, false);
                break;
            case R.id.tv_out_receiver:
                mCurSelectMode = 2;
                PersonListActivity.showPersonList(this, false);
                break;
            case R.id.tv_send:
                if (checkParams()){
                    requestSend();
                } 
                break;
        }
    }

    /**
     * 发送邮件
     */
    private void requestSend() {
        showWating("正在发送中...");
        String subject = et_subject.getText().toString();
        String content = et_content.getText().toString();
        String inPersonNames = getSelectedPersonsParams(1,mInPersonList);
        String inPersonIds = getSelectedPersonsParams(2,mInPersonList);
        String outPersonNames = getSelectedPersonsParams(1,mOutPersonList);
        String outPersonIds = getSelectedPersonsParams(2,mOutPersonList);
        HashMap<String, String> params = new HashMap<>();
        params.put("toName1", inPersonNames);
        params.put("toId1", inPersonIds);
        params.put("toName2", outPersonNames);
        params.put("toId2", outPersonIds);
        params.put("subject", subject);
        params.put("content", content);
        params.put("attachmentId", "");
        params.put("attachmentName", "");
        HttpUtil.requestPost(this, "sendEmail", params, new HttpCallBack<ResponseBean>() {
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

    /**
     * 检查参数
     * @return  true 可以发送 false 缺少参数
     */
    private boolean checkParams() {
        if (EmptyUtils.isEmpty(mInPersonList)){
            showToast("请选择内部收件人");
            return false;
        }
        if (EmptyUtils.isEmpty(mOutPersonList)){
            showToast("请选择外部收件人");
            return false;
        }
        String subject = et_subject.getText().toString();
        if (EmptyUtils.isEmpty(subject)){
            showToast("请填写主题");
            return false;
        }
        String content = et_content.getText().toString();
        if (EmptyUtils.isEmpty(content)){
            showToast("请填写内容");
            return false;
        }
        return true;
    }
}
