package com.wilshion.common.base;

import android.content.Context;

/**
 * Created by Wilshion on 2018/7/25 15:00.
 * [description : ]
 * [version : 1.0]
 */
public interface BaseUIDao {
    Context getContext();

    void showLogD(Object info);

    /**
     * print log with info message
     *
     * @param info message
     */
    void showLogE(Object info);

    /**
     * show a toast with default duration
     * @param info
     */
    void showToast(String info);

    /**
     * show a toast with a specific duration
     * @param info
     * @param duration
     */
    void showToast(String info, long duration);

    /**
     * show a dialog to hint user while loading sth.
     * @param info
     */
    void showWating(String info);

    /**
     *
     * @param info
     * @param isDimBackground
     */
    void showWating(String info,boolean isDimBackground);

    /**
     * show a warning dialog to hint user  .
     * @param info
     */
    void showInfo(String info);

    /**
     *
     * @param info
     * @param delayTime
     */
    void showInfo(String info, long delayTime);

    /**
     * show success dialog
     * @param info
     */
    void showSucceed(String info);

    /**
     *
     * @param info
     * @param delayTime
     */
    void showSucceed(String info, long delayTime);

    /**
     * show error dialog while sth was wrong
     * @param info
     */
    void showError(String info);

    /**
     * close the dialog
     */
    void closeDialog();
}
