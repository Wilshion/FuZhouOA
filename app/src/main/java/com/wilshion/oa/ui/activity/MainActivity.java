package com.wilshion.oa.ui.activity;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wilshion.oa.R;
import com.wilshion.oa.ui.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseTitleBarActivity {
    private RecyclerView rv_content;
    private MainAdapter mAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        rv_content = findViewById(R.id.rv_content);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,4);
        rv_content.setLayoutManager(gridLayoutManager);
        rv_content.addItemDecoration(new ItemDecoration());
        mAdapter = new MainAdapter(R.layout.item_main_ac,createFakeData());
        rv_content.setAdapter(mAdapter);
    }

    @Override
    protected void setTitleBar() {
        setTitle(getString(R.string.app_title));
        setRightImageRes(R.drawable.ic_back); 
    }
    
    private List<HomeItemBean> createFakeData(){
        List<HomeItemBean> result = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            HomeItemBean itemBean = new HomeItemBean();
            itemBean.title = "测试" + i;
            result.add(itemBean);
        }
        return result;
    }

    /**
     * 排布模型
     */
    public static final class HomeItemBean{
        public int resId;
        public String title;
    }
    
    public static final class ItemDecoration extends RecyclerView.ItemDecoration{
        Paint mPaint;

        public ItemDecoration() {
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

                //根据这些点画条目的四周的线
                // 上面的横线
                c.drawLine(x, y, x + width, y, mPaint);

                if (i % 3 == 0) {
                    // 第四列
                    // 左边的竖线
//                    c.drawLine(x, y, x, y + height, mPaint);
                } else {
                    // 前三列
                    //右边的竖线
                    c.drawLine(x + width, y, x + width, y + height, mPaint);
                }

                // 底部的横线
                c.drawLine(x, y + height, x + width, y + height, mPaint);
            }
        }
    }
}
