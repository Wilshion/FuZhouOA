package com.wilshion.oa.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Wilshion on 2018/8/7 20:17.
 * [description : ]
 * [version : 1.0]
 */
public class MyDocumentRespBean {


    private List<MyDocumentBean> listData;

    public List<MyDocumentBean> getListData() {
        return listData;
    }

    public void setListData(List<MyDocumentBean> listData) {
        this.listData = listData;
    }

    public static class MyDocumentBean implements Parcelable {
        /**
         * seqId : 144
         * runId : 112
         * flowId : 572
         * flowName : 发文
         * runName : []号
         * userId : 1
         * userName : admin
         * prcsName : 提交公文
         * flowType : 1
         * prcsId : 1
         * opFlag : 1
         * flowPrcs : 1
         * freeOther : 2
         * isHaveDelPriv : true
         * title : 
         * sendNo : []号
         * miji : 　
         * jinji : 　
         * sendFlag : 
         * writtenTime : 
         * nowUser : admin
         */

        private int seqId;
        private int runId;
        private int flowId;
        private String flowName;
        private String runName;
        private String userId;
        private String userName;
        private String prcsName;
        private int flowType;
        private int prcsId;
        private int opFlag;
        private int flowPrcs;
        private int freeOther;
        private boolean isHaveDelPriv;
        private String title;
        private String sendNo;
        private String miji;
        private String jinji;
        private String sendFlag;
        private String writtenTime;
        private String nowUser;

        public int getSeqId() {
            return seqId;
        }

        public void setSeqId(int seqId) {
            this.seqId = seqId;
        }

        public int getRunId() {
            return runId;
        }

        public void setRunId(int runId) {
            this.runId = runId;
        }

        public int getFlowId() {
            return flowId;
        }

        public void setFlowId(int flowId) {
            this.flowId = flowId;
        }

        public String getFlowName() {
            return flowName;
        }

        public void setFlowName(String flowName) {
            this.flowName = flowName;
        }

        public String getRunName() {
            return runName;
        }

        public void setRunName(String runName) {
            this.runName = runName;
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

        public String getPrcsName() {
            return prcsName;
        }

        public void setPrcsName(String prcsName) {
            this.prcsName = prcsName;
        }

        public int getFlowType() {
            return flowType;
        }

        public void setFlowType(int flowType) {
            this.flowType = flowType;
        }

        public int getPrcsId() {
            return prcsId;
        }

        public void setPrcsId(int prcsId) {
            this.prcsId = prcsId;
        }

        public int getOpFlag() {
            return opFlag;
        }

        public void setOpFlag(int opFlag) {
            this.opFlag = opFlag;
        }

        public int getFlowPrcs() {
            return flowPrcs;
        }

        public void setFlowPrcs(int flowPrcs) {
            this.flowPrcs = flowPrcs;
        }

        public int getFreeOther() {
            return freeOther;
        }

        public void setFreeOther(int freeOther) {
            this.freeOther = freeOther;
        }

        public boolean isIsHaveDelPriv() {
            return isHaveDelPriv;
        }

        public void setIsHaveDelPriv(boolean isHaveDelPriv) {
            this.isHaveDelPriv = isHaveDelPriv;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSendNo() {
            return sendNo;
        }

        public void setSendNo(String sendNo) {
            this.sendNo = sendNo;
        }

        public String getMiji() {
            return miji;
        }

        public void setMiji(String miji) {
            this.miji = miji;
        }

        public String getJinji() {
            return jinji;
        }

        public void setJinji(String jinji) {
            this.jinji = jinji;
        }

        public String getSendFlag() {
            return sendFlag;
        }

        public void setSendFlag(String sendFlag) {
            this.sendFlag = sendFlag;
        }

        public String getWrittenTime() {
            return writtenTime;
        }

        public void setWrittenTime(String writtenTime) {
            this.writtenTime = writtenTime;
        }

        public String getNowUser() {
            return nowUser;
        }

        public void setNowUser(String nowUser) {
            this.nowUser = nowUser;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.seqId);
            dest.writeInt(this.runId);
            dest.writeInt(this.flowId);
            dest.writeString(this.flowName);
            dest.writeString(this.runName);
            dest.writeString(this.userId);
            dest.writeString(this.userName);
            dest.writeString(this.prcsName);
            dest.writeInt(this.flowType);
            dest.writeInt(this.prcsId);
            dest.writeInt(this.opFlag);
            dest.writeInt(this.flowPrcs);
            dest.writeInt(this.freeOther);
            dest.writeByte(this.isHaveDelPriv ? (byte) 1 : (byte) 0);
            dest.writeString(this.title);
            dest.writeString(this.sendNo);
            dest.writeString(this.miji);
            dest.writeString(this.jinji);
            dest.writeString(this.sendFlag);
            dest.writeString(this.writtenTime);
            dest.writeString(this.nowUser);
        }

        public MyDocumentBean() {
        }

        protected MyDocumentBean(Parcel in) {
            this.seqId = in.readInt();
            this.runId = in.readInt();
            this.flowId = in.readInt();
            this.flowName = in.readString();
            this.runName = in.readString();
            this.userId = in.readString();
            this.userName = in.readString();
            this.prcsName = in.readString();
            this.flowType = in.readInt();
            this.prcsId = in.readInt();
            this.opFlag = in.readInt();
            this.flowPrcs = in.readInt();
            this.freeOther = in.readInt();
            this.isHaveDelPriv = in.readByte() != 0;
            this.title = in.readString();
            this.sendNo = in.readString();
            this.miji = in.readString();
            this.jinji = in.readString();
            this.sendFlag = in.readString();
            this.writtenTime = in.readString();
            this.nowUser = in.readString();
        }

        public static final Parcelable.Creator<MyDocumentBean> CREATOR = new Parcelable.Creator<MyDocumentBean>() {
            @Override
            public MyDocumentBean createFromParcel(Parcel source) {
                return new MyDocumentBean(source);
            }

            @Override
            public MyDocumentBean[] newArray(int size) {
                return new MyDocumentBean[size];
            }
        };
    }
}
