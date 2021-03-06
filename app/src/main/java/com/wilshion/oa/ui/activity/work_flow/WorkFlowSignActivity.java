package com.wilshion.oa.ui.activity.work_flow;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.wilshion.common.network.HttpCallBack;
import com.wilshion.common.utils.EmptyUtils;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseTitleBarActivity;
import com.wilshion.oa.ui.bean.ResponseBean;
import com.wilshion.oa.ui.bean.WorkFlowBean;
import com.wilshion.oa.ui.constant.Constant;
import com.wilshion.oa.ui.utils.HttpUtil;

import java.util.HashMap;

/**
 * Created by Wilshion on 2018/7/27 17:41.
 * [description : 会签]
 * [version : 1.0]
 */
public class WorkFlowSignActivity extends BaseTitleBarActivity implements View.OnClickListener {
    private EditText et_content;
    private WorkFlowBean mWorkFlowBean;

    @Override
    protected void setTitleBar() {
        setTitle("会签");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_work_flow_sign;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mWorkFlowBean = getIntent().getParcelableExtra(Constant.INTENT_PARAM_DATA);
        if (mWorkFlowBean == null){
            showToast("数据获取失败");
            finish();
            return;
        }
        et_content = findViewById(R.id.et_content);
        findViewById(R.id.tv_submit).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (checkParams())
            requestSend();
    }

    private boolean checkParams() {
        String content = et_content.getText().toString();
        if (EmptyUtils.isEmpty(content)) {
            showToast("请输入会签意见");
            return false;
        }
        return true;
    }

    private void requestSend() {
        showWating("正在操作中...");
        String content = et_content.getText().toString();
        HashMap<String, String> params = new HashMap<>();
        params.put("flowId", mWorkFlowBean.getFLOW_ID() + "");
        params.put("runId", mWorkFlowBean.getRUN_ID() + "");
        params.put("prcsId", mWorkFlowBean.getPRCS_ID() + "");
        params.put("flowPrcs", mWorkFlowBean.getFLOW_PRCS() + "");
        params.put("content", content);
        HttpUtil.requestPost(this, "sign", params, new HttpCallBack<ResponseBean>() {
            @Override
            public void onSuccess(int statusCode, String rawJsonResponse, ResponseBean response) {
                if (response.isSuccess()) {
                    showSucceed(response.getResultNote());
                    setResult(RESULT_OK);
                    finishWithDelay(1000);
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
}
