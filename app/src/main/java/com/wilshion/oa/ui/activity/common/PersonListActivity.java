package com.wilshion.oa.ui.activity.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wilshion.common.network.HttpCallBack;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseRvActivity;
import com.wilshion.oa.ui.adapter.PersonListAdapter;
import com.wilshion.oa.ui.bean.PersonListRespBean;
import com.wilshion.oa.ui.bean.ResponseBean;
import com.wilshion.oa.ui.constant.Constant;
import com.wilshion.oa.ui.utils.HttpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Wilshion on 2018/7/27 08:44.
 * [description : 通用人员列表页面]
 * [version : 1.0]
 */
public class PersonListActivity extends BaseRvActivity<PersonListRespBean.PersonBean, PersonListAdapter> implements BaseQuickAdapter.OnItemClickListener {
    private boolean isSingleMode;
    private static final String INTENT_PARAM_NAME_SELECT_MODE = "select_mode";

    public static void showPersonList(Activity context, boolean isSingleMode) {
        Intent intent = new Intent(context, PersonListActivity.class);
        intent.putExtra(INTENT_PARAM_NAME_SELECT_MODE, isSingleMode);
        context.startActivityForResult(intent, 1);
    }

    @Override
    protected PersonListAdapter createAdapter() {
        return new PersonListAdapter(R.layout.item_person_list, isSingleMode);
    }

    @Override
    protected void setTitleBar() {
        setTitle("人员列表");
    }

    @Override
    protected void onRightClick() {
        
        selectOk(-1);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        isSingleMode = getIntent().getBooleanExtra(INTENT_PARAM_NAME_SELECT_MODE, false);
        super.initView(savedInstanceState);

        if (isSingleMode) {
            //单选模式的时候，添加cell 的点击事件
            getAdapter().setOnItemClickListener(this);
        } else {
            // 多选模式的时候，点击cell 不触发选择完成，需要右上角的完成按钮
            setRightText("完成");
        }
        requestData();
    }

    @Override
    protected void requestData() {
        super.requestData();
        showWating("正在加载中...");
        HashMap<String, String> params = new HashMap<>();
        params.put("pageNo", getCurrentPage() + "");
        HttpUtil.requestPost(this, "personList", params, new HttpCallBack<ResponseBean<PersonListRespBean>>() {
            @Override
            public void onSuccess(int statusCode, String rawJsonResponse, ResponseBean<PersonListRespBean> response) {
                closeDialog();
                if (response.isSuccess()) {
                    List<PersonListRespBean.PersonBean> personBeans = response.getDetail().getPersonList();
                    showData(personBeans);
                } else {
                    showError(response.getResultNote());
                }
            }

            @Override
            public void onFailure(int statusCode, String rawJsonResponse, ResponseBean<PersonListRespBean> response) {
                showError(rawJsonResponse);
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        selectOk(position);
    }

    /**
     * 选择完成之后，返回值
     * @param position
     */
    private void selectOk(int position) {
        Intent intent = new Intent();
        if (isSingleMode) {
            PersonListRespBean.PersonBean personBean = getAdapter().getItem(position);
            intent.putExtra(Constant.INTENT_RESULT, personBean);
        } else {
            ArrayList<PersonListRespBean.PersonBean> selectedPersonList = getAdapter().getSelectedPersonList();
            if (null == selectedPersonList || selectedPersonList.size() == 0){
                showToast("你尚未选择");
                return;
            }
            intent.putParcelableArrayListExtra(Constant.INTENT_RESULT, selectedPersonList);
        }
        setResult(RESULT_OK, intent);
        finish();
    }
}
