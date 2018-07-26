package com.wilshion.oa.ui.activity.work_flow;

import android.os.Bundle;
import android.widget.TextView;

import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseTitleBarActivity;
import com.wilshion.oa.ui.bean.WorkFlowBean;

/**
 * Created by Wilshion on 2018/7/26 20:50.
 * [description : 表单]
 * [version : 1.0]
 */
public class WorkFlowFormActivity extends BaseTitleBarActivity {
    private TextView tv_name;
    private TextView tv_flow_num;
    private TextView tv_time;
    private WorkFlowBean mWorkFlowBean;
    
    @Override
    protected void setTitleBar() {
        setTitle("表单");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_work_flow_form;
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
        tv_flow_num = findViewById(R.id.tv_flow_num);
        tv_time = findViewById(R.id.tv_time);
        
        tv_name.setText(mWorkFlowBean.getFLOW_NAME());
        tv_flow_num.setText(mWorkFlowBean.getFLOW_ID()+"");
        tv_time.setText(mWorkFlowBean.getRUN_NAME());
    }
}
