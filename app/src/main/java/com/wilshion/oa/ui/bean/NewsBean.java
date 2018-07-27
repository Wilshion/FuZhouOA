package com.wilshion.oa.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Wilshion on 2018/7/27 22:20.
 * [description : ]
 * [version : 1.0]
 */
public class NewsBean implements Parcelable {

    /**
     * SEQ_ID : 2
     * PROVIDER : 1340
     * SUBJECT : 关于疫苗1
     * USER_NAME : 校长
     * NEWS_TIME : 2018-07-26 16:23:35.0
     * FORMAT : 0
     * TYPE_ID : 
     * CLASS_DESC : 
     * ATTACHMENT_ID : 
     * ATTACHMENT_NAME : 
     * CONTENT : &nbsp;未取得覅就
     */

    private int SEQ_ID;
    private String PROVIDER;
    private String SUBJECT;
    private String USER_NAME;
    private String NEWS_TIME;
    private String FORMAT;
    private String TYPE_ID;
    private String CLASS_DESC;
    private String ATTACHMENT_ID;
    private String ATTACHMENT_NAME;
    private String CONTENT;

    public int getSEQ_ID() {
        return SEQ_ID;
    }

    public void setSEQ_ID(int SEQ_ID) {
        this.SEQ_ID = SEQ_ID;
    }

    public String getPROVIDER() {
        return PROVIDER;
    }

    public void setPROVIDER(String PROVIDER) {
        this.PROVIDER = PROVIDER;
    }

    public String getSUBJECT() {
        return SUBJECT;
    }

    public void setSUBJECT(String SUBJECT) {
        this.SUBJECT = SUBJECT;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getNEWS_TIME() {
        return NEWS_TIME;
    }

    public void setNEWS_TIME(String NEWS_TIME) {
        this.NEWS_TIME = NEWS_TIME;
    }

    public String getFORMAT() {
        return FORMAT;
    }

    public void setFORMAT(String FORMAT) {
        this.FORMAT = FORMAT;
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
        dest.writeString(this.PROVIDER);
        dest.writeString(this.SUBJECT);
        dest.writeString(this.USER_NAME);
        dest.writeString(this.NEWS_TIME);
        dest.writeString(this.FORMAT);
        dest.writeString(this.TYPE_ID);
        dest.writeString(this.CLASS_DESC);
        dest.writeString(this.ATTACHMENT_ID);
        dest.writeString(this.ATTACHMENT_NAME);
        dest.writeString(this.CONTENT);
    }

    public NewsBean() {
    }

    protected NewsBean(Parcel in) {
        this.SEQ_ID = in.readInt();
        this.PROVIDER = in.readString();
        this.SUBJECT = in.readString();
        this.USER_NAME = in.readString();
        this.NEWS_TIME = in.readString();
        this.FORMAT = in.readString();
        this.TYPE_ID = in.readString();
        this.CLASS_DESC = in.readString();
        this.ATTACHMENT_ID = in.readString();
        this.ATTACHMENT_NAME = in.readString();
        this.CONTENT = in.readString();
    }

    public static final Parcelable.Creator<NewsBean> CREATOR = new Parcelable.Creator<NewsBean>() {
        @Override
        public NewsBean createFromParcel(Parcel source) {
            return new NewsBean(source);
        }

        @Override
        public NewsBean[] newArray(int size) {
            return new NewsBean[size];
        }
    };
}
