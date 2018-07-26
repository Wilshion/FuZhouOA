package com.wilshion.oa.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Wilshion on 2018/7/26 20:35.
 * [description : ]
 * [version : 1.0]
 */
public class WorkFlowBean implements Parcelable {


    /**
     * PRCS_ID : 1
     * RUN_ID : 152
     * RUN_NAME : 教育教学类(2018-07-24 12:21:05)
     * FLOW_ID : 684
     * FLOW_NAME : 教育教学类
     * FLOW_TYPE : 1
     * PRCS_FLAG : 2
     * FLOW_PRCS : 1
     * OP_FLAG : 1
     * PRCS_NAME : 教师提交
     * FEEDBACK : 0
     */

    private int PRCS_ID;
    private int RUN_ID;
    private String RUN_NAME;
    private int FLOW_ID;
    private String FLOW_NAME;
    private String FLOW_TYPE;
    private String PRCS_FLAG;
    private int FLOW_PRCS;
    private String OP_FLAG;
    private String PRCS_NAME;
    private String FEEDBACK;

    public int getPRCS_ID() {
        return PRCS_ID;
    }

    public void setPRCS_ID(int PRCS_ID) {
        this.PRCS_ID = PRCS_ID;
    }

    public int getRUN_ID() {
        return RUN_ID;
    }

    public void setRUN_ID(int RUN_ID) {
        this.RUN_ID = RUN_ID;
    }

    public String getRUN_NAME() {
        return RUN_NAME;
    }

    public void setRUN_NAME(String RUN_NAME) {
        this.RUN_NAME = RUN_NAME;
    }

    public int getFLOW_ID() {
        return FLOW_ID;
    }

    public void setFLOW_ID(int FLOW_ID) {
        this.FLOW_ID = FLOW_ID;
    }

    public String getFLOW_NAME() {
        return FLOW_NAME;
    }

    public void setFLOW_NAME(String FLOW_NAME) {
        this.FLOW_NAME = FLOW_NAME;
    }

    public String getFLOW_TYPE() {
        return FLOW_TYPE;
    }

    public void setFLOW_TYPE(String FLOW_TYPE) {
        this.FLOW_TYPE = FLOW_TYPE;
    }

    public String getPRCS_FLAG() {
        return PRCS_FLAG;
    }

    public void setPRCS_FLAG(String PRCS_FLAG) {
        this.PRCS_FLAG = PRCS_FLAG;
    }

    public int getFLOW_PRCS() {
        return FLOW_PRCS;
    }

    public void setFLOW_PRCS(int FLOW_PRCS) {
        this.FLOW_PRCS = FLOW_PRCS;
    }

    public String getOP_FLAG() {
        return OP_FLAG;
    }

    public void setOP_FLAG(String OP_FLAG) {
        this.OP_FLAG = OP_FLAG;
    }

    public String getPRCS_NAME() {
        return PRCS_NAME;
    }

    public void setPRCS_NAME(String PRCS_NAME) {
        this.PRCS_NAME = PRCS_NAME;
    }

    public String getFEEDBACK() {
        return FEEDBACK;
    }

    public void setFEEDBACK(String FEEDBACK) {
        this.FEEDBACK = FEEDBACK;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.PRCS_ID);
        dest.writeInt(this.RUN_ID);
        dest.writeString(this.RUN_NAME);
        dest.writeInt(this.FLOW_ID);
        dest.writeString(this.FLOW_NAME);
        dest.writeString(this.FLOW_TYPE);
        dest.writeString(this.PRCS_FLAG);
        dest.writeInt(this.FLOW_PRCS);
        dest.writeString(this.OP_FLAG);
        dest.writeString(this.PRCS_NAME);
        dest.writeString(this.FEEDBACK);
    }

    public WorkFlowBean() {
    }

    protected WorkFlowBean(Parcel in) {
        this.PRCS_ID = in.readInt();
        this.RUN_ID = in.readInt();
        this.RUN_NAME = in.readString();
        this.FLOW_ID = in.readInt();
        this.FLOW_NAME = in.readString();
        this.FLOW_TYPE = in.readString();
        this.PRCS_FLAG = in.readString();
        this.FLOW_PRCS = in.readInt();
        this.OP_FLAG = in.readString();
        this.PRCS_NAME = in.readString();
        this.FEEDBACK = in.readString();
    }

    public static final Parcelable.Creator<WorkFlowBean> CREATOR = new Parcelable.Creator<WorkFlowBean>() {
        @Override
        public WorkFlowBean createFromParcel(Parcel source) {
            return new WorkFlowBean(source);
        }

        @Override
        public WorkFlowBean[] newArray(int size) {
            return new WorkFlowBean[size];
        }
    };
}
