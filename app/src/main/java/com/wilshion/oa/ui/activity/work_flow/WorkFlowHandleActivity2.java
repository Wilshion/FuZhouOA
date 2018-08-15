package com.wilshion.oa.ui.activity.work_flow;

import android.os.Bundle;
import android.webkit.JavascriptInterface;

import com.tencent.smtt.sdk.WebSettings;
import com.wilshion.common.base.BaseUIActivity;
import com.wilshion.common.network.HttpCallBack;
import com.wilshion.common.utils.LogUtils;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.bean.ResponseBean;
import com.wilshion.oa.ui.bean.WorkFlowBean;
import com.wilshion.oa.ui.bean.WorkFlowHandleBean;
import com.wilshion.oa.ui.constant.ConstantUrl;
import com.wilshion.oa.ui.data.UserInfoUtil;
import com.wilshion.oa.ui.utils.HttpUtil;
import com.wilshion.oa.ui.widget.UIWebView;

import java.util.HashMap;

/**
 * Created by Wilshion on 2018/7/26 20:50.
 * [description : 主办]
 * [version : 1.0]
 */
public class WorkFlowHandleActivity2 extends BaseUIActivity implements UIWebView.OnWebViewLoadStatusListener {
    private WorkFlowBean mWorkFlowBean;
    private UIWebView wv_content;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_webview;
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

        wv_content = findViewById(R.id.wv_content);
        wv_content.getSettings().setTextSize(WebSettings.TextSize.LARGER);
        wv_content.addJavascriptInterface(new JsHook(), "js_hook");
        wv_content.setOnWebViewLoadStatusListener(this);
        requestData();
    }

    @Override
    public void onBackPressed() {
        if (wv_content.canGoBack()) {
            wv_content.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void requestData() {
        super.requestData();
        requestTransferData();
    }

    private void requestTransferData() {
        showWating("正在加载中...");
        int userId = UserInfoUtil.getCurUserId();
        HashMap params = new HashMap();
        params.put("flowId", mWorkFlowBean.getFLOW_ID());
        params.put("runId", mWorkFlowBean.getRUN_ID());
        params.put("prcsId", mWorkFlowBean.getPRCS_ID());
        params.put("flowPrcs", mWorkFlowBean.getFLOW_PRCS());
        params.put("opFlag", mWorkFlowBean.getOP_FLAG());
        params.put("feedback", mWorkFlowBean.getFEEDBACK());
        params.put("userId", userId);
        
        HttpUtil.requestPost(this, "getHandlerData", params, new HttpCallBack<ResponseBean<WorkFlowHandleBean>>() {
            @Override
            public void onSuccess(int statusCode, String rawJsonResponse, ResponseBean<WorkFlowHandleBean> response) {
                if (response.isSuccess()) {
                    showData(response.getDetail());
                } else {
                    showError(response.getResultNote());
                }
            }

            @Override
            public void onFailure(int statusCode, String rawJsonResponse, ResponseBean<WorkFlowHandleBean> response) {
                showError(rawJsonResponse);
            }
        });
    }


    private void showData() {
        int userId = UserInfoUtil.getCurUserId();
        int flowId = mWorkFlowBean.getFLOW_ID();
        int runId = mWorkFlowBean.getRUN_ID();
        int prcsId = mWorkFlowBean.getPRCS_ID();
        int flowPrcs = mWorkFlowBean.getFLOW_PRCS();
        String opFlag = mWorkFlowBean.getOP_FLAG();
        String feedback = mWorkFlowBean.getFEEDBACK();
        String url = String.format("http://mwoa.fujiants.com/yh/yh/pda/workflow/act/YHPdaWorkflowAct/getHandlerData.act?P=%d&flowId=%d&runId=%d&prcsId=%d&flowPrcs=%d&opFlag=%s&feedback=%s",
                userId, flowId, runId, prcsId, flowPrcs, opFlag, feedback);
        wv_content.loadUrl(url);
    }

    private void showData(WorkFlowHandleBean detail) {
//        int flowId = detail.getFlowId();
//        int runId = detail.getRunId();
//        int prcsId = detail.getPrcsId();
//        int flowPrcs = detail.getFlowPrcs();
//        String opFlag = detail.getOpFlag();
//        String feedback = detail.getFeedback();
//        String url = String.format("http://mwoa.fujiants.com/yh/yh/pda/workflow/act/YHPdaWorkflowAct/getHandlerData.act?P=1&flowId=%d&runId=%d&prcsId=%d&flowPrcs=%d&opFlag=%s&feedback=%s",
//                flowId, runId, prcsId, flowPrcs, opFlag, feedback);
        String url = ConstantUrl.getUrlPrefix() + detail.getUrl();
        LogUtils.e(url);
        wv_content.loadUrl(url);
    }

    @Override
    public void onLoadStart() {
        showWating("正在加载中...");
    }

    @Override
    public void onLoadProgress(int progress) {

    }

    @Override
    public void onLoadEnd() {
        closeDialog();
    }

    final class JsHook {
        @JavascriptInterface
        public void onBackClick() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showLogD("点击了返回按钮");
                    onBackPressed();
                }
            });
        }

        @JavascriptInterface
        public void onSaveClick() {
            showLogD("点击了保存按钮");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showWating("正在保存中...");
                }
            });
        }

        @JavascriptInterface
        public void onSaveResult(final boolean result, final String msg) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showLogD("保存结果");
                    closeDialog();
                    showToast(msg);
                    if (result) {
                        setResult(RESULT_OK);
                        finish();
                    }
                }
            });
        }
    }


}
