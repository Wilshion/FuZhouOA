package com.wilshion.oa.ui.activity.msg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.luck.picture.lib.decoration.RecycleViewDivider;
import com.wilshion.common.network.HttpCallBack;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseRvActivity;
import com.wilshion.oa.ui.adapter.MsgListAdapter;
import com.wilshion.oa.ui.bean.MsgBean;
import com.wilshion.oa.ui.bean.MsgListRespBean;
import com.wilshion.oa.ui.bean.ResponseBean;
import com.wilshion.oa.ui.constant.Constant;
import com.wilshion.oa.ui.utils.HttpUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Wilshion on 2018/7/26 09:39.
 * [description : 短信列表]
 * [version : 1.0]
 */
public class MsgListActivity extends BaseRvActivity<MsgBean, MsgListAdapter> implements BaseQuickAdapter.OnItemClickListener {
    @Override
    protected void setTitleBar() {
        setTitle("短信");
        setRightText("写短信");
    }

    @Override
    protected MsgListAdapter createAdapter() {
        return new MsgListAdapter(R.layout.item_msg_list);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        requestData();
        getAdapter().setOnItemClickListener(this);
    }

    @Override
    public RecyclerView.ItemDecoration getRecyclerViewItemDecoration() {
        return new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL, 10, R.color.system_view_gray);
    }

    @Override
    protected void requestData() {
        showWating("正在加载中");
        HashMap<String, Integer> params = new HashMap<>();
        params.put("pageNo", getCurrentPage());
        HttpUtil.requestPost(this, "smsesList", params, new HttpCallBack<ResponseBean<MsgListRespBean>>() {
            @Override
            public void onSuccess(int statusCode, String rawJsonResponse, ResponseBean<MsgListRespBean> response) {
                closeDialog();
                showLogD(response);
                if (response.isSuccess()) {
                    List<MsgBean> msgBeans = response.getDetail().getSmsesList();
                    showData(msgBeans);
                } else {
                    showError(response.getResultNote());
                }
            }

            @Override
            public void onFailure(int statusCode, String rawJsonResponse, ResponseBean<MsgListRespBean> response) {
                showLogD(rawJsonResponse);
                showError(rawJsonResponse);
            }
        });
    }

    @Override
    protected void onRightClick() {
        Intent intent = new Intent(this, MsgSendActivity.class);
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
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        MsgBean itemBean = (MsgBean) adapter.getData().get(position);
        Intent intent = new Intent(this, MsgDetailActivity.class);
        intent.putExtra(Constant.INTENT_PARAM_DATA, itemBean);
        startActivityForResult(intent, 1);
    }
}
