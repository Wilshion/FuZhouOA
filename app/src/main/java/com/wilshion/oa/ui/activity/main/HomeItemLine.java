package com.wilshion.oa.ui.activity.main;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wilshion.oa.R;

/**
 * Created by Wilshion on 2018/7/26 10:31.
 * [description : 首页九宫格分割线]
 * [version : 1.0]
 */
public class HomeItemLine extends RecyclerView.ItemDecoration {
    Paint mPaint;

    public HomeItemLine() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(5);
//            mPaint.setColor(parent.getContext().getResources().getColor(R.color.gray));
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        //先初始化一个Paint来简单指定一下Canvas的颜色，就黑的吧！
//            Paint paint = new Paint();
        mPaint.setColor(parent.getContext().getResources().getColor(R.color.gray));

        //获得RecyclerView中总条目数量
        int childCount = parent.getChildCount();

        //遍历一下
        for (int i = 0; i < childCount; i++) {
            //获得子View，也就是一个条目的View，准备给他画上边框
            View childView = parent.getChildAt(i);


            //先获得子View的长宽，以及在屏幕上的位置，方便我们得到边框的具体坐标
            float x = childView.getX();
            float y = childView.getY();
            int width = childView.getWidth();
            int height = childView.getHeight();

            float wSpace = width * 0.1f;
            float hSpace = height * 0.1f;

            float startX = x + wSpace;
            float endX = x + width - wSpace;
            float startY = y + hSpace;
            float endY = y + height - hSpace;

            //根据这些点画条目的四周的线
//            if (i > 3) {
//                // 上面的横线,从第二排开始画
////                    c.drawLine(x, y, x + width, y, mPaint);
//                c.drawLine(startX, y, endX, y, mPaint);
//            }

            if (i % 4 != 3) {
                //标识前三列，则绘制右边的竖线
//                    c.drawLine(x + width, y, x + width, y + height, mPaint);
                c.drawLine(x + width, startY, x + width, endY, mPaint);
            }

            if (i < 8) {
                // 上面的横线,从第二排开始画
//                    c.drawLine(x, y, x + width, y, mPaint);
                c.drawLine(startX, y , endX, y, mPaint);
            }
        }
    }
}
