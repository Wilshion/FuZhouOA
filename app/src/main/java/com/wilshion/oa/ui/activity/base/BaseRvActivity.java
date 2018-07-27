package com.wilshion.oa.ui.activity.base;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.github.nukc.stateview.StateView;
import com.luck.picture.lib.decoration.RecycleViewDivider;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.constant.Constant;

import java.util.List;

/**
 * Created by Wilshion on 2018/7/26 10:55.
 * [description : 含有 recyclerView 基类]
 * [version : 1.0]
 */
public abstract class BaseRvActivity<T, A extends BaseQuickAdapter<T, BaseViewHolder>> extends BaseTitleBarActivity implements OnLoadmoreListener, OnRefreshListener {
    private SmartRefreshLayout refresh_layout;
    private RecyclerView rv_content;
    private StateView mStateView;

    private A mAdapter;

    private int mCurrentPage = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rv;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        
        initRefreshLayout();
        initRecyclerView();
        initStateView();
    }

    private void initStateView() {
        mStateView = StateView.inject(refresh_layout);
        if (mStateView != null) {
            mStateView.setOnRetryClickListener(new StateView.OnRetryClickListener() {
                @Override
                public void onRetryClick() {
                    requestData();
                }
            });
            mStateView.setEmptyResource(R.layout.state_view_no_data);
//            mStateView.setLoadingResource(R.layout.page_loading);
//            mStateView.setRetryResource(R.layout.page_net_error);
        }
    }

    protected void initRefreshLayout() {
        refresh_layout = findViewById(R.id.refresh_layout);
        refresh_layout.setPrimaryColors(0xff444444, 0xffffffff);
        refresh_layout.setEnableRefresh(enableRefresh());
        if (enableRefresh()) {
            refresh_layout.setOnRefreshListener(this);
        }

        refresh_layout.setEnableLoadmore(enableLoadMore());
        if (enableLoadMore()) {
//            refresh_layout.setEnableAutoLoadmore(enableAutoLoadMore());
            refresh_layout.setOnLoadmoreListener(this);
        }
        /**
         * 设置在内容不满一页的时候，是否可以上拉加载更多
         */
        refresh_layout.setEnableLoadmoreWhenContentNotFull(true);
    }

    protected void initRecyclerView() {
        rv_content = findViewById(R.id.rv_content);
        rv_content.setLayoutManager(getRecyclerViewLayoutManager());
        if (showItemDecoration())
            rv_content.addItemDecoration(getRecyclerViewItemDecoration());
        rv_content.setAdapter(getAdapter());
    }

    protected abstract A createAdapter();

    protected A getAdapter() {
        if (mAdapter == null)
            mAdapter = createAdapter();
        return mAdapter;
    }

    public int getPageSize() {
        return Constant.DEFAULT_PAGE_SIZE;
    }

    public boolean enableRefresh() {
        return true;
    }

    public boolean enableLoadMore() {
        return false;
    }

    public boolean showItemDecoration() {
        return true;
    }

    public RecyclerView.LayoutManager getRecyclerViewLayoutManager() {
        return new LinearLayoutManager(this);
    }

    public RecyclerView.ItemDecoration getRecyclerViewItemDecoration() {
        return new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        mCurrentPage = 1;
        requestData();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        mCurrentPage++;
        requestData();
    }

    protected SmartRefreshLayout getRefreshLayout() {
        return refresh_layout;
    }

    public int getCurrentPage() {
        return mCurrentPage;
    }

    /**
     * 展示数据，供子类调用
     *
     * @param dataList
     */
    protected void showData(List<T> dataList) {
        boolean isRefresh = getCurrentPage() == 1;

        if (isRefresh) {
            /** 当前状态是下拉刷新 */
            getRefreshLayout().finishRefresh();
        } else {
            /** 当前状态是上啦加载更多 */
            getRefreshLayout().finishLoadmore();
        }

        //1、控制内容的状态
        if (dataList == null | dataList.size() == 0) {
            /**当前无数据*/
            if (isRefresh) {
                /** 刷新时候就没有数据，说明是完全没有数据 */
                mStateView.showEmpty();
            } else {
                /** 加载更多的时候没有数据，告诉刷新控件，已经没有更多数据了 */
                getRefreshLayout().setLoadmoreFinished(true);
            }
        } else {
            /**
             *  既然有数据，无论是刷新还是加载更多，都告诉控件还有加载更多，
             *  否则当用户加载到最后一页（例如第三页），此时再下拉刷新，然后再上啦加载更多则显示无更多数据
             */
            getRefreshLayout().setLoadmoreFinished(false);
            /**当前有数据*/
            mStateView.showContent();
        }

        //2、控制adapter
        if (isRefresh)
            mAdapter.setNewData(dataList);
        else mAdapter.addData(dataList);
    }
}
