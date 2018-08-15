package com.wilshion.oa.ui.bean;

/**
 * Created by Wilshion on 2018/8/14 21:52.
 * [description : ]
 * [version : 1.0]
 */
public class MyDocumentHandleBean {

    /**
     * flowId : 1
     * runId : 1
     * prcsId : 1
     * flowPrcs : 1
     * userId : 1
     * url : http://127.0.0.1:8080/yh/yh/pda/doc/act/YHPdaDocAct/getHandlerData.act?P=1&flowId=1&runId=1&prcsId=1&flowPrcs=1
     */

    private String flowId;
    private String runId;
    private String prcsId;
    private String flowPrcs;
    private String userId;
    private String url;

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getRunId() {
        return runId;
    }

    public void setRunId(String runId) {
        this.runId = runId;
    }

    public String getPrcsId() {
        return prcsId;
    }

    public void setPrcsId(String prcsId) {
        this.prcsId = prcsId;
    }

    public String getFlowPrcs() {
        return flowPrcs;
    }

    public void setFlowPrcs(String flowPrcs) {
        this.flowPrcs = flowPrcs;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
