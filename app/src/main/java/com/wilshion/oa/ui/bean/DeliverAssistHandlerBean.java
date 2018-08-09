package com.wilshion.oa.ui.bean;

/**
 * Created by Wilshion on 2018/8/9 21:30.
 * [description : 公文、工作流 转交的时候，人员bean]
 * [version : 1.0]
 */
public class DeliverAssistHandlerBean {
    /**
     * seqId : 1296
     * userName : 郑秀珍
     */

    private String seqId;
    private String userName;

    public String getSeqId() {
        return seqId;
    }

    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return userName;
    }
}
