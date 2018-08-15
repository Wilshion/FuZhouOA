package com.wilshion.oa.ui.constant;

/**
 * Created by Wilshion on 2018/7/25 17:09.
 * [description : ]
 * [version : 1.0]
 */
public class ConstantUrl {
    private static String URL_PREFIX = "http://mwoa.fujiants.com/";

    public static String URL = URL_PREFIX + "yh/api";

    public static String getUrlPrefix() {
        return URL_PREFIX;
    }

    public static void setUrlPrefix(String urlPrefix) {
        URL_PREFIX = urlPrefix;
        URL = URL_PREFIX + "yh/api";
    }
}
