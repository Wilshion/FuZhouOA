package com.wilshion.oa.ui.bean;

import java.util.List;

/**
 * Created by Wilshion on 2018/7/26 11:11.
 * [description : 短信列表接口返回对应详情字段]
 * [version : 1.0]
 */
public class MsgListRespBean {
    private List<MsgBean> smsesList;

    public List<MsgBean> getSmsesList() {
        return smsesList;
    }

    public void setSmsesList(List<MsgBean> smsesList) {
        this.smsesList = smsesList;
    }
}
