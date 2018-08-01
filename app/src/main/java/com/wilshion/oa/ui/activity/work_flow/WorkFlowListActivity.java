package com.wilshion.oa.ui.activity.work_flow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wilshion.common.network.HttpCallBack;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseRvActivity;
import com.wilshion.oa.ui.adapter.WorkFlowListAdapter;
import com.wilshion.oa.ui.bean.ResponseBean;
import com.wilshion.oa.ui.bean.WorkFlowBean;
import com.wilshion.oa.ui.bean.WorkFlowListRespBean;
import com.wilshion.oa.ui.constant.Constant;
import com.wilshion.oa.ui.utils.HttpUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Wilshion on 2018/7/26 09:54.
 * [description : 工作流列表]
 * [version : 1.0]
 */
public class WorkFlowListActivity extends BaseRvActivity<WorkFlowBean, WorkFlowListAdapter> implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {
    @Override
    protected void setTitleBar() {
        setTitle("工作流");
    }

    @Override
    protected WorkFlowListAdapter createAdapter() {
        return new WorkFlowListAdapter(R.layout.item_work_flow_list);
    }

    @Override
    public boolean showItemDecoration() {
        return false;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        getAdapter().setOnItemClickListener(this);
        getAdapter().setOnItemChildClickListener(this);
        requestData();
    }


    @Override
    protected void requestData() {
        super.requestData();
        showWating("正在加载中");
        HashMap<String, String> params = new HashMap<>();
        params.put("pageNo", getCurrentPage() + "");
        params.put("psnName", "");
        params.put("deptName", "");
        HttpUtil.requestPost(this, "workflowList", params, new HttpCallBack<ResponseBean<WorkFlowListRespBean>>() {
            @Override
            public void onSuccess(int statusCode, String rawJsonResponse, ResponseBean<WorkFlowListRespBean> response) {
                closeDialog();
                showLogD(response);
                if (response.isSuccess()) {
                    List<WorkFlowBean> msgBeans = response.getDetail().getWorkflowList();
                    showData(msgBeans);
                } else {
                    showError(response.getResultNote());
                }
            }

            @Override
            public void onFailure(int statusCode, String rawJsonResponse, ResponseBean<WorkFlowListRespBean> response) {
                showLogD(rawJsonResponse);
                showError(rawJsonResponse);
            }
        });
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        WorkFlowBean workFlowBean = getAdapter().getItem(position);
        Intent intent = new Intent();
        intent.putExtra(Constant.INTENT_PARAM_DATA, workFlowBean);
        int id = view.getId();
        switch (id) {
            case R.id.tv_form:
                intent.setClass(this, WorkFlowFormActivity.class);
                break;
            case R.id.tv_zhuban:
                intent.setClass(this, WorkFlowHandleActivity.class);
                break;
            case R.id.tv_deliver:
                intent.setClass(this, WorkFlowDeliverActivity.class);
                break;
            case R.id.tv_sign:
                intent.setClass(this, WorkFlowSignActivity.class);
                break;
            default:
                return;
        }
        startActivity(intent);
    }
}
