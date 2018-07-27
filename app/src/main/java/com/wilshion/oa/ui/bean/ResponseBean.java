package com.wilshion.oa.ui.bean;

/**
 * Created by Wilshion on 2018/7/25 16:54.
 * [description : ]
 * [version : 1.0]
 */
public class ResponseBean<T> {

    /**
     * cmd : loginOut
     * result : 0
     * resultNote : Success
     * totalRecordNum : 1
     * pageNum : 1
     * pageNo : 0
     * detail : {}
     */

    private String cmd;
    private int result;
    private String resultNote;
    private int totalRecordNum;
    private int pageNum;
    private int pageNo;
    private T detail;

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getResultNote() {
        return resultNote;
    }

    public void setResultNote(String resultNote) {
        this.resultNote = resultNote;
    }

    public int getTotalRecordNum() {
        return totalRecordNum;
    }

    public void setTotalRecordNum(int totalRecordNum) {
        this.totalRecordNum = totalRecordNum;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public T getDetail() {
        return detail;
    }

    public void setDetail(T detail) {
        this.detail = detail;
    }

    public boolean isSuccess() {
        return result == 0;
    }
}