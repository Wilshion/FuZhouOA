package com.wilshion.oa.ui.activity.schedule;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.wilshion.common.network.HttpCallBack;
import com.wilshion.common.utils.StringUtils;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseTitleBarActivity;
import com.wilshion.oa.ui.bean.ResponseBean;
import com.wilshion.oa.ui.utils.HttpUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Wilshion on 2018/7/26 15:44.
 * [description : 新建日程]
 * [version : 1.0]
 */
public class NewScheduleActivity extends BaseTitleBarActivity implements View.OnClickListener {
    private TextView tv_select_type;
    private TextView tv_select_level;
    private EditText et_start_time;
    private EditText et_end_time;
    private EditText et_content;
    private OptionsPickerView mTypeSelector;
    private ArrayList<String> mTypeArr;
    private ArrayList<String> mLevelArr;

    /**
     * 当前展示的是 选择类型的选择框吗
     */
    private boolean isShowType;
    /**
     * 选择的事务类型，1，工作事务，2 是个人事务
     */
    private int mSelectedType = 1;

    /**
     * 级别 0未指定  1重要 /紧急  2重要/不紧急  3不重要/紧急  4不重要/不紧急
     */
    private int mSelectedLevel = 0;

    @Override
    protected void setTitleBar() {
        setTitle("新建日程");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_schedule;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        tv_select_type = findViewById(R.id.tv_select_type);
        tv_select_level = findViewById(R.id.tv_select_level);
        et_start_time = findViewById(R.id.et_start_time);
        et_end_time = findViewById(R.id.et_end_time);
        et_content = findViewById(R.id.et_content);

        tv_select_type.setOnClickListener(this);
        tv_select_level.setOnClickListener(this);
        findViewById(R.id.tv_send).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.tv_select_type:
                isShowType = true;
                showSelectView();
                break;
            case R.id.tv_select_level:
                isShowType = false;
                showSelectView();
                break;
            case R.id.tv_send:
                if (checkParams())
                    requestSend();
                break;
        }
    }


    /**
     * 提交订单前判读参数
     *
     * @return
     */
    private boolean checkParams() {
        if (mSelectedType <= 0) {
            showToast("请选择日程类型");
            return false;
        }
        String startTime = et_start_time.getText().toString();
        if (StringUtils.isEmpty(startTime)) {
            showToast("请填写开始时间");
            return false;
        }
        if (!checkTime(startTime)) {
            showToast("时间格式不对，应形如 09:35");
            return false;
        }
        String endTime = et_end_time.getText().toString();
        if (StringUtils.isEmpty(endTime)) {
            showToast("请填写结束时间");
            return false;
        }
        if (!checkTime(endTime)) {
            showToast("时间格式不对，应形如 09:35");
            return false;
        }

        return true;
    }

    /**
     * 校验时间格式 必须我"09:35"
     *
     * @param time
     * @return
     */
    private boolean checkTime(String time) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        format.setLenient(false);
        try {
            format.parse(time);
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void requestSend() {
        showWating("正在提交中...");
        String startTime = et_start_time.getText().toString();
        String endTime = et_end_time.getText().toString();
        String content = et_content.getText().toString();
        HashMap<String, String> params = new HashMap<>();
        params.put("calType", mSelectedType + "");
        params.put("calLevel", mSelectedLevel + "");
        params.put("calTime", startTime);
        params.put("endTime", endTime);
        params.put("content", content);

        HttpUtil.requestPost(this, "calendarNew", params, new HttpCallBack<ResponseBean>() {
            @Override
            public void onSuccess(int statusCode, String rawJsonResponse, ResponseBean response) {
                if (response.isSuccess()) {
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


    /**
     * 弹出 选择器
     */
    private void showSelectView() {
        if (mTypeSelector == null) {
            mTypeSelector = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    if (isShowType) {
                        mSelectedType = options1 + 1;
                        tv_select_type.setText(getTypeArr().get(options1));
                    } else {
                        mSelectedLevel = options1;
                        tv_select_level.setText(getLevelArr().get(options1));
                    }
                }
            }).build();
        }

        mTypeSelector.setNPicker(isShowType ? getTypeArr() : getLevelArr(), null, null);
        mTypeSelector.show(isShowType ? tv_select_type : tv_select_level);
    }

    public ArrayList<String> getTypeArr() {
        if (mTypeArr == null) {
            mTypeArr = new ArrayList();
            mTypeArr.add("工作事务");
            mTypeArr.add("个人事务");
        }
        return mTypeArr;
    }

    public ArrayList<String> getLevelArr() {
        if (mLevelArr == null) {
            mLevelArr = new ArrayList();
            mLevelArr.add("未指定");
            mLevelArr.add("重要 /紧急");
            mLevelArr.add("重要/不紧急");
            mLevelArr.add("不重要/紧急");
            mLevelArr.add("不重要/不紧急");
        }
        return mLevelArr;
    }
}
