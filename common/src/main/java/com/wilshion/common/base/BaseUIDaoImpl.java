package com.wilshion.common.base;

import android.content.Context;
import android.os.Handler;
import android.widget.ImageView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.wilshion.common.R;
import com.wilshion.common.utils.LogUtils;
import com.wilshion.common.utils.ToastUtils;

/**
 * Created by Wilshion on 2018/7/25 15:02.
 * [description : ]
 * [version : 1.0]
 */
public class BaseUIDaoImpl implements BaseUIDao {
    private final long DIALOG_SHOW_TIME = 1500;
    private Context mContext;
    private KProgressHUD mHUD;

    public KProgressHUD getHUD() {
        if (mHUD == null) {
            mHUD = KProgressHUD.create(mContext).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setDimAmount(0.5f);
        }
        return mHUD;
    }

    public BaseUIDaoImpl(Context context) {
        mContext = context;
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public void showLogD(Object info) {
        LogUtils.d(info);
    }

    @Override
    public void showLogE(Object info) {
        LogUtils.e(info);
    }

    @Override
    public void showToast(String info) {
        ToastUtils.showShort(info);
    }

    @Override
    public void showToast(String info, long duration) {
        ToastUtils.showDuration(info, (int) (duration / 1000));
    }

    @Override
    public void showWating(String info) {
        showWating(info, false);
    }

    @Override
    public void showWating(String info, boolean isDimBackground) {
        getHUD().setLabel(info).setCancellable(false).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
        if (!mHUD.isShowing())
            mHUD.show();
    }

    @Override
    public void showInfo(String info) {
        showInfo(info, getDialogShowTime());
    }

    @Override
    public void showInfo(String info, long delayTime) {
        closeDialog();
        ImageView imageView = new ImageView(mContext);
        imageView.setBackgroundResource(R.drawable.ic_dialog_warn);
        final KProgressHUD hud1 = KProgressHUD.create(mContext).setCustomView(imageView).setLabel(info);
        hud1.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hud1.dismiss();
            }
        }, delayTime);
    }

    @Override
    public void showSucceed(String info) {
        showSucceed(info, getDialogShowTime());
    }

    @Override
    public void showSucceed(String info, long delayTime) {
        closeDialog();
        ImageView imageView = new ImageView(mContext);
        imageView.setBackgroundResource(R.drawable.ic_dialog_success);
        final KProgressHUD hud1 = KProgressHUD.create(mContext).setCustomView(imageView).setLabel(info);
        hud1.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hud1.dismiss();
            }
        }, delayTime);
    }

    @Override
    public void showError(String info) {
        closeDialog();
        ImageView imageView = new ImageView(mContext);
        imageView.setBackgroundResource(R.drawable.ic_dialog_error);
        final KProgressHUD hud1 = KProgressHUD.create(mContext).setCustomView(imageView).setLabel(info);
        hud1.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hud1.dismiss();
            }
        }, getDialogShowTime());
    }

    @Override
    public void closeDialog() {
        if (null != mHUD && mHUD.isShowing())
            mHUD.dismiss();
    }

    protected long getDialogShowTime() {
        return DIALOG_SHOW_TIME;
    }
}
