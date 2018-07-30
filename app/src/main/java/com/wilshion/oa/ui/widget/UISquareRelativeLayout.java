package com.wilshion.oa.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by Wilshion on 2018/7/30 10:51.
 * [description : ]
 * [version : 1.0]
 */
public class UISquareRelativeLayout extends LinearLayout {
    public UISquareRelativeLayout(Context context) {
        super(context);
    }

    public UISquareRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UISquareRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec),
                getDefaultSize(0, heightMeasureSpec));

        int childWidthSize = getMeasuredWidth();
        // 高度和宽度一样
        heightMeasureSpec = widthMeasureSpec = MeasureSpec.makeMeasureSpec(
                childWidthSize, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
