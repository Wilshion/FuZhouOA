package com.wilshion.oa.ui.bean;

/**
 * Created by Wilshion on 2018/7/26 15:29.
 * [description : ]
 * [version : 1.0]
 */
public class ScheduleBean {

    /**
     * SEQ_ID : 44
     * CAL_TYPE : 1
     * CAL_LEVEL : 
     * CAL_TIME : 2018-07-26 09:35:00.0
     * END_TIME : 2018-07-26 19:23:59.0
     * CONTENT : 测试新建日程
     */

    private int SEQ_ID;
    private String CAL_TYPE;
    private String CAL_LEVEL;
    private String CAL_TIME;
    private String END_TIME;
    private String CONTENT;

    public int getSEQ_ID() {
        return SEQ_ID;
    }

    public void setSEQ_ID(int SEQ_ID) {
        this.SEQ_ID = SEQ_ID;
    }

    public String getCAL_TYPE() {
        return CAL_TYPE;
    }

    public void setCAL_TYPE(String CAL_TYPE) {
        this.CAL_TYPE = CAL_TYPE;
    }

    public String getCAL_LEVEL() {
        return CAL_LEVEL;
    }

    public void setCAL_LEVEL(String CAL_LEVEL) {
        this.CAL_LEVEL = CAL_LEVEL;
    }

    public String getCAL_TIME() {
        return CAL_TIME;
    }

    public void setCAL_TIME(String CAL_TIME) {
        this.CAL_TIME = CAL_TIME;
    }

    public String getEND_TIME() {
        return END_TIME;
    }

    public void setEND_TIME(String END_TIME) {
        this.END_TIME = END_TIME;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
    }
}
