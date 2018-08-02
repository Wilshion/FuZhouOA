package com.wilshion.oa.ui.activity.notification;

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
import com.wilshion.oa.ui.adapter.NotificationListAdapter;
import com.wilshion.oa.ui.bean.NotificationBean;
import com.wilshion.oa.ui.bean.NotificationListRespBean;
import com.wilshion.oa.ui.bean.ResponseBean;
import com.wilshion.oa.ui.constant.Constant;
import com.wilshion.oa.ui.utils.HttpUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Wilshion on 2018/7/26 09:45.
 * [description : 通知公告列表]
 * [version : 1.0]
 */
public class NotificationListActivity extends BaseRvActivity<NotificationBean, NotificationListAdapter> implements BaseQuickAdapter.OnItemClickListener {
    @Override
    protected void setTitleBar() {
        setTitle("公告通知");
    }

    @Override
    protected NotificationListAdapter createAdapter() {
        return new NotificationListAdapter(R.layout.item_msg_list);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        getAdapter().setOnItemClickListener(this);
        requestData();
    }

    @Override
    public RecyclerView.ItemDecoration getRecyclerViewItemDecoration() {
        return new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL,10, ResouceUtil.getColor( R.color.system_view_gray));
    }

    @Override
    protected void requestData() {
        super.requestData();
        showWating("正在加载中");
        HashMap<String, Integer> params = new HashMap<>();
        params.put("pageNo",getCurrentPage());
        HttpUtil.requestPost(this, "notifyList",params, new HttpCallBack<ResponseBean<NotificationListRespBean>>() {
            @Override
            public void onSuccess(int statusCode, String rawJsonResponse, ResponseBean<NotificationListRespBean> response) {
                closeDialog();
                showLogD(response);
                if (response.isSuccess()){
                    List<NotificationBean> notificationBeans = response.getDetail().getNotifyList();
                    showData(notificationBeans);
                }else {
                    showError(response.getResultNote());
                }
            }

            @Override
            public void onFailure(int statusCode, String rawJsonResponse, ResponseBean<NotificationListRespBean> response) {
                showLogD(rawJsonResponse);
                showError(rawJsonResponse);
            }
        });
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        NotificationBean notificationBean = getAdapter().getItem(position);
        Intent intent = new Intent(this,NotificationDetailActivity.class);
        intent.putExtra(Constant.INTENT_PARAM_DATA,notificationBean);
        startActivity(intent);
    }
}
