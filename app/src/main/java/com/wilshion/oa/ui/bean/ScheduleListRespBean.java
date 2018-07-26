package com.wilshion.oa.ui.bean;

import java.util.List;

/**
 * Created by Wilshion on 2018/7/26 14:34.
 * [description : 邮件列表接口返回]
 * [version : 1.0]
 */
public class ScheduleListRespBean {
    private List<ScheduleBean> calendarList;

    public List<ScheduleBean> getCalendarList() {
        return calendarList;
    }

    public void setCalendarList(List<ScheduleBean> calendarList) {
        this.calendarList = calendarList;
    }
}
