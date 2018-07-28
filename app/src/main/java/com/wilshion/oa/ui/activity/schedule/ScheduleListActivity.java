package com.wilshion.oa.ui.activity.schedule;

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
import com.wilshion.oa.ui.adapter.ScheduleListAdapter;
import com.wilshion.oa.ui.bean.ResponseBean;
import com.wilshion.oa.ui.bean.ScheduleBean;
import com.wilshion.oa.ui.bean.ScheduleListRespBean;
import com.wilshion.oa.ui.utils.HttpUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Wilshion on 2018/7/26 09:51.
 * [description : 今日日程列表]
 * [version : 1.0]
 */
public class ScheduleListActivity extends BaseRvActivity<ScheduleBean, ScheduleListAdapter> implements BaseQuickAdapter.OnItemClickListener {
    @Override
    protected void setTitleBar() {
        setTitle("今日日程");
        setRightText("新建日程");
    }

    @Override
    protected ScheduleListAdapter createAdapter() {
        return new ScheduleListAdapter(R.layout.item_msg_list);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        getAdapter().setOnItemClickListener(this);
        requestData();
    }

    @Override
    public RecyclerView.ItemDecoration getRecyclerViewItemDecoration() {
        return new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL,10,R.color.gray_dark);
    }

    @Override
    protected void requestData() {
        super.requestData();
        showWating("正在加载中");
        HashMap<String, Integer> params = new HashMap<>();
        HttpUtil.requestPost(this, "calendarList",params, new HttpCallBack<ResponseBean<ScheduleListRespBean>>() {
            @Override
            public void onSuccess(int statusCode, String rawJsonResponse, ResponseBean<ScheduleListRespBean> response) {
                closeDialog();
                showLogD(response);
                if (response.isSuccess()){
                    List<ScheduleBean> msgBeans = response.getDetail().getCalendarList();
                    showData(msgBeans);
                }else {
                    showError(response.getResultNote());
                }
            }

            @Override
            public void onFailure(int statusCode, String rawJsonResponse, ResponseBean<ScheduleListRespBean> response) {
                showLogD(rawJsonResponse);
                showError(rawJsonResponse);
            }
        });
    }

    @Override
    protected void onRightClick() {
        Intent intent = new Intent(this,NewScheduleActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            onRefresh(getRefreshLayout());
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        
    }
}
