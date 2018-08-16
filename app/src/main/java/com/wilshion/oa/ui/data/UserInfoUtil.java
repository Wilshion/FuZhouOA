package com.wilshion.oa.ui.data;

import com.wilshion.common.utils.SPUtils;
import com.wilshion.oa.ui.bean.LoginRespBean;

/**
 * Created by Wilshion on 2018/7/25 20:27.
 * [description : 存储登陆信息]
 * [version : 1.0]
 */
public class UserInfoUtil {
    private static final String SP_NAME = "SP_CUR_USER";
    private static final String CUR_USER_ID = "user_id";
    private static final String CUR_USER_NAME = "user_name";
    private static final String CUR_USER_PWD = "user_pwd";
    private static final String CUR_USER_SEQ_ID = "seq_id";

    public static void saveLoginResult(LoginRespBean loginRespBean) {
        SPUtils.getInstance().put(CUR_USER_ID, loginRespBean.getUserId());
        SPUtils.getInstance().put(CUR_USER_NAME, loginRespBean.getUserName());
        SPUtils.getInstance().put(CUR_USER_SEQ_ID, loginRespBean.getSeqId());
    }

    public static boolean hasLogin() {
        return getCurUserId() > 0 ;
    }

    public static void saveUserPwd(String pwd) {
        SPUtils.getInstance().put(CUR_USER_PWD, pwd);
    }

    public static int getCurUserId() {
        return SPUtils.getInstance().getInt(CUR_USER_SEQ_ID,0);
    }

    public static String getCurUserName() {
        return SPUtils.getInstance().getString(CUR_USER_NAME);
    }

    public static String getCurUserPwd() {
        return SPUtils.getInstance().getString(CUR_USER_PWD);
    }

    public static void clear() {
        SPUtils.getInstance().put(CUR_USER_ID, 0);
        SPUtils.getInstance().put(CUR_USER_NAME, "");
        SPUtils.getInstance().put(CUR_USER_SEQ_ID, 0);
    }
}
