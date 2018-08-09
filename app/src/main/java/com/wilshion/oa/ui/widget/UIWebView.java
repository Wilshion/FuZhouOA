package com.wilshion.oa.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.wilshion.common.utils.EmptyUtils;
import com.wilshion.common.utils.NetworkUtils;

/**
 * Created by Wilshion on 2018/7/31 19:46.
 * [description : ]
 * [version : 1.0]
 */
public class UIWebView extends WebView {
    public OnWebViewLoadStatusListener mOnWebViewLoadStatusListener;
    public UIWebView(Context context) {
        this(context, null);
    }

    public UIWebView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet,0);
    }

    public UIWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
                if (mOnWebViewLoadStatusListener!=null)
                    mOnWebViewLoadStatusListener.onLoadProgress(i);
            }
        });
        
        setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                return super.shouldOverrideUrlLoading(webView, s);
            }

            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
                if (mOnWebViewLoadStatusListener!=null)
                    mOnWebViewLoadStatusListener.onLoadStart();
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                if (mOnWebViewLoadStatusListener!=null)
                    mOnWebViewLoadStatusListener.onLoadEnd();
            }
        });

        WebSettings webSettings = getSettings();
        webSettings.setJavaScriptEnabled(true);

        if (NetworkUtils.isConnected()) {
            webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ONLY);
        }

        webSettings.setAppCacheEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);


        webSettings.setBuiltInZoomControls(true);
        // 设置可以支持缩放
        webSettings.setSupportZoom(true);
        //扩大比例的缩放
        webSettings.setUseWideViewPort(true);
        //自适应屏幕
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
    }

    /**
     * 加载html
     *
     * @param html
     */
    public void loadHtml(String html) {
        if (EmptyUtils.isEmpty(html))
            return;
        html = "<!DOCTYPE html><html><meta name='viewport' content='width=device-width, initial-scale=1' /><head><style>img{width:80%; height:auto;}</style></head><body>"
                + html + "</body></html>";
        loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
    }

    public void setOnWebViewLoadStatusListener(OnWebViewLoadStatusListener onWebViewLoadStatusListener) {
        mOnWebViewLoadStatusListener = onWebViewLoadStatusListener;
    }

    public interface OnWebViewLoadStatusListener {
        void onLoadStart();
        void onLoadProgress(int progress);
        void onLoadEnd();
    }
}
