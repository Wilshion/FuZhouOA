package com.wilshion.oa.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Wilshion on 2018/7/26 11:11.
 * [description : 短信 item ]
 * [version : 1.0]
 */
public class MsgBean implements Parcelable {

    /**
     * SEQ_ID : 1640
     * FROM_ID : 1
     * SEND_TIME : 2018-07-24 20:24:51.0
     * SMS_TYPE : 2
     * CONTENT :  请查收我的邮件！主题：测试邮件主题
     * USER_NAME : admin
     */

    private int SEQ_ID;
    private int FROM_ID;
    private String SEND_TIME;
    private String SMS_TYPE;
    private String CONTENT;
    private String USER_NAME;

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

    public String getSEND_TIME() {
        return SEND_TIME;
    }

    public void setSEND_TIME(String SEND_TIME) {
        this.SEND_TIME = SEND_TIME;
    }

    public String getSMS_TYPE() {
        return SMS_TYPE;
    }

    public void setSMS_TYPE(String SMS_TYPE) {
        this.SMS_TYPE = SMS_TYPE;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.SEQ_ID);
        dest.writeInt(this.FROM_ID);
        dest.writeString(this.SEND_TIME);
        dest.writeString(this.SMS_TYPE);
        dest.writeString(this.CONTENT);
        dest.writeString(this.USER_NAME);
    }

    public MsgBean() {
    }

    protected MsgBean(Parcel in) {
        this.SEQ_ID = in.readInt();
        this.FROM_ID = in.readInt();
        this.SEND_TIME = in.readString();
        this.SMS_TYPE = in.readString();
        this.CONTENT = in.readString();
        this.USER_NAME = in.readString();
    }

    public static final Parcelable.Creator<MsgBean> CREATOR = new Parcelable.Creator<MsgBean>() {
        @Override
        public MsgBean createFromParcel(Parcel source) {
            return new MsgBean(source);
        }

        @Override
        public MsgBean[] newArray(int size) {
            return new MsgBean[size];
        }
    };
}
