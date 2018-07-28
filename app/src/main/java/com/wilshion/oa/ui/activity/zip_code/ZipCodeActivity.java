package com.wilshion.oa.ui.activity.zip_code;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseTitleBarActivity;
import com.wilshion.oa.ui.adapter.ZipCodeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wilshion on 2018/7/26 09:56.
 * [description : 区号邮编]
 * [version : 1.0]
 */
public class ZipCodeActivity extends BaseTitleBarActivity implements View.OnClickListener {

    private SmartRefreshLayout refresh_layout;
    private RecyclerView mRcView;
    private LinearLayout mLinearLayout;
    private List<String> mList = new ArrayList<>();
    private ZipCodeAdapter mAdapter;

    @Override
    protected void setTitleBar() {
        setTitle("区号邮编查询");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zip_code;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);


        findViewById(R.id.tv_search_quhao).setOnClickListener(this);

        refresh_layout = findViewById(R.id.zip_refresh_layout);
        refresh_layout.setPrimaryColors(0xff444444, 0xffffffff);

        mRcView = findViewById(R.id.zip_rc_list_view);
        //创建LinearLayoutManager 对象
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        layoutmanager.setOrientation(LinearLayoutManager.VERTICAL);
        //设置RecyclerView 布局
        mRcView.setLayoutManager(layoutmanager);
        mLinearLayout = findViewById(R.id.id_search_linear_layout);

        mList.add("111111");
        mList.add("222222");
        mList.add("333333");
        mList.add("444444");
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId){
            case R.id.tv_search_quhao:
            {
                mLinearLayout.setVisibility(View.GONE);
                refresh_layout.setVisibility(View.VISIBLE);
                if (mAdapter == null){
                    mAdapter = new ZipCodeAdapter(this,mList,R.layout.cell_zip_code_list);
                    mRcView.setAdapter(mAdapter);
                }
            }
                break;
            default:
                break;
        }
    }
}
