package com.wilshion.common.network;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;
import com.wilshion.common.utils.LogUtils;
import com.wilshion.common.utils.NetworkUtils;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.Map;

import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by Wilshion on 16/8/22.
 * [description :网络请求帮助类]
 * [version : 1.0]
 */
public class ZHHttpHelper {
    public static final String TAG = ZHHttpHelper.class.getSimpleName();
    /**
     * 默认超时时间
     */
    public static final int SOCKET_TIMEOUT = 15 * 1000;
    public static final int NO_NETWORK_STATUS_CODE = -1000000;

    private static ZHHttpHelper instance;

    private static AsyncHttpClient client = new AsyncHttpClient();

    private static SyncHttpClient mSyncHttpClient;

    private static Object block = new Object();

    public static SyncHttpClient getSyncHttpClient() {
        if (mSyncHttpClient == null) {
            synchronized (block) {
                if (mSyncHttpClient == null) {
                    mSyncHttpClient = new SyncHttpClient();
                }
            }
        }
        return mSyncHttpClient;
    }

    static {
        client.setConnectTimeout(SOCKET_TIMEOUT);  //连接时间
        client.setResponseTimeout(SOCKET_TIMEOUT); //响应时间
        client.setTimeout(SOCKET_TIMEOUT); // 设置连接超时，如果不设置，默认为10s
    }

    private PersistentCookieStore cookieStore;

    public static ZHHttpHelper getInstance() {
        if (instance == null)
            instance = new ZHHttpHelper();
        return instance;
    }

    public AsyncHttpClient getAsyncHttpClient() {
        return client;
    }


    /**
     * get方法带参数
     */
    public RequestHandle get(String url, Map<String, Object> params,
                             ZHHttpCallBack httpCallBack) {
        return get(url, params, true, httpCallBack);
    }

    public RequestHandle get(String url, Map<String, Object> params, boolean async,
                             ZHHttpCallBack httpCallBack) {
        if (!NetworkUtils.isConnected()) {
            if (httpCallBack != null) httpCallBack.onFailure(NO_NETWORK_STATUS_CODE, "没有网络", null);
            return null;
        }

        if (params != null && params.size() > 0) {
            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append(url);
            urlBuilder.append("?");
            String paramStr;
            for (Map.Entry<String, Object> param : params.entrySet()) {
                paramStr = MessageFormat.format("{0}={1}", param.getKey(), param.getValue());
                urlBuilder.append(paramStr);
                urlBuilder.append("&");
            }
            urlBuilder.delete(urlBuilder.length() - 1, urlBuilder.length());
            url = urlBuilder.toString();
        }
        LogUtils.e(TAG, url);
        RequestHandle requestHandle;
        if (async) {
            requestHandle = client.get(url, httpCallBack);
        } else {
            requestHandle = getSyncHttpClient().get(url, httpCallBack);
        }

        return requestHandle;
    }

    /**
     * 用于同步请求
     *
     * @param url
     * @param params
     * @param httpCallBack
     * @return
     */
    public RequestHandle syncGet(String url, Map<String, Object> params,
                                 ZHHttpCallBack httpCallBack) {
        if (!NetworkUtils.isConnected()) {
            if (httpCallBack != null) httpCallBack.onFailure(NO_NETWORK_STATUS_CODE, "没有网络", null);
            return null;
        }

        if (params != null && params.size() > 0) {
            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append(url);
            urlBuilder.append("?");
            String paramStr;
            for (Map.Entry<String, Object> param : params.entrySet()) {
                paramStr = MessageFormat.format("{0}={1}", param.getKey(), param.getValue());
                urlBuilder.append(paramStr);
                urlBuilder.append("&");
            }
            urlBuilder.delete(urlBuilder.length() - 1, urlBuilder.length());
            url = urlBuilder.toString();
        }
        LogUtils.e(TAG, url);
        RequestHandle requestHandle = getSyncHttpClient().get(url, httpCallBack);
        return requestHandle;
    }

    /**
     * post请求，带参数
     */
    public RequestHandle post(Context context,String url, JSONObject params,
                              ZHHttpCallBack httpCallBack) {
//        LogUtils.e(TAG, getAsyncHttpClient().getUrlWithQueryString(true, url, params));
        StringEntity se = null;
        try {
            se = new StringEntity(params.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestHandle requestHandle = client.post(context,url,se,RequestParams.APPLICATION_JSON,  httpCallBack);
        return requestHandle;
    }

    public RequestHandle getRaw(String url, Map<String, Object> params,
                                AsyncHttpResponseHandler httpCallBack) {

        if (!NetworkUtils.isConnected()) {
            return null;
        }

        if (params != null && params.size() > 0) {
            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append(url);
            urlBuilder.append("?");
            String paramStr;
            for (Map.Entry<String, Object> param : params.entrySet()) {
                String key = param.getKey();
                String value = param.getValue().toString();
                if (param.getValue() == null)
                    value = "";
                paramStr = MessageFormat.format("{0}={1}", key, value);
                urlBuilder.append(paramStr);
                urlBuilder.append("&");
            }
            urlBuilder.delete(urlBuilder.length() - 1, urlBuilder.length());
            url = urlBuilder.toString();
        }
        LogUtils.e(TAG, url);
        RequestHandle requestHandle = client.get(url, httpCallBack);
        return requestHandle;
    }

}
