package com.wilshion.oa.ui.activity.ip_reset;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.wilshion.common.utils.EmptyUtils;
import com.wilshion.oa.BuildConfig;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseTitleBarActivity;
import com.wilshion.oa.ui.constant.ConstantUrl;

/**
 * Created by Wilshion on 2018/8/14 19:49.
 * [description : 服务器地址设置]
 * [version : 1.0]
 */
public class IpSettingActivity extends BaseTitleBarActivity implements View.OnClickListener {
    private EditText et_content;

    @Override
    protected void setTitleBar() {
        setTitle("服务器设置");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ip_setting;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        et_content = findViewById(R.id.et_content);
        if (BuildConfig.DEBUG)
            et_content.setText("http://mzoa.fujiants.com/");
        findViewById(R.id.tv_submit).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.tv_submit:
                String httpAddr = et_content.getText().toString();
                if (EmptyUtils.isEmpty(httpAddr)) {
                    showToast("内容不可为空");
                    return;
                }
                showToast("服务器地址设置成功");
                ConstantUrl.setUrlPrefix(httpAddr);
                finishWithDelay(500);
                break;
        }
    }
}
