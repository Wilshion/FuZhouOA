package com.wilshion.oa.ui.activity.base;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wilshion.common.base.BaseUIActivity;
import com.wilshion.oa.R;

/**
 * Created by Wilshion on 2018/7/25 21:29.
 * [description : ]
 * [version : 1.0]
 */
public abstract class BaseTitleBarActivity extends BaseUIActivity  {
    private TextView tv_title;
    private TextView tv_right;
    private RelativeLayout rl_right;
    private ImageView iv_right;
    private ImageView iv_left;
    
    
    
    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        tv_title = findViewById(R.id.tv_title);
        rl_right = findViewById(R.id.rl_right);
        tv_right = findViewById(R.id.tv_right);
        iv_right = findViewById(R.id.iv_right);
        iv_left =  findViewById(R.id.iv_left);
        if (null ==iv_left ){
            throw new NullPointerException("请确保布局中有part_title_bar");
        }
        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLeftClick();
            }
        });
        rl_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRightClick();
            }
        });
        setTitleBar();
    }
    
    protected abstract void setTitleBar();

    protected void onLeftClick() {
        onBackPressed();
    }
    
    protected void onRightClick(){
        
    }

    protected void setTitle(String title){
        if (null == tv_title){
            throw new NullPointerException("调用此方法请确保布局中有tv_title");
        }
        tv_title.setText(title);
    }

    protected void setRightText(String text){
      
        if (null == tv_right){
            throw new NullPointerException("调用此方法请确保布局中有tv_right");
        }
        tv_right.setText(text);
    }

    protected void setRightImageRes(int res){
        
        if (null == iv_right){
            throw new NullPointerException("调用此方法请确保布局中有iv_right");
        }
        iv_right.setImageResource(res);
    }

   
}
