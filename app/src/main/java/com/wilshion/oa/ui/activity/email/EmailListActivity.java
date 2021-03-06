package com.wilshion.oa.ui.activity.email;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.luck.picture.lib.decoration.RecycleViewDivider;
import com.wilshion.common.network.HttpCallBack;
import com.wilshion.common.utils.ResouceUtil;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseRvActivity;
import com.wilshion.oa.ui.adapter.EmailListAdapter;
import com.wilshion.oa.ui.bean.EmailBean;
import com.wilshion.oa.ui.bean.EmailListRespBean;
import com.wilshion.oa.ui.bean.ResponseBean;
import com.wilshion.oa.ui.constant.Constant;
import com.wilshion.oa.ui.utils.HttpUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Wilshion on 2018/7/26 09:44.
 * [description : 邮件列表]
 * [version : 1.0]
 */
public class EmailListActivity extends BaseRvActivity<EmailBean, EmailListAdapter> implements BaseQuickAdapter.OnItemClickListener {
    @Override
    protected void setTitleBar() {
        setTitle("邮件");
        setRightText("写邮件");
    }

    @Override
    protected EmailListAdapter createAdapter() {
        return new EmailListAdapter(R.layout.item_msg_list);
    }

    @Override
    public RecyclerView.ItemDecoration getRecyclerViewItemDecoration() {
        return new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL, 10, ResouceUtil.getColor(R.color.system_view_gray));
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        getAdapter().setOnItemClickListener(this);
        requestData();
    }

    @Override
    protected void onRightClick() {
        Intent intent = new Intent(this, EmailSendActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            onRefresh(getRefreshLayout());
        }
    }

    @Override
    protected void requestData() {
        super.requestData();
        showWating("正在加载中");
        HashMap<String, Integer> params = new HashMap<>();
        params.put("pageNo", getCurrentPage());
        HttpUtil.requestPost(this, "emailList", params, new HttpCallBack<ResponseBean<EmailListRespBean>>() {
            @Override
            public void onSuccess(int statusCode, String rawJsonResponse, ResponseBean<EmailListRespBean> response) {
                closeDialog();
                showLogD(response);
                if (response.isSuccess()) {
                    List<EmailBean> emailBeans = response.getDetail().getEmailList();
                    showData(emailBeans);
                } else {
                    showError(response.getResultNote());
                }
            }

            @Override
            public void onFailure(int statusCode, String rawJsonResponse, ResponseBean<EmailListRespBean> response) {
                showLogD(rawJsonResponse);
                showError(rawJsonResponse);
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        EmailBean emailBean = getAdapter().getItem(position);
        Intent intent = new Intent(this, EmailDetailActivity.class);
        intent.putExtra(Constant.INTENT_PARAM_DATA, emailBean);
        startActivityForResult(intent, 1);
    }
}
