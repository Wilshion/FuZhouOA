package com.wilshion.oa.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Wilshion on 2018/7/28 09:34.
 * [description : ]
 * [version : 1.0]
 */
public class DiaryBean implements Parcelable {

    /**
     * SEQ_ID : 13
     * DIA_TYPE : 1
     * DIA_DATE : 2018-07-27 00:00:00.0
     * SUBJECT : 日志主题
     * CONTENT : 写日志测试
     * ATTACHMENT_ID : 
     * ATTACHMENT_NAME : 
     */

    private int SEQ_ID;
    private int DIA_TYPE;
    private String DIA_DATE;
    private String SUBJECT;
    private String CONTENT;
    private String ATTACHMENT_ID;
    private String ATTACHMENT_NAME;

    public int getSEQ_ID() {
        return SEQ_ID;
    }

    public void setSEQ_ID(int SEQ_ID) {
        this.SEQ_ID = SEQ_ID;
    }

    public int getDIA_TYPE() {
        return DIA_TYPE;
    }

    public void setDIA_TYPE(int DIA_TYPE) {
        this.DIA_TYPE = DIA_TYPE;
    }

    public String getDIA_DATE() {
        return DIA_DATE;
    }

    public void setDIA_DATE(String DIA_DATE) {
        this.DIA_DATE = DIA_DATE;
    }

    public String getSUBJECT() {
        return SUBJECT;
    }

    public void setSUBJECT(String SUBJECT) {
        this.SUBJECT = SUBJECT;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.SEQ_ID);
        dest.writeInt(this.DIA_TYPE);
        dest.writeString(this.DIA_DATE);
        dest.writeString(this.SUBJECT);
        dest.writeString(this.CONTENT);
        dest.writeString(this.ATTACHMENT_ID);
        dest.writeString(this.ATTACHMENT_NAME);
    }

    public DiaryBean() {
    }

    protected DiaryBean(Parcel in) {
        this.SEQ_ID = in.readInt();
        this.DIA_TYPE = in.readInt();
        this.DIA_DATE = in.readString();
        this.SUBJECT = in.readString();
        this.CONTENT = in.readString();
        this.ATTACHMENT_ID = in.readString();
        this.ATTACHMENT_NAME = in.readString();
    }

    public static final Parcelable.Creator<DiaryBean> CREATOR = new Parcelable.Creator<DiaryBean>() {
        @Override
        public DiaryBean createFromParcel(Parcel source) {
            return new DiaryBean(source);
        }

        @Override
        public DiaryBean[] newArray(int size) {
            return new DiaryBean[size];
        }
    };
}
