package com.wilshion.oa.ui.activity.email;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.wilshion.common.base.BaseUIActivity;
import com.wilshion.common.network.HttpCallBack;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.bean.EmailBean;
import com.wilshion.oa.ui.bean.ResponseBean;
import com.wilshion.oa.ui.constant.Constant;
import com.wilshion.oa.ui.utils.HttpUtil;

import java.util.HashMap;

/**
 * Created by Wilshion on 2018/7/26 09:45.
 * [description : 邮件详情]
 * [version : 1.0]
 */
public class EmailDetailActivity extends BaseUIActivity implements View.OnClickListener {
    private TextView tv_author_time;
    private TextView tv_content;

    private EmailBean mEmailBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_email_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        TextView tv_title = findViewById(R.id.tv_title);
        TextView tv_right1 = findViewById(R.id.tv_right1);
        TextView tv_right2 = findViewById(R.id.tv_right2);

        findViewById(R.id.iv_left).setOnClickListener(this);
        findViewById(R.id.tv_right1).setOnClickListener(this);
        findViewById(R.id.tv_right2).setOnClickListener(this);

        tv_title.setText("写邮件");
        tv_right1.setText("回复");
        tv_right2.setText("删除");


        mEmailBean = getIntent().getParcelableExtra(Constant.INTENT_PARAM_DATA);
        if (null == mEmailBean){
            showToast("传值出错");
            finish();
            return;
        }
        tv_author_time = findViewById(R.id.tv_author_time);
        tv_content = findViewById(R.id.tv_content);

        String author = mEmailBean.getUSER_NAME();
        String time = mEmailBean.getSEND_TIME();
        tv_author_time.setText(author +"  "+ time);
        tv_content.setText(mEmailBean.getCONTENT());
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.iv_left:
                onBackPressed();
                break;
            case R.id.tv_right1:
                goToSendEmail();
                break;
            case R.id.tv_right2:
                showConfirmDialog();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            setResult(RESULT_OK);
            finish();
        }
    }

    private void goToSendEmail() {
//        PersonListRespBean.PersonBean personBean = new PersonListRespBean.PersonBean();
//        personBean.setSEQ_ID(mEmailBean.getFROM_ID());
//        personBean.setUSER_NAME(mEmailBean.getUSER_NAME());

        Intent intent = new Intent(this, EmailSendActivity.class);
        intent.putExtra(Constant.INTENT_PARAM_DATA, mEmailBean);
        startActivityForResult(intent,1);
    }

    private void showConfirmDialog() {
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title("温馨提示")
                .content("确认删除该邮件吗？")
                .positiveText("删除")
                .negativeText("取消")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        requestDelete();
                    }
                })
                .show();
    }

    private void requestDelete() {
        showWating("正在删除中...");
        HashMap<String, Integer> params = new HashMap<>();
        params.put("emailId", mEmailBean.getSEQ_ID());
        HttpUtil.requestPost(this, "deleteEmail", params, new HttpCallBack<ResponseBean>() {
            @Override
            public void onSuccess(int statusCode, String rawJsonResponse, ResponseBean response) {
                if (response.isSuccess()) {
                    closeDialog();
                    showToast(response.getResultNote());
                    setResult(RESULT_OK);
                    finish();
                } else {
                    showError(response.getResultNote());
                }
            }

            @Override
            public void onFailure(int statusCode, String rawJsonResponse, ResponseBean response) {
                showError(rawJsonResponse);
            }
        });

    }
}
