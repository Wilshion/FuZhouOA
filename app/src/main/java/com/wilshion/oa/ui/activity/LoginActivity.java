package com.wilshion.oa.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wilshion.common.base.BaseUIActivity;
import com.wilshion.common.network.HttpCallBack;
import com.wilshion.common.utils.StringUtils;
import com.wilshion.common.widgets.UIEditText;
import com.wilshion.oa.BuildConfig;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.main.MainActivity;
import com.wilshion.oa.ui.bean.LoginRespBean;
import com.wilshion.oa.ui.bean.ResponseBean;
import com.wilshion.oa.ui.data.UserInfoUtil;
import com.wilshion.oa.ui.utils.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wilshion on 2018/7/25 15:08.
 * [description : 登陆]
 * [version : 1.0]
 */
public class LoginActivity extends BaseUIActivity implements View.OnClickListener {
    private UIEditText et_name;
    private UIEditText et_pwd;
    private TextView btn_login;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        et_name = findViewById(R.id.et_name);
        et_pwd = findViewById(R.id.et_pwd);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(this);
        if (BuildConfig.DEBUG) {
            et_name.setText("admin");
            et_pwd.setText("");
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_login:
                if (checkParams()) {
                    requestLogin();
                }
                break;
        }
    }

    private void requestLogin() {
        showWating("正在登录中");

        String name = et_name.getText().toString();
        String pwd = et_pwd.getText().toString();

        Map paramsDetail = new HashMap();
        paramsDetail.put("userName", name);
        paramsDetail.put("password", pwd);

        HttpUtil.requestPost(this, "signin", paramsDetail, new HttpCallBack<ResponseBean<LoginRespBean>>() {

            @Override
            public void onSuccess(int statusCode, String rawJsonResponse, ResponseBean<LoginRespBean> response) {
                closeDialog();
                if (response.isSuccess()) {
                    LoginRespBean loginRespBean = response.getDetail();
                    if (null != loginRespBean) {
                        UserInfoUtil.saveLoginResult(response.getDetail());
                        goToActivity(MainActivity.class);
                    } else {
                        showError(response.getResultNote());
                    }
                } else {
                    showError(response.getResultNote());
                }
            }

            @Override
            public void onFailure(int statusCode, String rawJsonResponse, ResponseBean<LoginRespBean> response) {
                showError(rawJsonResponse);
            }
        });
    }

    private boolean checkParams() {
        String name = et_name.getText().toString();
        String pwd = et_pwd.getText().toString();
        if (StringUtils.isEmpty(name)) {
            showToast("请输入用户名");
            return false;
        }
//        if (StringUtils.isEmpty(pwd)) {
//            showToast("请输入密码");
//            return false;
//        }
        return true;
    }
}
