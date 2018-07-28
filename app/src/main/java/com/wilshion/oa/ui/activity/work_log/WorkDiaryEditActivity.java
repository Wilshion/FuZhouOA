package com.wilshion.oa.ui.activity.work_log;

import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.base.BaseTitleBarActivity;
import com.wilshion.oa.ui.bean.DiaryBean;

/**
 * Created by Wilshion on 2018/7/28 09:45.
 * [description : 编辑、新建工作日志]
 * [version : 1.0]
 */
public class WorkDiaryEditActivity extends BaseTitleBarActivity {
    private DiaryBean mDiaryBean;
    @Override
    protected void setTitleBar() {
        setTitle("今日日志");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_work_diary_edit;
    }
}
