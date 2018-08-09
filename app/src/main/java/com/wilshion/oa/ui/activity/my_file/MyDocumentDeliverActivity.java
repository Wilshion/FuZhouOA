package com.wilshion.oa.ui.activity.my_file;

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
import com.wilshion.oa.ui.bean.MyDocumentDeliverRespBean;
import com.wilshion.oa.ui.bean.MyDocumentDeliverRespBean.MyDocumentDeliverDataBean.NextPrcsBean;
import com.wilshion.oa.ui.bean.MyDocumentRespBean;
import com.wilshion.oa.ui.bean.ResponseBean;
import com.wilshion.oa.ui.constant.Constant;
import com.wilshion.oa.ui.utils.HttpUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Wilshion on 2018/8/9 21:04.
 * [description : 转交]
 * [version : 1.0]
 */
public class MyDocumentDeliverActivity extends BaseTitleBarActivity implements View.OnClickListener {
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
    private MyDocumentRespBean.MyDocumentBean mMyDocumentBean;
    /**
     * 选择经办人的适配器
     */
    private WorkFlowDeliverHandlerAdapter mAdapter;
    private OptionsPickerView<DeliverAssistHandlerBean> mSelector;
    /**
     * 可供选择的经办人（接口下发的）
     */
    private List<DeliverAssistHandlerBean> mDeliverAssistHandlerBeanList;

    /**
     * 已选择的主办人
     */
    private DeliverAssistHandlerBean mSelectedMainHandler;

    private MyDocumentDeliverRespBean.MyDocumentDeliverDataBean mMyDocumentDeliverDataBean;

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

        mMyDocumentBean = getIntent().getParcelableExtra(Constant.INTENT_PARAM_DATA);
        if (mMyDocumentBean == null) {
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
        params.put("flowId", mMyDocumentBean.getFlowId());
        params.put("runId", mMyDocumentBean.getRunId());
        params.put("prcsId", mMyDocumentBean.getPrcsId());
        params.put("flowPrcs", mMyDocumentBean.getFlowPrcs());
        params.put("isManage", "true");
        HttpUtil.requestPost(this, "turnDoc", params, new HttpCallBack<ResponseBean<MyDocumentDeliverRespBean>>() {
            @Override
            public void onSuccess(int statusCode, String rawJsonResponse, ResponseBean<MyDocumentDeliverRespBean> response) {
                if (response.isSuccess()) {
                    closeDialog();
                    showData(response.getDetail());
                } else {
                    showError(response.getResultNote());
                }
            }

            @Override
            public void onFailure(int statusCode, String rawJsonResponse, ResponseBean<MyDocumentDeliverRespBean> response) {
                showError(rawJsonResponse);
            }
        });
    }

    /**
     * 展示 请求回来的数据
     *
     * @param detail
     */
    private void showData(MyDocumentDeliverRespBean detail) {
        if (detail == null) {
            showToast("暂无数据");
            finishWithDelay(500);
            return;
        }
        mMyDocumentDeliverDataBean = detail.getData();

        tv_name.setText(mMyDocumentDeliverDataBean.getFlowName());
        tv_user.setText(mMyDocumentDeliverDataBean.getFlowName());
        List<NextPrcsBean> nextPrcsBeanList = mMyDocumentDeliverDataBean.getNextPrcs();
        if (!EmptyUtils.isEmpty(nextPrcsBeanList)) {
            NextPrcsBean nextPrcsBean = nextPrcsBeanList.get(0);
            if (nextPrcsBean != null) {
                String nextStep = nextPrcsBean.getPrcsName();
                tv_step.setText(nextStep);

                List<DeliverAssistHandlerBean> assitHandlerBeans = nextPrcsBean.getAssitHandler();
                if (!EmptyUtils.isEmpty(assitHandlerBeans)) {
                    DeliverAssistHandlerBean assitHandlerBean = assitHandlerBeans.get(0);
                    if (assitHandlerBean != null) {
                        String firstPersonName = assitHandlerBean.getUserName();
                        tv_select_main_handler.setText(firstPersonName);
                    }

                    mDeliverAssistHandlerBeanList = assitHandlerBeans;
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
        mAdapter.setNewData(mDeliverAssistHandlerBeanList);
        rv_content.setAdapter(mAdapter);
    }

    private OptionsPickerView getSelector() {
        if (mSelector == null) {
            mSelector = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    mSelectedMainHandler = mDeliverAssistHandlerBeanList.get(options1);
                    tv_select_main_handler.setText(mSelectedMainHandler.getUserName());
                }
            }).build();
            mSelector.setNPicker(mDeliverAssistHandlerBeanList, null, null);
        }
        return mSelector;
    }

    private void showSelectView() {
        getSelector().show(tv_select_main_handler);
    }

    private void requestSave() {
        showWating("正在提交中...");
        List<NextPrcsBean> nextPrcsBeanList = mMyDocumentDeliverDataBean.getNextPrcs();
        NextPrcsBean nextPrcsBean = nextPrcsBeanList.get(0);
        String prcsIdNext = nextPrcsBean.getPrcsId();
        String prcsOpUser_prcsIdNext = mAdapter.getSelectedUserIds();
        HashMap params = new HashMap();
        params.put("flowId", mMyDocumentBean.getFlowId());
        params.put("runId", mMyDocumentBean.getRunId());
        params.put("prcsId", mMyDocumentBean.getPrcsId());
        params.put("flowPrcs", mMyDocumentBean.getFlowPrcs());
        params.put("prcsChoose", prcsIdNext);
        params.put("isManage", "");

        //经办人id，中间用","隔开
        params.put("prcsOpUser_" + prcsIdNext, prcsOpUser_prcsIdNext);
        //主办人
        params.put("prcsUser_" + prcsIdNext, mSelectedMainHandler.getSeqId());

        HttpUtil.requestPost(this, "commitDoc", params, new HttpCallBack<ResponseBean>() {
            @Override
            public void onSuccess(int statusCode, String rawJsonResponse, ResponseBean response) {
                if (response.isSuccess()) {
                    showSucceed(response.getResultNote());
                    finishWithDelay(2000,true);
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
