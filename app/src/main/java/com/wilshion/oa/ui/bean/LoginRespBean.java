package com.wilshion.oa.ui.bean;

/**
 * Created by Wilshion on 2018/7/25 16:56.
 * [description : ]
 * [version : 1.0]
 */
public class LoginRespBean {

    /**
     * seqId : 1
     * userId : admin
     * userName : admin
     */

    private int seqId;
    private String userId;
    private String userName;

    public int getSeqId() {
        return seqId;
    }

    public void setSeqId(int seqId) {
        this.seqId = seqId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
