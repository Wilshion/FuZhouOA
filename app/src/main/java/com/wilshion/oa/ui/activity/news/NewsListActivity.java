package com.wilshion.oa.ui.activity.news;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wilshion.common.network.HttpCallBack;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseRvActivity;
import com.wilshion.oa.ui.adapter.NewsListAdapter;
import com.wilshion.oa.ui.bean.NewsBean;
import com.wilshion.oa.ui.bean.NewsListRespBean;
import com.wilshion.oa.ui.bean.ResponseBean;
import com.wilshion.oa.ui.constant.Constant;
import com.wilshion.oa.ui.utils.HttpUtil;

import java.util.HashMap;

/**
 * Created by Wilshion on 2018/7/26 09:48.
 * [description : 新闻列表]
 * [version : 1.0]
 */
public class NewsListActivity extends BaseRvActivity<NewsBean, NewsListAdapter> implements BaseQuickAdapter.OnItemClickListener {
    @Override
    protected void setTitleBar() {
        setTitle("最新新闻");
    }


    @Override
    protected NewsListAdapter createAdapter() {
        return new NewsListAdapter(R.layout.item_msg_list);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        getAdapter().setOnItemClickListener(this);
        requestData();
    }

    @Override
    protected void requestData() {
        super.requestData();

        showWating("正在加载中...");
        HashMap<String, Integer> params = new HashMap<>();
        params.put("pageNo", getCurrentPage());
        HttpUtil.requestPost(this, "newsList", params, new HttpCallBack<ResponseBean<NewsListRespBean>>() {
            @Override
            public void onSuccess(int statusCode, String rawJsonResponse, ResponseBean<NewsListRespBean> response) {
                if (response.isSuccess()) {
                    closeDialog();
                    showData(response.getDetail().getNewsList());
                } else {
                    showError(response.getResultNote());
                }
            }

            @Override
            public void onFailure(int statusCode, String rawJsonResponse, ResponseBean<NewsListRespBean> response) {
                showError(rawJsonResponse);
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        NewsBean newsBean = getAdapter().getItem(position);
        Intent intent = new Intent(this,NewsDetailActivity.class);
        intent.putExtra(Constant.INTENT_PARAM_DATA,newsBean);
        startActivity(intent);
    }
}
