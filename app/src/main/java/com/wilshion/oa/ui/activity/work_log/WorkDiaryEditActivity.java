package com.wilshion.oa.ui.activity.work_log;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.wilshion.common.network.HttpCallBack;
import com.wilshion.common.utils.EmptyUtils;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseTitleBarActivity;
import com.wilshion.oa.ui.bean.DiaryBean;
import com.wilshion.oa.ui.bean.ResponseBean;
import com.wilshion.oa.ui.constant.Constant;
import com.wilshion.oa.ui.utils.HttpUtil;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Wilshion on 2018/7/28 09:45.
 * [description : 编辑、新建工作日志]
 * [version : 1.0]
 */
public class WorkDiaryEditActivity extends BaseTitleBarActivity implements View.OnClickListener {
    private EditText et_subject;
    private EditText et_content;
    private TextView tv_type;
    private TextView tv_submit;

    private OptionsPickerView mTypeSelector;
    private ArrayList<String> mTypeArr;

    private DiaryBean mDiaryBean;
    private int mDiaryType;

    @Override
    protected void setTitleBar() {
        setTitle("今日日志");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_work_diary_edit;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mDiaryBean = getIntent().getParcelableExtra(Constant.INTENT_PARAM_DATA);
        tv_type = findViewById(R.id.tv_type);
        et_subject = findViewById(R.id.et_subject);
        et_content = findViewById(R.id.et_content);
        tv_submit = findViewById(R.id.tv_submit);

        if (mDiaryBean != null) {
            mDiaryType = mDiaryBean.getDIA_TYPE();
            tv_type.setText(getTypeArr().get(mDiaryType - 1));
            et_subject.setText(mDiaryBean.getSUBJECT());
            et_content.setText(mDiaryBean.getCONTENT());
        }

        tv_type.setOnClickListener(this);
        tv_submit.setOnClickListener(this);
    }

    private void showSelectView() {
        if (mTypeSelector == null) {
            mTypeSelector = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    tv_type.setText(getTypeArr().get(options1));
                    mDiaryType = options1 + 1;
                }
            }).build();
            mTypeSelector.setNPicker(getTypeArr(), null, null);
        }
        mTypeSelector.show();
    }

    public ArrayList<String> getTypeArr() {
        if (mTypeArr == null) {
            mTypeArr = new ArrayList();
            mTypeArr.add("工作日志");
            mTypeArr.add("个人日志");
        }
        return mTypeArr;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.tv_type:
                showSelectView();
                break;
            case R.id.tv_submit:
                if (checkParams()) {
                    requestSubmit();
                }
                break;
        }
    }

    private void requestSubmit() {
        showWating("正在发送中...");
        String subject = et_subject.getText().toString();
        String content = et_content.getText().toString();

        // 是否是编辑，true 是编辑，false 是新建
        boolean isEdit = mDiaryBean != null;

        HashMap<String, String> params = new HashMap<>();
        params.put("diaType", mDiaryType + "");
        params.put("subject", subject);
        params.put("content", content);
        params.put("attachmentId", "");
        params.put("attachmentName", "");
        if (isEdit) {
            // 根据接口请求参数，编辑的时候多一个参数
            params.put("seqId", mDiaryBean.getSEQ_ID() + "");
        }
        String cmd = isEdit ? "diaryEdit" : "diaryNew";
        HttpUtil.requestPost(this, cmd, params, new HttpCallBack<ResponseBean>() {
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

    private boolean checkParams() {
        if (mDiaryType <= 0) {
            showToast("请选择日志类型");
            return false;
        }
        String subject = et_subject.getText().toString();
        if (EmptyUtils.isEmpty(subject)) {
            showToast("请输入日志主题");
            return false;
        }

        String content = et_content.getText().toString();
        if (EmptyUtils.isEmpty(content)) {
            showToast("请输入日志内容");
            return false;
        }
        return true;
    }
}
