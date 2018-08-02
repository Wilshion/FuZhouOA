package com.wilshion.oa.ui.activity.address_list;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wilshion.common.network.HttpCallBack;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseTitleBarActivity;
import com.wilshion.oa.ui.adapter.AddressListAdapter;
import com.wilshion.oa.ui.bean.AddressBean;
import com.wilshion.oa.ui.bean.AddressListRespBean;
import com.wilshion.oa.ui.bean.ResponseBean;
import com.wilshion.oa.ui.utils.HttpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wilshion on 2018/7/26 09:56.
 * [description : 通讯博]
 * [version : 1.0]
 */
public class AddressListActivity extends BaseTitleBarActivity implements View.OnClickListener, OnRefreshListener, OnLoadmoreListener {

    private SmartRefreshLayout refresh_layout;
    private RecyclerView mRcView;
    private LinearLayout mLinearLayout;
    private List<AddressBean> mList = new ArrayList<>();
    private EditText mNameEt;
    private EditText mDanWeiEt;

    private AddressListAdapter mAdapter;

    private int mPageNo;

    @Override
    protected void setTitleBar() {
        setTitle("通讯簿");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address_list;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        findViewById(R.id.tv_search).setOnClickListener(this);

        refresh_layout = findViewById(R.id.adress_refresh_layout);
        refresh_layout.setPrimaryColors(0xff444444, 0xffffffff);
        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setOnLoadmoreListener(this);

        mRcView = findViewById(R.id.adress_rc_list_view);
        //创建LinearLayoutManager 对象
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        layoutmanager.setOrientation(LinearLayoutManager.VERTICAL);
        //设置RecyclerView 布局
        mRcView.setLayoutManager(layoutmanager);
        mLinearLayout = findViewById(R.id.id_search_linear_layout);


        mNameEt = findViewById(R.id.tx_et_name);
        mDanWeiEt = findViewById(R.id.et_danwei);


        mPageNo = 1;
    }


    private void researchData(){

        showWating("正在查询中");

        String psnName = mNameEt.getText().toString();
        String deptName = mDanWeiEt.getText().toString();
        String groupName = "";

        HashMap<String, String> paramsDetail = new HashMap<>();
        paramsDetail.put("psnName", psnName);
        paramsDetail.put("deptName", deptName);
        paramsDetail.put("groupName",groupName);
        paramsDetail.put("pageNo",String.valueOf(mPageNo));


        HttpUtil.requestPost(this, "contactList", paramsDetail, new HttpCallBack<ResponseBean<AddressListRespBean>>() {
            @Override
            public void onSuccess(int statusCode, String rawJsonResponse, ResponseBean<AddressListRespBean> response) {
                closeDialog();
                mLinearLayout.setVisibility(View.GONE);
                refresh_layout.setVisibility(View.VISIBLE);
                refresh_layout.finishRefresh();
                refresh_layout.finishLoadmore();

                if (mPageNo == 1){
                    mList = response.getDetail().getContactList();
                }else {
                    mList.addAll(mList);
                }

                if (mAdapter == null){
                    mAdapter = new AddressListAdapter(AddressListActivity.this,mList,R.layout.cell_address_list);
                    mRcView.setAdapter(mAdapter);
                }else {
                    mAdapter.updateData(mList);
                }
            }
            @Override
            public void onFailure(int statusCode, String rawJsonResponse, ResponseBean<AddressListRespBean> response) {
                refresh_layout.finishRefresh();
                refresh_layout.finishLoadmore();
                showError(rawJsonResponse);
            }
        });

    }


    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId){
            case R.id.tv_search:
            {
                researchData();
            }
                break;
            default:

                break;
        }
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        mPageNo = 1;
        researchData();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        mPageNo++;
        researchData();
    }
}
