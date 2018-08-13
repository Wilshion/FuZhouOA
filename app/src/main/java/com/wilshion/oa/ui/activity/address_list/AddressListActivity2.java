package com.wilshion.oa.ui.activity.address_list;

import android.os.Bundle;

import com.wilshion.common.network.HttpCallBack;
import com.wilshion.common.utils.EmptyUtils;
import com.wilshion.common.utils.EncodeUtils;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseRvActivity;
import com.wilshion.oa.ui.adapter.AddressListAdapter;
import com.wilshion.oa.ui.bean.AddressBean;
import com.wilshion.oa.ui.bean.AddressListRespBean;
import com.wilshion.oa.ui.bean.ResponseBean;
import com.wilshion.oa.ui.dialog.AddressSearchWindow;
import com.wilshion.oa.ui.utils.HttpUtil;

import java.util.HashMap;

/**
 * Created by Wilshion on 2018/8/12 23:28.
 * [description : 新通讯簿]
 * [version : 1.0]
 */
public class AddressListActivity2 extends BaseRvActivity<AddressBean, AddressListAdapter> implements AddressSearchWindow.OnAddressListSearchListener {
    private AddressSearchWindow mAddressSearchWindow;
    private String mPsnName = "";
    private String mDeptName = "";

    @Override
    protected AddressListAdapter createAdapter() {
        return new AddressListAdapter(R.layout.cell_address_list);
    }

    @Override
    protected void setTitleBar() {
        setTitle("通讯簿");
        setRightText("搜索");
    }

    @Override
    protected void onRightClick() {
        super.onRightClick();
        showSearchWindow();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        onRefresh(getRefreshLayout());
    }

    @Override
    public boolean showItemDecoration() {
        return false;
    }

    private void showSearchWindow() {
        if (mAddressSearchWindow == null) {
            mAddressSearchWindow = new AddressSearchWindow(this);
            mAddressSearchWindow.setOnAddressListSearchListener(this);
        }
        mAddressSearchWindow.showAsDropDown(getTitleBar());
    }

    @Override
    public void onSearchListener(String psnName, String deptName) {
        mPsnName = psnName;
        mDeptName = deptName;
        if (!EmptyUtils.isEmpty(psnName)){
            mPsnName = EncodeUtils.urlEncode(psnName);
        }
        if (!EmptyUtils.isEmpty(psnName)){
            mDeptName = EncodeUtils.urlEncode(deptName);
        }
        onRefresh(getRefreshLayout());
    }

    @Override
    protected void requestData() {
        super.requestData();
        showWating("正在查询中");
        String groupName = "";
        HashMap<String, String> paramsDetail = new HashMap<>();
        paramsDetail.put("psnName", mPsnName);
        paramsDetail.put("deptName", mDeptName);
        paramsDetail.put("groupName", groupName);
        paramsDetail.put("pageNo", String.valueOf(getCurrentPage()));
        HttpUtil.requestPost(this, "contactList", paramsDetail, new HttpCallBack<ResponseBean<AddressListRespBean>>() {
            @Override
            public void onSuccess(int statusCode, String rawJsonResponse, ResponseBean<AddressListRespBean> response) {
                closeDialog();
                if (response.isSuccess()) {
                    showData(response.getDetail().getContactList());
                } else {
                    showError("未搜索到");
                }
            }

            @Override
            public void onFailure(int statusCode, String rawJsonResponse, ResponseBean<AddressListRespBean> response) {
                showError(rawJsonResponse);
            }
        });
    }
}
