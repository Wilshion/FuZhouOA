package com.wilshion.oa.ui.activity.work_flow;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.wilshion.common.network.HttpCallBack;
import com.wilshion.common.utils.EmptyUtils;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseTitleBarActivity;
import com.wilshion.oa.ui.adapter.WorkFlowDeliverHandlerAdapter;
import com.wilshion.oa.ui.bean.DeliverAssistHandlerBean;
import com.wilshion.oa.ui.bean.ResponseBean;
import com.wilshion.oa.ui.bean.WorkFlowBean;
import com.wilshion.oa.ui.bean.WorkFlowDeliverBean;
import com.wilshion.oa.ui.constant.Constant;
import com.wilshion.oa.ui.utils.HttpUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Wilshion on 2018/7/26 21:13.
 * [description : 转交]
 * [version : 1.0]
 */
public class WorkFlowDeliverActivity extends BaseTitleBarActivity implements View.OnClickListener {
    private TextView tv_name;
    private TextView tv_user;
    private TextView tv_step;
    private TextView tv_select_main_handler;
    /**
     * 经办人
     */
    private RecyclerView rv_content;
    /**
     * 列表页面传过来的数据
     */
    private WorkFlowBean mWorkFlowBean;
    /**
     * 选择经办人的适配器
     */
    private WorkFlowDeliverHandlerAdapter mAdapter;
    private OptionsPickerView<DeliverAssistHandlerBean> mSelector;
    /**
     * 可供选择的经办人（接口下发的）
     */
    private List<DeliverAssistHandlerBean> mAssitHandlerBeanList;

    /**
     * 已选择的主办人
     */
    private DeliverAssistHandlerBean mSelectedMainHandler;

    private WorkFlowDeliverBean mWorkFlowDeliverBean;

    @Override
    protected void setTitleBar() {
        setTitle("工作转交");
//        setRightText("提交");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_work_flow_deliver;
    }

    @Override
    protected void onRightClick() {
        super.onRightClick();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        mWorkFlowBean = getIntent().getParcelableExtra(Constant.INTENT_PARAM_DATA);
        if (mWorkFlowBean == null) {
            showToast("数据获取失败");
            finishWithDelay(500);
            return;
        }

        tv_name = findViewById(R.id.tv_name);
        tv_user = findViewById(R.id.tv_user);
        tv_step = findViewById(R.id.tv_step);
        tv_select_main_handler = findViewById(R.id.tv_select_main_handler);
        rv_content = findViewById(R.id.rv_content);
        rv_content.setLayoutManager(new GridLayoutManager(this, 4));

        tv_select_main_handler.setOnClickListener(this);
        findViewById(R.id.tv_send).setOnClickListener(this);
        requestData();
    }

    @Override
    protected void requestData() {
        super.requestData();
        showWating("正在加载中...");
        HashMap params = new HashMap();
        params.put("flowId", mWorkFlowBean.getFLOW_ID());
        params.put("runId", mWorkFlowBean.getRUN_ID());
        params.put("prcsId", mWorkFlowBean.getPRCS_ID());
        params.put("flowPrcs", mWorkFlowBean.getFLOW_PRCS());

        HttpUtil.requestPost(this, "turn", params, new HttpCallBack<ResponseBean<WorkFlowDeliverBean>>() {
            @Override
            public void onSuccess(int statusCode, String rawJsonResponse, ResponseBean<WorkFlowDeliverBean> response) {
                if (response.isSuccess()) {
                    closeDialog();
                    showData(response.getDetail());
                } else {
                    showError(response.getResultNote());
                }
            }

            @Override
            public void onFailure(int statusCode, String rawJsonResponse, ResponseBean<WorkFlowDeliverBean> response) {
                showError(rawJsonResponse);
            }
        });
    }

