package com.wilshion.oa.ui.bean;

import java.util.List;

/**
 * Created by Wilshion on 2018/8/9 21:13.
 * [description : ]
 * [version : 1.0]
 */
public class MyDocumentDeliverRespBean {
    private MyDocumentDeliverDataBean data;

    public MyDocumentDeliverDataBean getData() {
        return data;
    }

    public void setData(MyDocumentDeliverDataBean data) {
        this.data = data;
    }

    public static final class MyDocumentDeliverDataBean{

        /**
         * flowName : 发文
         * prcsName : 提交公文
         * remindFlag : 768
         * syncDeal : 0
         * flowRun : {"runName":"[]号","isAllowTurn":true,"userNameStr":"<font color=red title=正在办理>admin(办理中)<\/font>","notAllFinish":""}
         * nextPrcs : [{"seqId":697,"prcsId":"3","prcsUser":"","prcsDept":"","prcsPriv":"325,326","docSmsStyle":"#{办理人}:#{标题}请你审核。拟稿人：#{拟稿人}","childFlow":"0","parentRun":"0","prcsName":"分管园长审核","isAutoSelect":false,"prcsOpUser":"","prcsOpUserName":"","prcsUserName":"","userFilter":"0","userLock":false,"topFlag":"0","assitHandler":[{"seqId":"1296","userName":"郑秀珍"},{"seqId":"1297","userName":"王芳华"}]}]
         * smsRemind : 11,12,13,14,15,16,17,18,19,2,20,22,3,1,30,31,32,33,34,35,36,37,4,40,41,42,43,44,45,a0,5,56,57,58,6,61,62,64,7,8,9,99,
         * sms2Remind : 
         * sms2PrivNext : false
         * sms2PrivStart : false
         * sms2PrivAll : false
         */

        private String flowName;
        private String prcsName;
        private int remindFlag;
        private String syncDeal;
        private FlowRunBean flowRun;
        private String smsRemind;
        private String sms2Remind;
        private boolean sms2PrivNext;
        private boolean sms2PrivStart;
        private boolean sms2PrivAll;
        private List<NextPrcsBean> nextPrcs;

        public String getFlowName() {
            return flowName;
        }

        public void setFlowName(String flowName) {
            this.flowName = flowName;
        }

        public String getPrcsName() {
            return prcsName;
        }

        public void setPrcsName(String prcsName) {
            this.prcsName = prcsName;
        }

        public int getRemindFlag() {
            return remindFlag;
        }

        public void setRemindFlag(int remindFlag) {
            this.remindFlag = remindFlag;
        }

        public String getSyncDeal() {
            return syncDeal;
        }

        public void setSyncDeal(String syncDeal) {
            this.syncDeal = syncDeal;
        }

        public FlowRunBean getFlowRun() {
            return flowRun;
        }

        public void setFlowRun(FlowRunBean flowRun) {
            this.flowRun = flowRun;
        }

        public String getSmsRemind() {
            return smsRemind;
        }

        public void setSmsRemind(String smsRemind) {
            this.smsRemind = smsRemind;
        }

        public String getSms2Remind() {
            return sms2Remind;
        }

        public void setSms2Remind(String sms2Remind) {
            this.sms2Remind = sms2Remind;
        }

        public boolean isSms2PrivNext() {
            return sms2PrivNext;
        }

        public void setSms2PrivNext(boolean sms2PrivNext) {
            this.sms2PrivNext = sms2PrivNext;
        }

        public boolean isSms2PrivStart() {
            return sms2PrivStart;
        }

        public void setSms2PrivStart(boolean sms2PrivStart) {
            this.sms2PrivStart = sms2PrivStart;
        }

        public boolean isSms2PrivAll() {
            return sms2PrivAll;
        }

        public void setSms2PrivAll(boolean sms2PrivAll) {
            this.sms2PrivAll = sms2PrivAll;
        }

        public List<NextPrcsBean> getNextPrcs() {
            return nextPrcs;
        }

        public void setNextPrcs(List<NextPrcsBean> nextPrcs) {
            this.nextPrcs = nextPrcs;
        }

        public static class FlowRunBean {
            /**
             * runName : []号
             * isAllowTurn : true
             * userNameStr : <font color=red title=正在办理>admin(办理中)</font>
             * notAllFinish : 
             */

            private String runName;
            private boolean isAllowTurn;
            private String userNameStr;
            private String notAllFinish;

            public String getRunName() {
                return runName;
            }

            public void setRunName(String runName) {
                this.runName = runName;
            }

            public boolean isIsAllowTurn() {
                return isAllowTurn;
            }

