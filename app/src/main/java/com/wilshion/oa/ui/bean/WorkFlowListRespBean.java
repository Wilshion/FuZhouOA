package com.wilshion.oa.ui.bean;

import java.util.List;

/**
 * Created by Wilshion on 2018/7/26 20:35.
 * [description : 工作流接口返回列表模型]
 * [version : 1.0]
 */
public class WorkFlowListRespBean {
    private List<WorkFlowBean> workflowList;

    public List<WorkFlowBean> getWorkflowList() {
        return workflowList;
    }

    public void setWorkflowList(List<WorkFlowBean> workflowList) {
        this.workflowList = workflowList;
    }
}
