package com.wilshion.oa.ui.widget;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Wilshion on 2018/8/14 20:10.
 * [description : ]
 * [version : 1.0]
 */
public class UINoScrollWebView extends UIWebView {
    public UINoScrollWebView(Context context) {
        super(context);
    }

    public UINoScrollWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public UINoScrollWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

   
    /**
     * 使WebView不可滚动 
     * */
    @Override
    public void scrollTo(int x, int y){
        super.scrollTo(0,0);
    }
}
