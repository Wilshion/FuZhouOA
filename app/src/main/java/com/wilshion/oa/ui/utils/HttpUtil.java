package com.wilshion.oa.ui.utils;

import android.content.Context;

import com.wilshion.common.network.ZHHttpCallBack;
import com.wilshion.common.network.ZHHttpHelper;
import com.wilshion.oa.ui.constant.ConstantUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Wilshion on 2018/7/25 17:43.
 * [description : ]
 * [version : 1.0]
 */
public class HttpUtil {
    public static void requestPost(Context context,String cmd, Map paramsDetail, ZHHttpCallBack callBack) {
        JSONObject params = prepareRequestParams(cmd, paramsDetail);
        ZHHttpHelper.getInstance().post(context,ConstantUrl.URL, params,callBack);
    }

    private static JSONObject prepareRequestParams(String cmd, Map<String, Object> paramsDetail) {
        JSONObject jsonParams = new JSONObject();
        JSONObject jsonParamsDetail = new JSONObject();
        for (Map.Entry<String, Object> param : paramsDetail.entrySet()) {
            String key = param.getKey();
            String value = param.getValue().toString();
            try {
                jsonParamsDetail.put(key, value);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            jsonParams.put("cmd", cmd);
            jsonParams.put("params", jsonParamsDetail);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonParams;
    }


}
