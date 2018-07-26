package com.wilshion.oa.ui.activity.news;

import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.BaseTitleBarActivity;

/**
 * Created by Wilshion on 2018/7/26 09:48.
 * [description : 新闻列表]
 * [version : 1.0]
 */
public class NewsListActivity extends BaseTitleBarActivity {
    @Override
    protected void setTitleBar() {
        setTitle("最新新闻");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_news_list;
    }
}
