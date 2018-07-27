package com.wilshion.oa.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Wilshion on 2018/7/26 14:35.
 * [description : ]
 * [version : 1.0]
 */
public class EmailBean implements Parcelable {

    /**
     * SEQ_ID : 66
     * FROM_ID : 1
     * SUBJECT : 测试邮件主题
     * SEND_TIME : 2018-07-24 20:24:51.0
     * IMPORTANT : 3
     * ATTACHMENT_ID : 
     * ATTACHMENT_NAME : 
     * USER_NAME : admin
     * COPY_TO_ID : 
     * COPY_PERSION_NAME : 
     * CONTENT : 测试邮件。
     */

    private int SEQ_ID;
    private int FROM_ID;
    private String SUBJECT;
    private String SEND_TIME;
    private String IMPORTANT;
    private String ATTACHMENT_ID;
    private String ATTACHMENT_NAME;
    private String USER_NAME;
    private String COPY_TO_ID;
    private String COPY_PERSION_NAME;
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

    public String getSUBJECT() {
        return SUBJECT;
    }

    public void setSUBJECT(String SUBJECT) {
        this.SUBJECT = SUBJECT;
    }

    public String getSEND_TIME() {
        return SEND_TIME;
    }

    public void setSEND_TIME(String SEND_TIME) {
        this.SEND_TIME = SEND_TIME;
    }

    public String getIMPORTANT() {
        return IMPORTANT;
    }

    public void setIMPORTANT(String IMPORTANT) {
        this.IMPORTANT = IMPORTANT;
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

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getCOPY_TO_ID() {
        return COPY_TO_ID;
    }

    public void setCOPY_TO_ID(String COPY_TO_ID) {
        this.COPY_TO_ID = COPY_TO_ID;
    }

    public String getCOPY_PERSION_NAME() {
        return COPY_PERSION_NAME;
    }

    public void setCOPY_PERSION_NAME(String COPY_PERSION_NAME) {
        this.COPY_PERSION_NAME = COPY_PERSION_NAME;
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
        dest.writeString(this.SUBJECT);
        dest.writeString(this.SEND_TIME);
        dest.writeString(this.IMPORTANT);
        dest.writeString(this.ATTACHMENT_ID);
        dest.writeString(this.ATTACHMENT_NAME);
        dest.writeString(this.USER_NAME);
        dest.writeString(this.COPY_TO_ID);
        dest.writeString(this.COPY_PERSION_NAME);
        dest.writeString(this.CONTENT);
    }

    public EmailBean() {
    }

    protected EmailBean(Parcel in) {
        this.SEQ_ID = in.readInt();
        this.FROM_ID = in.readInt();
        this.SUBJECT = in.readString();
        this.SEND_TIME = in.readString();
        this.IMPORTANT = in.readString();
        this.ATTACHMENT_ID = in.readString();
        this.ATTACHMENT_NAME = in.readString();
        this.USER_NAME = in.readString();
        this.COPY_TO_ID = in.readString();
        this.COPY_PERSION_NAME = in.readString();
        this.CONTENT = in.readString();
    }

    public static final Parcelable.Creator<EmailBean> CREATOR = new Parcelable.Creator<EmailBean>() {
        @Override
        public EmailBean createFromParcel(Parcel source) {
            return new EmailBean(source);
        }

        @Override
        public EmailBean[] newArray(int size) {
            return new EmailBean[size];
        }
    };
}
