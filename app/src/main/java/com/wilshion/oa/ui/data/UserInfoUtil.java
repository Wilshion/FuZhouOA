package com.wilshion.oa.ui.data;

import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.wilshion.common.utils.LogUtils;
import com.wilshion.common.utils.SPUtils;
import com.wilshion.common.utils.Utils;
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
    private static final String CUR_USER_UMENG_ALIAS = "umeng_alias";

    public static void saveLoginResult(LoginRespBean loginRespBean) {
        SPUtils.getInstance().put(CUR_USER_ID, loginRespBean.getUserId());
        SPUtils.getInstance().put(CUR_USER_NAME, loginRespBean.getUserName());
        SPUtils.getInstance().put(CUR_USER_SEQ_ID, loginRespBean.getSeqId());
        setUMengAlias();
    }

    public static boolean hasLogin() {
        return getCurUserId() > 0;
    }

    public static void saveUserPwd(String pwd) {
        SPUtils.getInstance().put(CUR_USER_PWD, pwd);
    }

    public static int getCurUserId() {
        return SPUtils.getInstance().getInt(CUR_USER_SEQ_ID, 0);
    }

    public static String getCurUserName() {
        return SPUtils.getInstance().getString(CUR_USER_NAME);
    }

    public static String getCurUserPwd() {
        return SPUtils.getInstance().getString(CUR_USER_PWD);
    }

    public static void clear() {
        saveUserAliasResult(false);
//        deleteUMengAlias();
        SPUtils.getInstance().put(CUR_USER_ID, 0);
        SPUtils.getInstance().put(CUR_USER_NAME, "");
        SPUtils.getInstance().put(CUR_USER_SEQ_ID, 0);
    }

    public static void saveUserAliasResult(boolean result) {
        SPUtils.getInstance().put(CUR_USER_UMENG_ALIAS, result);
    }

    public static boolean getUserAliasResult() {
        return SPUtils.getInstance().getBoolean(CUR_USER_UMENG_ALIAS);
    }

    private static void setUMengAlias() {
        if (UserInfoUtil.hasLogin()){
            if (!UserInfoUtil.getUserAliasResult()){
                String alias = UserInfoUtil.getCurUserId() + "";
                PushAgent.getInstance(Utils.getContext()).setAlias(alias, "user_id", new UTrack.ICallBack() {
                    @Override
                    public void onMessage(boolean b, String s) {
                        LogUtils.e(b + "  " + s);
                        saveUserAliasResult(b);
                    }
                });
            }
        }
    }

    private static void deleteUMengAlias() {
        if (UserInfoUtil.getUserAliasResult()){
            String alias = UserInfoUtil.getCurUserId() + "";
            PushAgent.getInstance(Utils.getContext()).deleteAlias(alias, "user_id", new UTrack.ICallBack() {
                @Override
                public void onMessage(boolean b, String s) {
                    LogUtils.e(b + "  " + s);
                }
            });
        }
    }
}
