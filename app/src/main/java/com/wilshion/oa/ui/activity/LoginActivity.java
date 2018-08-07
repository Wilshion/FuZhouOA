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
//              QueryAddressTask task = new QueryAddressTask();
//              task.execute();
                if (checkParams()) {
                    requestLogin();
                }
                break;
        }
    }

    private void requestLogin() {
        showWating("正在登录中");

        String name = et_name.getText().toString();
        final String pwd = et_pwd.getText().toString();

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
                        UserInfoUtil.saveUserPwd(pwd);
                        goToActivity(MainActivity.class);
                        finish();
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


//    public String getRemoteInfo(String phoneSec) throws Exception {
//        String WSDL_URI = "http://210.76.72.52:8080/gdcldpfCitizen/services/NewDisableCitizenService?wsdl";//wsdl 的uri
//        String namespace = "http://nciic.com.cn";//namespace
//        String methodName = "QueryDisableCitizenBatch";//要调用的方法名称
//
//        SoapObject request = new SoapObject(namespace, methodName);
//        // 设置需调用WebService接口需要传入的两个参数mobileCode、userId
//        request.addProperty("ad", AuthenticationData.newInstance());
//        request.addProperty("startTime", "2017-07-07 00:00:00");
//
//        //创建SoapSerializationEnvelope 对象，同时指定soap版本号(之前在wsdl中看到的)
//        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER12);
//        envelope.bodyOut = request;//由于是发送请求，所以是设置bodyOut
//        envelope.dotNet = true;//由于是.net开发的webservice，所以这里要设置为true
//
//        HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_URI);
//        httpTransportSE.call(null, envelope);//调用
//
//        // 获取返回的数据
//        SoapObject object = (SoapObject) envelope.bodyIn;
//        // 获取返回的结果
//        String result = object.getProperty(0).toString();
//        Log.d("debug", result);
//        return result;
//
//    }
//
//    class QueryAddressTask extends AsyncTask<String, Integer, String> {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            showWating(".......");
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//            // 查询手机号码（段）信息*/
//            String result = "";
//            try {
//                 result = getRemoteInfo(params[0]);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            //将结果返回给onPostExecute方法
//            return result;
//        }
//
//        @Override
//        //此方法可以在主线程改变UI
//        protected void onPostExecute(String result) {
//            closeDialog();
//            // 将WebService返回的结果显示在TextView中
//            LogUtils.e(result);
//        }
//    }
}
