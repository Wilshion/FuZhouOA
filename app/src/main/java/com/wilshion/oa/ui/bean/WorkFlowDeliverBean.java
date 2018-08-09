package com.wilshion.oa.ui.bean;

import java.util.List;

/**
 * Created by Wilshion on 2018/8/1 19:35.
 * [description : ]
 * [version : 1.0]
 */
public class WorkFlowDeliverBean {


    /**
     * flowName : 教职工请假调休
     * flowType : 1
     * runName : 教职工请假调休(2018-07-31 09:42:35)
     * beginUserName : admin
     * parentRun : 0
     * flowProcesses : [{"PRCS_ID":"2","PRCS_NAME":"分管园长审核","PRCS_IN":"","PRCS_IN_SET":"","PRCS_USER":"","PRCS_DEPT":"","PRCS_PRIV":"325,326","assitHandler":[{"seqId":"1296","userName":"郑秀珍"},{"seqId":"1297","userName":"王芳华"}],"CONDITION_DESC":"","USER_LOCK":"1","TOP_DEFAULT":"0","CHILD_FLOW":"0","AUTO_BASE_USER":"0"}]
     * flowId : 663
     * runId : 200
     * prcsId : 1
     * flowPrcs : 1
     */

    private String flowName;
    private String flowType;
    private String runName;
    private String beginUserName;
    private String parentRun;
    private int flowId;
    private int runId;
    private int prcsId;
    private int flowPrcs;
    private List<FlowProcessesBean> flowProcesses;

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public String getRunName() {
        return runName;
    }

    public void setRunName(String runName) {
        this.runName = runName;
    }

    public String getBeginUserName() {
        return beginUserName;
    }

    public void setBeginUserName(String beginUserName) {
        this.beginUserName = beginUserName;
    }

    public String getParentRun() {
        return parentRun;
    }

    public void setParentRun(String parentRun) {
        this.parentRun = parentRun;
    }

    public int getFlowId() {
        return flowId;
    }

    public void setFlowId(int flowId) {
        this.flowId = flowId;
    }

    public int getRunId() {
        return runId;
    }

    public void setRunId(int runId) {
        this.runId = runId;
    }

    public int getPrcsId() {
        return prcsId;
    }

    public void setPrcsId(int prcsId) {
        this.prcsId = prcsId;
    }

    public int getFlowPrcs() {
        return flowPrcs;
    }

    public void setFlowPrcs(int flowPrcs) {
        this.flowPrcs = flowPrcs;
    }

    public List<FlowProcessesBean> getFlowProcesses() {
        return flowProcesses;
    }

    public void setFlowProcesses(List<FlowProcessesBean> flowProcesses) {
        this.flowProcesses = flowProcesses;
    }

    public static class FlowProcessesBean {
        /**
         * PRCS_ID : 2
         * PRCS_NAME : 分管园长审核
         * PRCS_IN : 
         * PRCS_IN_SET : 
         * PRCS_USER : 
         * PRCS_DEPT : 
         * PRCS_PRIV : 325,326
         * assitHandler : [{"seqId":"1296","userName":"郑秀珍"},{"seqId":"1297","userName":"王芳华"}]
         * CONDITION_DESC : 
         * USER_LOCK : 1
         * TOP_DEFAULT : 0
         * CHILD_FLOW : 0
         * AUTO_BASE_USER : 0
         */

        private String PRCS_ID;
        private String PRCS_NAME;
        private String PRCS_IN;
        private String PRCS_IN_SET;
        private String PRCS_USER;
        private String PRCS_DEPT;
        private String PRCS_PRIV;
        private String CONDITION_DESC;
        private String USER_LOCK;
        private String TOP_DEFAULT;
        private String CHILD_FLOW;
        private String AUTO_BASE_USER;
        private List<DeliverAssistHandlerBean> assitHandler;

        public String getPRCS_ID() {
            return PRCS_ID;
        }

        public void setPRCS_ID(String PRCS_ID) {
            this.PRCS_ID = PRCS_ID;
        }

        public String getPRCS_NAME() {
            return PRCS_NAME;
        }

        public void setPRCS_NAME(String PRCS_NAME) {
            this.PRCS_NAME = PRCS_NAME;
        }

        public String getPRCS_IN() {
            return PRCS_IN;
        }

        public void setPRCS_IN(String PRCS_IN) {
            this.PRCS_IN = PRCS_IN;
        }

        public String getPRCS_IN_SET() {
            return PRCS_IN_SET;
        }

        public void setPRCS_IN_SET(String PRCS_IN_SET) {
            this.PRCS_IN_SET = PRCS_IN_SET;
        }

        public String getPRCS_USER() {
            return PRCS_USER;
        }

        public void setPRCS_USER(String PRCS_USER) {
            this.PRCS_USER = PRCS_USER;
        }

        public String getPRCS_DEPT() {
            return PRCS_DEPT;
        }

        public void setPRCS_DEPT(String PRCS_DEPT) {
            this.PRCS_DEPT = PRCS_DEPT;
        }

        public String getPRCS_PRIV() {
            return PRCS_PRIV;
        }

        public void setPRCS_PRIV(String PRCS_PRIV) {
            this.PRCS_PRIV = PRCS_PRIV;
        }

        public String getCONDITION_DESC() {
            return CONDITION_DESC;
        }

        public void setCONDITION_DESC(String CONDITION_DESC) {
            this.CONDITION_DESC = CONDITION_DESC;
        }

        public String getUSER_LOCK() {
            return USER_LOCK;
        }

        public void setUSER_LOCK(String USER_LOCK) {
            this.USER_LOCK = USER_LOCK;
        }

        public String getTOP_DEFAULT() {
            return TOP_DEFAULT;
        }

        public void setTOP_DEFAULT(String TOP_DEFAULT) {
            this.TOP_DEFAULT = TOP_DEFAULT;
        }

        public String getCHILD_FLOW() {
            return CHILD_FLOW;
        }

        public void setCHILD_FLOW(String CHILD_FLOW) {
            this.CHILD_FLOW = CHILD_FLOW;
        }

        public String getAUTO_BASE_USER() {
            return AUTO_BASE_USER;
        }

        public void setAUTO_BASE_USER(String AUTO_BASE_USER) {
            this.AUTO_BASE_USER = AUTO_BASE_USER;
        }

        public List<DeliverAssistHandlerBean> getAssitHandler() {
            return assitHandler;
        }

        public void setAssitHandler(List<DeliverAssistHandlerBean> assitHandler) {
            this.assitHandler = assitHandler;
        }

    }
}
