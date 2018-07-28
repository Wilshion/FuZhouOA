package com.wilshion.oa.ui.activity.work_log;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wilshion.common.network.HttpCallBack;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseRvActivity;
import com.wilshion.oa.ui.adapter.DiaryListAdapter;
import com.wilshion.oa.ui.bean.DiaryBean;
import com.wilshion.oa.ui.bean.DiaryListRespBean;
import com.wilshion.oa.ui.bean.ResponseBean;
import com.wilshion.oa.ui.constant.Constant;
import com.wilshion.oa.ui.utils.HttpUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Wilshion on 2018/7/26 09:52.
 * [description : 工作日志列表]
 * [version : 1.0]
 */
public class WorkDiaryListActivity extends BaseRvActivity<DiaryBean,DiaryListAdapter> implements BaseQuickAdapter.OnItemClickListener {
    @Override
    protected void setTitleBar() {
        setTitle("工作日志");
        setRightText("写日志");
    }


    @Override
    protected DiaryListAdapter createAdapter() {
        return new DiaryListAdapter(R.layout.item_msg_list);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        
        getAdapter().setOnItemClickListener(this);
        requestData();
    }

    @Override
    protected void onRightClick() {
        super.onRightClick();
        Intent intent = new Intent(this, WorkDiaryEditActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            onRefresh(getRefreshLayout());
        }
    }

    @Override
    protected void requestData() {
        super.requestData();

        showWating("正在加载中");
        HashMap<String, Integer> params = new HashMap<>();
        params.put("pageNo", getCurrentPage());
        HttpUtil.requestPost(this, "diaryList", params, new HttpCallBack<ResponseBean<DiaryListRespBean>>() {
            @Override
            public void onSuccess(int statusCode, String rawJsonResponse, ResponseBean<DiaryListRespBean> response) {
                closeDialog();
                showLogD(response);
                if (response.isSuccess()) {
                    List<DiaryBean> emailBeans = response.getDetail().getDiaryList();
                    showData(emailBeans);
                } else {
                    showError(response.getResultNote());
                }
            }

            @Override
            public void onFailure(int statusCode, String rawJsonResponse, ResponseBean<DiaryListRespBean> response) {
                showLogD(rawJsonResponse);
                showError(rawJsonResponse);
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        DiaryBean diaryBean = getAdapter().getItem(position);
        Intent intent = new Intent(this, WorkDiaryEditActivity.class);
        intent.putExtra(Constant.INTENT_PARAM_DATA,diaryBean);
        startActivityForResult(intent, 1);
    }
}
