package com.wilshion.oa.ui.activity.work_flow;

import android.os.Bundle;
import android.widget.TextView;

import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseTitleBarActivity;
import com.wilshion.oa.ui.bean.WorkFlowBean;

/**
 * Created by Wilshion on 2018/7/26 21:13.
 * [description : 转交]
 * [version : 1.0]
 */
public class WorkFlowDeliverActivity extends BaseTitleBarActivity {
    private TextView tv_name;
    private TextView tv_user;
    private TextView tv_time;
    private WorkFlowBean mWorkFlowBean;
    
    @Override
    protected void setTitleBar() {
        setTitle("工作转交");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_work_flow_deliver;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        mWorkFlowBean = getIntent().getParcelableExtra("data");
        if (mWorkFlowBean == null){
            showToast("数据获取失败");
            finish();
            return;
        }

        tv_name = findViewById(R.id.tv_name);
        tv_user = findViewById(R.id.tv_user);
        tv_time = findViewById(R.id.tv_time);

        tv_name.setText(mWorkFlowBean.getRUN_NAME());
        tv_user.setText(mWorkFlowBean.getPRCS_NAME());
    }
}
