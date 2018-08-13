package com.wilshion.oa.ui.dialog;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.wilshion.oa.R;

/**
 * Created by Wilshion on 2018/8/12 23:16.
 * [description : ]
 * [version : 1.0]
 */
public class AddressSearchWindow extends PopupWindow implements View.OnClickListener {
    private Activity mContext;
    private EditText mNameEt;
    private EditText mDanWeiEt;
    private OnAddressListSearchListener mOnAddressListSearchListener;

    public AddressSearchWindow(Activity context) {
        mContext = context;
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        View contentView = LayoutInflater.from(context).inflate(R.layout.dialog_address_list_search, null);
        setContentView(contentView);

        initAttrs();
    }

    private void initAttrs() {
        mNameEt = getContentView().findViewById(R.id.et_name);
        mDanWeiEt = getContentView().findViewById(R.id.et_danwei);
        getContentView().findViewById(R.id.tv_search).setOnClickListener(this);

    }

    @Override
    public void showAsDropDown(View anchor) {
        ViewGroup root = (ViewGroup) mContext.getWindow().getDecorView().getRootView();
        applyDim(root, 0.5f);
        super.showAsDropDown(anchor);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        ViewGroup root = (ViewGroup) mContext.getWindow().getDecorView().getRootView();
        clearDim(root);
    }

    public static void applyDim(@NonNull ViewGroup parent, float dimAmount) {
        Drawable dim = new ColorDrawable(Color.BLACK);
        dim.setBounds(0, 0, parent.getWidth(), parent.getHeight());
        dim.setAlpha((int) (255 * dimAmount));
        ViewGroupOverlay overlay = parent.getOverlay();
        overlay.add(dim);
    }

    public static void clearDim(@NonNull ViewGroup parent) {
        ViewGroupOverlay overlay = parent.getOverlay();
        overlay.clear();
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId) {
            case R.id.tv_search: {
                if (mOnAddressListSearchListener != null) {
                    String psnName = mNameEt.getText().toString();
                    String deptName = mDanWeiEt.getText().toString();
                    mOnAddressListSearchListener.onSearchListener(psnName, deptName);
                    dismiss();
                }
            }
            break;
        }
    }

    public void setOnAddressListSearchListener(OnAddressListSearchListener onAddressListSearchListener) {
        mOnAddressListSearchListener = onAddressListSearchListener;
    }

   

    public interface OnAddressListSearchListener {
        void onSearchListener(String psnName, String deptName);
    }
}
