package com.wilshion.oa.ui.bean;

import java.util.List;

/**
 * Created by Wilshion on 2018/7/28 09:34.
 * [description : 工作日志接口返回值]
 * [version : 1.0]
 */
public class DiaryListRespBean {
    private List<DiaryBean> diaryList;

    public List<DiaryBean> getDiaryList() {
        return diaryList;
    }

    public void setDiaryList(List<DiaryBean> diaryList) {
        this.diaryList = diaryList;
    }
}
