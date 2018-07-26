package com.wilshion.oa.ui.activity.main;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.BaseTitleBarActivity;
import com.wilshion.oa.ui.activity.main.HomeDataSource.HomeItemBean;
import com.wilshion.oa.ui.adapter.MainAdapter;

import java.util.List;

public class MainActivity extends BaseTitleBarActivity implements BaseQuickAdapter.OnItemClickListener {
    private RecyclerView rv_content;
    private MainAdapter mAdapter;
    private List<HomeItemBean> mData;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mData = HomeDataSource.getHomeData();
        rv_content = findViewById(R.id.rv_content);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        rv_content.setLayoutManager(gridLayoutManager);
        rv_content.addItemDecoration(new HomeItemLine());
        mAdapter = new MainAdapter(R.layout.item_main_ac, mData);
        rv_content.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void setTitleBar() {
        setTitle(getString(R.string.app_title));
        setRightImageRes(R.drawable.ic_back);
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
       HomeItemBean homeItemBean = mData.get(position);
       goToActivity(homeItemBean.targetClazz);
    }

   

}
