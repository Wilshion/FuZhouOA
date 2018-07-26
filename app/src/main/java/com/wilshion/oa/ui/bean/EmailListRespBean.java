package com.wilshion.oa.ui.bean;

import java.util.List;

/**
 * Created by Wilshion on 2018/7/26 14:34.
 * [description : 邮件列表接口返回]
 * [version : 1.0]
 */
public class EmailListRespBean {
    private List<EmailBean> emailList;

    public List<EmailBean> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<EmailBean> emailList) {
        this.emailList = emailList;
    }
}