            public void setIsAllowTurn(boolean isAllowTurn) {
                this.isAllowTurn = isAllowTurn;
            }

            public String getUserNameStr() {
                return userNameStr;
            }

            public void setUserNameStr(String userNameStr) {
                this.userNameStr = userNameStr;
            }

            public String getNotAllFinish() {
                return notAllFinish;
            }

            public void setNotAllFinish(String notAllFinish) {
                this.notAllFinish = notAllFinish;
            }
        }

        public static class NextPrcsBean {
            /**
             * seqId : 697
             * prcsId : 3
             * prcsUser : 
             * prcsDept : 
             * prcsPriv : 325,326
             * docSmsStyle : #{办理人}:#{标题}请你审核。拟稿人：#{拟稿人}
             * childFlow : 0
             * parentRun : 0
             * prcsName : 分管园长审核
             * isAutoSelect : false
             * prcsOpUser : 
             * prcsOpUserName : 
             * prcsUserName : 
             * userFilter : 0
             * userLock : false
             * topFlag : 0
             * assitHandler : [{"seqId":"1296","userName":"郑秀珍"},{"seqId":"1297","userName":"王芳华"}]
             */

            private int seqId;
            private String prcsId;
            private String prcsUser;
            private String prcsDept;
            private String prcsPriv;
            private String docSmsStyle;
            private String childFlow;
            private String parentRun;
            private String prcsName;
            private boolean isAutoSelect;
            private String prcsOpUser;
            private String prcsOpUserName;
            private String prcsUserName;
            private String userFilter;
            private boolean userLock;
            private String topFlag;
            private List<DeliverAssistHandlerBean> assitHandler;

            public int getSeqId() {
                return seqId;
            }

            public void setSeqId(int seqId) {
                this.seqId = seqId;
            }

            public String getPrcsId() {
                return prcsId;
            }

            public void setPrcsId(String prcsId) {
                this.prcsId = prcsId;
            }

            public String getPrcsUser() {
                return prcsUser;
            }

            public void setPrcsUser(String prcsUser) {
                this.prcsUser = prcsUser;
            }

            public String getPrcsDept() {
                return prcsDept;
            }

            public void setPrcsDept(String prcsDept) {
                this.prcsDept = prcsDept;
            }

            public String getPrcsPriv() {
                return prcsPriv;
            }

            public void setPrcsPriv(String prcsPriv) {
                this.prcsPriv = prcsPriv;
            }

            public String getDocSmsStyle() {
                return docSmsStyle;
            }

            public void setDocSmsStyle(String docSmsStyle) {
                this.docSmsStyle = docSmsStyle;
            }

            public String getChildFlow() {
                return childFlow;
            }

            public void setChildFlow(String childFlow) {
                this.childFlow = childFlow;
            }

            public String getParentRun() {
                return parentRun;
            }

            public void setParentRun(String parentRun) {
                this.parentRun = parentRun;
            }

            public String getPrcsName() {
                return prcsName;
            }

            public void setPrcsName(String prcsName) {
                this.prcsName = prcsName;
            }

            public boolean isIsAutoSelect() {
                return isAutoSelect;
            }

            public void setIsAutoSelect(boolean isAutoSelect) {
                this.isAutoSelect = isAutoSelect;
            }

            public String getPrcsOpUser() {
                return prcsOpUser;
            }

            public void setPrcsOpUser(String prcsOpUser) {
                this.prcsOpUser = prcsOpUser;
            }

            public String getPrcsOpUserName() {
                return prcsOpUserName;
            }

            public void setPrcsOpUserName(String prcsOpUserName) {
                this.prcsOpUserName = prcsOpUserName;
            }

            public String getPrcsUserName() {
                return prcsUserName;
            }

            public void setPrcsUserName(String prcsUserName) {
                this.prcsUserName = prcsUserName;
            }

            public String getUserFilter() {
                return userFilter;
            }

            public void setUserFilter(String userFilter) {
                this.userFilter = userFilter;
            }

            public boolean isUserLock() {
                return userLock;
            }

            public void setUserLock(boolean userLock) {
                this.userLock = userLock;
            }

            public String getTopFlag() {
                return topFlag;
            }

            public void setTopFlag(String topFlag) {
                this.topFlag = topFlag;
            }

            public List<DeliverAssistHandlerBean> getAssitHandler() {
                return assitHandler;
            }

            public void setAssitHandler(List<DeliverAssistHandlerBean> assitHandler) {
                this.assitHandler = assitHandler;
            }

           
        }
    }
}
