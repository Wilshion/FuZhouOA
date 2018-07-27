package com.wilshion.oa.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Wilshion on 2018/7/26 16:05.
 * [description : ]
 * [version : 1.0]
 */
public class NotificationBean implements Parcelable {

    /**
     * SEQ_ID : 11
     * FROM_ID : 1340
     * USER_NAME : 校长
     * SUBJECT : 招聘通知1
     * TOP : 0
     * TYPE_ID : 
     * CLASS_DESC : 
     * BEGIN_DATE : 2018-07-26 00:00:00.0
     * SEND_TIME : 2018-07-26 16:15:59.0
     * ATTACHMENT_ID : 
     * ATTACHMENT_NAME : 
     * CONTENT : &nbsp;关于招聘事宜1
     */

    private int SEQ_ID;
    private int FROM_ID;
    private String USER_NAME;
    private String SUBJECT;
    private String TOP;
    private String TYPE_ID;
    private String CLASS_DESC;
    private String BEGIN_DATE;
    private String SEND_TIME;
    private String ATTACHMENT_ID;
    private String ATTACHMENT_NAME;
    private String CONTENT;

    public int getSEQ_ID() {
        return SEQ_ID;
    }

    public void setSEQ_ID(int SEQ_ID) {
        this.SEQ_ID = SEQ_ID;
    }

    public int getFROM_ID() {
        return FROM_ID;
    }

    public void setFROM_ID(int FROM_ID) {
        this.FROM_ID = FROM_ID;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getSUBJECT() {
        return SUBJECT;
    }

    public void setSUBJECT(String SUBJECT) {
        this.SUBJECT = SUBJECT;
    }

    public String getTOP() {
        return TOP;
    }

    public void setTOP(String TOP) {
        this.TOP = TOP;
    }

    public String getTYPE_ID() {
        return TYPE_ID;
    }

    public void setTYPE_ID(String TYPE_ID) {
        this.TYPE_ID = TYPE_ID;
    }

    public String getCLASS_DESC() {
        return CLASS_DESC;
    }

    public void setCLASS_DESC(String CLASS_DESC) {
        this.CLASS_DESC = CLASS_DESC;
    }

    public String getBEGIN_DATE() {
        return BEGIN_DATE;
    }

    public void setBEGIN_DATE(String BEGIN_DATE) {
        this.BEGIN_DATE = BEGIN_DATE;
    }

    public String getSEND_TIME() {
        return SEND_TIME;
    }

    public void setSEND_TIME(String SEND_TIME) {
        this.SEND_TIME = SEND_TIME;
    }

    public String getATTACHMENT_ID() {
        return ATTACHMENT_ID;
    }

    public void setATTACHMENT_ID(String ATTACHMENT_ID) {
        this.ATTACHMENT_ID = ATTACHMENT_ID;
    }

    public String getATTACHMENT_NAME() {
        return ATTACHMENT_NAME;
    }

    public void setATTACHMENT_NAME(String ATTACHMENT_NAME) {
        this.ATTACHMENT_NAME = ATTACHMENT_NAME;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.SEQ_ID);
        dest.writeInt(this.FROM_ID);
        dest.writeString(this.USER_NAME);
        dest.writeString(this.SUBJECT);
        dest.writeString(this.TOP);
        dest.writeString(this.TYPE_ID);
        dest.writeString(this.CLASS_DESC);
        dest.writeString(this.BEGIN_DATE);
        dest.writeString(this.SEND_TIME);
        dest.writeString(this.ATTACHMENT_ID);
        dest.writeString(this.ATTACHMENT_NAME);
        dest.writeString(this.CONTENT);
    }

    public NotificationBean() {
    }

    protected NotificationBean(Parcel in) {
        this.SEQ_ID = in.readInt();
        this.FROM_ID = in.readInt();
        this.USER_NAME = in.readString();
        this.SUBJECT = in.readString();
        this.TOP = in.readString();
        this.TYPE_ID = in.readString();
        this.CLASS_DESC = in.readString();
        this.BEGIN_DATE = in.readString();
        this.SEND_TIME = in.readString();
        this.ATTACHMENT_ID = in.readString();
        this.ATTACHMENT_NAME = in.readString();
        this.CONTENT = in.readString();
    }

    public static final Parcelable.Creator<NotificationBean> CREATOR = new Parcelable.Creator<NotificationBean>() {
        @Override
        public NotificationBean createFromParcel(Parcel source) {
            return new NotificationBean(source);
        }

        @Override
        public NotificationBean[] newArray(int size) {
            return new NotificationBean[size];
        }
    };
}
