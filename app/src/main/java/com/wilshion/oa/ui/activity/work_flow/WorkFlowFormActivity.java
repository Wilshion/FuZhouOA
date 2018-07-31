package com.wilshion.oa.ui.activity.work_flow;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wilshion.common.network.HttpCallBack;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseTitleBarActivity;
import com.wilshion.oa.ui.bean.ResponseBean;
import com.wilshion.oa.ui.bean.WorkFlowBean;
import com.wilshion.oa.ui.bean.WorkFlowFormBean;
import com.wilshion.oa.ui.utils.HttpUtil;
import com.wilshion.oa.ui.widget.UIWebView;

import java.util.HashMap;

/**
 * Created by Wilshion on 2018/7/26 20:50.
 * [description : 表单]
 * [version : 1.0]
 */
public class WorkFlowFormActivity extends BaseTitleBarActivity {
    private TextView tv_name;
    private TextView tv_flow_num;
    private TextView tv_time;
    private WorkFlowBean mWorkFlowBean;
    private ProgressBar pb_load;
    private UIWebView wv_content;

    @Override
    protected void setTitleBar() {
        setTitle("表单");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_work_flow_form;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        mWorkFlowBean = getIntent().getParcelableExtra("data");
        if (mWorkFlowBean == null) {
            showToast("数据获取失败");
            finish();
            return;
        }

        tv_name = findViewById(R.id.tv_name);
        tv_flow_num = findViewById(R.id.tv_flow_num);
        tv_time = findViewById(R.id.tv_time);

        pb_load = findViewById(R.id.pb_load);
        wv_content = findViewById(R.id.wv_content);

        requestData();
    }

    @Override
    protected void requestData() {
        super.requestData();
        showWating("正在加载中...");
        HashMap params = new HashMap();
        params.put("flowId", mWorkFlowBean.getFLOW_ID());
        params.put("runId", mWorkFlowBean.getRUN_ID());
        params.put("prcsId", mWorkFlowBean.getPRCS_ID());
        params.put("flowPrcs", mWorkFlowBean.getFLOW_PRCS());
        params.put("opFlag", mWorkFlowBean.getOP_FLAG());
        params.put("feedback", mWorkFlowBean.getFEEDBACK());

        HttpUtil.requestPost(this, "workflowGetForm", params, new HttpCallBack<ResponseBean<WorkFlowFormBean>>() {
            @Override
            public void onSuccess(int statusCode, String rawJsonResponse, ResponseBean<WorkFlowFormBean> response) {
                if (response.isSuccess()) {
                    closeDialog();
                    showData(response.getDetail());
                } else {
                    showError(response.getResultNote());
                }
            }

            @Override
            public void onFailure(int statusCode, String rawJsonResponse, ResponseBean<WorkFlowFormBean> response) {
                showError(rawJsonResponse);
            }
        });
    }

    private void showData(WorkFlowFormBean detail) {
        String runName = detail.getRunName();
        tv_name.setText(runName);
        tv_flow_num.setText(detail.getRunId() + "");
        tv_time.setText(detail.getBeginTime());

        String form = detail.getForm();
        wv_content.loadHtml(form);
    }
}
