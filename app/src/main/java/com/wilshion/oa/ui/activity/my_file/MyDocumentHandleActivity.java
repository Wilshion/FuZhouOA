package com.wilshion.oa.ui.activity.my_file;

import android.os.Bundle;
import android.webkit.JavascriptInterface;

import com.tencent.smtt.sdk.WebSettings;
import com.wilshion.common.base.BaseUIActivity;
import com.wilshion.common.network.HttpCallBack;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.bean.MyDocumentRespBean.MyDocumentBean;
import com.wilshion.oa.ui.bean.ResponseBean;
import com.wilshion.oa.ui.bean.WorkFlowHandleBean;
import com.wilshion.oa.ui.data.UserInfoUtil;
import com.wilshion.oa.ui.utils.HttpUtil;
import com.wilshion.oa.ui.widget.UIWebView;

import java.util.HashMap;

/**
 * Created by Wilshion on 2018/8/9 21:04.
 * [description : 主办]
 * [version : 1.0]
 */
public class MyDocumentHandleActivity extends BaseUIActivity implements UIWebView.OnWebViewLoadStatusListener {
    private MyDocumentBean mMyDocumentBean;
    private UIWebView wv_content;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        mMyDocumentBean = getIntent().getParcelableExtra("data");
        if (mMyDocumentBean == null) {
            showToast("数据获取失败");
            finish();
            return;
        }

        wv_content = findViewById(R.id.wv_content);
        wv_content.getSettings().setTextSize(WebSettings.TextSize.LARGER);
        wv_content.addJavascriptInterface(new JsHook(), "js_hook");
        wv_content.setOnWebViewLoadStatusListener(this);
//        requestLogin();
        showData();
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

    }

    private void requestTransferData() {
        showWating("正在加载中...");
        HashMap params = new HashMap();
        params.put("flowId", mMyDocumentBean.getFlowId());
        params.put("runId", mMyDocumentBean.getRunId());
        params.put("prcsId", mMyDocumentBean.getPrcsId());
        params.put("flowPrcs", mMyDocumentBean.getFlowPrcs());
        params.put("isManage", "true");

        HttpUtil.requestPost(this, "turnDoc", params, new HttpCallBack<ResponseBean<WorkFlowHandleBean>>() {
            @Override
            public void onSuccess(int statusCode, String rawJsonResponse, ResponseBean<WorkFlowHandleBean> response) {
                if (response.isSuccess()) {
                    closeDialog();
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
        int flowId = mMyDocumentBean.getFlowId();
        int runId = mMyDocumentBean.getRunId();
        int prcsId = mMyDocumentBean.getPrcsId();
        int flowPrcs = mMyDocumentBean.getFlowPrcs();
        int opFlag = mMyDocumentBean.getOpFlag();

        String url = String.format("http://mwoa.fujiants.com/yh/yh/pda/doc/act/YHPdaDocAct/getHandlerData.act?P=%d&flowId=%d&runId=%d&prcsId=%d&flowPrcs=%d",
                userId, flowId, runId, prcsId, flowPrcs);
        wv_content.loadUrl(url);
    }

    private void showData(WorkFlowHandleBean detail) {
        int flowId = detail.getFlowId();
        int runId = detail.getRunId();
        int prcsId = detail.getPrcsId();
        int flowPrcs = detail.getFlowPrcs();
        String opFlag = detail.getOpFlag();
        String feedback = detail.getFeedback();
        String url = String.format("http://mwoa.fujiants.com/yh/yh/pda/workflow/act/YHPdaWorkflowAct/getHandlerData.act?P=1&flowId=%d&runId=%d&prcsId=%d&flowPrcs=%d&opFlag=%s&feedback=%s",
                flowId, runId, prcsId, flowPrcs, opFlag, feedback);
        wv_content.loadHtml(url);
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
