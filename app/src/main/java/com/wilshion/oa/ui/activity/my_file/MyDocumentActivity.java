package com.wilshion.oa.ui.activity.my_file;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wilshion.common.network.HttpCallBack;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseRvActivity;
import com.wilshion.oa.ui.adapter.MyDocumentAdapter;
import com.wilshion.oa.ui.bean.MyDocumentRespBean;
import com.wilshion.oa.ui.bean.MyDocumentRespBean.MyDocumentBean;
import com.wilshion.oa.ui.bean.ResponseBean;
import com.wilshion.oa.ui.constant.Constant;
import com.wilshion.oa.ui.utils.HttpUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Wilshion on 2018/7/26 09:53.
 * [description : 我的文件]
 * [version : 1.0]
 */
public class MyDocumentActivity extends BaseRvActivity<MyDocumentBean, MyDocumentAdapter> implements BaseQuickAdapter.OnItemChildClickListener {
    @Override
    protected void setTitleBar() {
        setTitle("我的公文");
    }


    @Override
    protected MyDocumentAdapter createAdapter() {
        return new MyDocumentAdapter(R.layout.item_work_flow_list);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);


        getAdapter().setOnItemChildClickListener(this);
        
        requestData();
    }

    @Override
    protected void requestData() {
        super.requestData();

        showWating("正在加载中");
        HashMap params = new HashMap<>();
        params.put("pageNo", getCurrentPage());
        params.put("pageNum", 10);
        params.put("flowId", "");
        params.put("typeStr", 2);
        params.put("sortId", 183);

        HttpUtil.requestPost(this, "getDocList", params, new HttpCallBack<ResponseBean<MyDocumentRespBean>>() {
            @Override
            public void onSuccess(int statusCode, String rawJsonResponse, ResponseBean<MyDocumentRespBean> response) {
                closeDialog();
                showLogD(response);
                if (response.isSuccess()) {
                    List<MyDocumentBean> documentBeanList = response.getDetail().getListData();
                    showData(documentBeanList);
                } else {
                    showError(response.getResultNote());
                }
            }

            @Override
            public void onFailure(int statusCode, String rawJsonResponse, ResponseBean<MyDocumentRespBean> response) {
                showLogD(rawJsonResponse);
                showError(rawJsonResponse);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            onRefresh(getRefreshLayout());
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        MyDocumentBean myDocumentBean = getAdapter().getItem(position);
        Intent intent = new Intent();
        intent.putExtra(Constant.INTENT_PARAM_DATA, myDocumentBean);
        int id = view.getId();
        switch (id) {
            case R.id.tv_zhuban:
                intent.setClass(this, MyDocumentHandleActivity.class);
                break;
            case R.id.tv_deliver:
                intent.setClass(this, MyDocumentDeliverActivity.class);
                break;
            case R.id.tv_sign:
                intent.setClass(this, MyDocumentSignActivity.class);
                break;
            default:
                return;
        }
        startActivityForResult(intent,0);
    }
}
