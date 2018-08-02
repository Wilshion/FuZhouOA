package com.wilshion.common.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by Wilshion on 2018/7/25 15:02.
 * [description : activity 基类，不负责业务，扩展一些基本方法]
 * [version : 1.0]
 */
public class BaseActivity extends AppCompatActivity implements BaseUIDao {
    protected String TAG = getClass().getSimpleName();
    private BaseUIDaoImpl mBaseUIDao;

    public BaseUIDaoImpl getBaseUIDao() {
        if (null == mBaseUIDao){
            synchronized (BaseActivity.class){
                if (null == mBaseUIDao){
                    mBaseUIDao = new BaseUIDaoImpl(this);
                }
            }
        }
        return mBaseUIDao;
    }
    
    protected void goToActivity(Class<? extends Activity> clazz){
        Intent intent = new Intent(this,clazz);
        startActivity(intent);
    }
    
    protected void finishWithDelay(long delayed){
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },delayed);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showLogD(Object info) {
        getBaseUIDao().showLogD(info);
    }

    @Override
    public void showLogE(Object info) {
        getBaseUIDao().showLogE(info);
    }

    @Override
    public void showToast(String info) {
        getBaseUIDao().showToast(info);
    }

    @Override
    public void showToast(String info, long duration) {
        getBaseUIDao().showToast(info, duration);
    }

    @Override
    public void showWating(String info) {
        getBaseUIDao().showWating(info);
    }

    @Override
    public void showWating(String info, boolean isDimBackground) {
        getBaseUIDao().showWating(info, isDimBackground);
    }

    @Override
    public void showInfo(String info) {
        getBaseUIDao().showInfo(info);
    }

    @Override
    public void showInfo(String info, long delayTime) {
        getBaseUIDao().showInfo(info, delayTime);
    }

    @Override
    public void showSucceed(String info) {
        getBaseUIDao().showSucceed(info);
    }

    @Override
    public void showSucceed(String info, long delayTime) {
        getBaseUIDao().showSucceed(info, delayTime);
    }

    @Override
    public void showError(String info) {
        getBaseUIDao().showError(info);
    }

    @Override
    public void closeDialog() {
        getBaseUIDao().closeDialog();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