    /**
     * 展示 请求回来的数据
     *
     * @param detail
     */
    private void showData(WorkFlowDeliverBean detail) {
        if (detail == null) {
            showToast("暂无数据");
            finishWithDelay(500);
            return;
        }
        mWorkFlowDeliverBean = detail;

        tv_name.setText(detail.getRunName());
        tv_user.setText(detail.getBeginUserName());
        List<WorkFlowDeliverBean.FlowProcessesBean> flowProcessesBeans = detail.getFlowProcesses();
        if (!EmptyUtils.isEmpty(flowProcessesBeans)) {
            WorkFlowDeliverBean.FlowProcessesBean flowProcessesBean = flowProcessesBeans.get(0);
            if (flowProcessesBean != null) {
                String nextStep = flowProcessesBeans.get(0).getPRCS_NAME();
                tv_step.setText(nextStep);

                List<DeliverAssistHandlerBean> assitHandlerBeans = flowProcessesBean.getAssitHandler();
                if (!EmptyUtils.isEmpty(assitHandlerBeans)) {
                    DeliverAssistHandlerBean assitHandlerBean = assitHandlerBeans.get(0);
                    if (assitHandlerBean != null) {
                        String firstPersonName = assitHandlerBean.getUserName();
                        tv_select_main_handler.setText(firstPersonName);
                    }

                    mAssitHandlerBeanList = assitHandlerBeans;
                    mSelectedMainHandler = assitHandlerBean;
                    showAssitRecyclerView();
                }

            }
        }
    }

    private void showAssitRecyclerView() {
        if (mAdapter == null) {
            mAdapter = new WorkFlowDeliverHandlerAdapter(R.layout.item_assit_handler);
        }
        mAdapter.setNewData(mAssitHandlerBeanList);
        rv_content.setAdapter(mAdapter);
    }

    private OptionsPickerView getSelector() {
        if (mSelector == null) {
            mSelector = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    mSelectedMainHandler = mAssitHandlerBeanList.get(options1);
                    tv_select_main_handler.setText(mSelectedMainHandler.getUserName());
                }
            }).build();
            mSelector.setNPicker(mAssitHandlerBeanList, null, null);
        }
        return mSelector;
    }

    private void showSelectView() {
        getSelector().show(tv_select_main_handler);
    }

    private void requestSave() {
        showWating("正在提交中...");
        List<WorkFlowDeliverBean.FlowProcessesBean> flowProcessesBeans = mWorkFlowDeliverBean.getFlowProcesses();
        WorkFlowDeliverBean.FlowProcessesBean flowProcessesBean = flowProcessesBeans.get(0);
        String prcsIdNext = flowProcessesBean.getPRCS_ID();
        String prcsOpUser_prcsIdNext = mAdapter.getSelectedUserIds();
        HashMap params = new HashMap();
        params.put("flowId", mWorkFlowBean.getFLOW_ID());
        params.put("runId", mWorkFlowBean.getRUN_ID());
        params.put("prcsId", mWorkFlowBean.getPRCS_ID());
        params.put("flowPrcs", mWorkFlowBean.getFLOW_PRCS());
        params.put("prcsIdNext", prcsIdNext);
        //经办人id，中间用","隔开
        params.put("prcsOpUser_" + prcsIdNext, prcsOpUser_prcsIdNext);
        //主办人
        params.put("prcsUser_" + prcsIdNext, mSelectedMainHandler.getSeqId());

        HttpUtil.requestPost(this, "put", params, new HttpCallBack<ResponseBean>() {
            @Override
            public void onSuccess(int statusCode, String rawJsonResponse, ResponseBean response) {
                if (response.isSuccess()) {
                    showSucceed(response.getResultNote());
                    setResult(RESULT_OK);
                    finishWithDelay(2000);
                } else {
                    showError(response.getResultNote());
                }
            }

            @Override
            public void onFailure(int statusCode, String rawJsonResponse, ResponseBean response) {
                showError(rawJsonResponse);
            }
        });
    }

    private boolean checkParams() {
        String selectedAssitIds = mAdapter.getSelectedUserIds();
        if (EmptyUtils.isEmpty(selectedAssitIds)) {
            showToast("请选择经办人");
            return false;
        }
        if (mSelectedMainHandler == null) {
            showToast("请选择主办人");
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.tv_select_main_handler:
                showSelectView();
                break;
            case R.id.tv_send:
                if (checkParams()) {
                    requestSave();
                }
                break;
        }
    }
}
