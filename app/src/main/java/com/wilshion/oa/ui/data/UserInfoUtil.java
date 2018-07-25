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
    private static final String CUR_USER_SEQ_ID =  "seq_id";

    public static void saveLoginResult(LoginRespBean loginRespBean) {
        SPUtils.getInstance().put(CUR_USER_ID,loginRespBean.getUserId());
        SPUtils.getInstance().put(CUR_USER_NAME,loginRespBean.getUserName());
        SPUtils.getInstance().put(CUR_USER_SEQ_ID,loginRespBean.getSeqId());
    }
}
