package com.wilshion.oa.ui.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.bean.PersonListRespBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wilshion on 2018/7/27 08:48.
 * [description : ]
 * [version : 1.0]
 */
public class PersonListAdapter extends BaseQuickAdapter<PersonListRespBean.PersonBean, BaseViewHolder> {
    private boolean mIsSingleMode;

    public PersonListAdapter(int layoutResId, boolean isSingleMode) {
        super(layoutResId);
        mIsSingleMode = isSingleMode;
    }

    @Override
    protected void convert(BaseViewHolder helper, final PersonListRespBean.PersonBean item) {
        helper.setText(R.id.tv_name, item.getUSER_NAME());
        CheckBox checkBox = helper.getView(R.id.cb_check);
        checkBox.setVisibility(mIsSingleMode ? View.GONE : View.VISIBLE);
        if (!mIsSingleMode){
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    item.isSelected = b;
                }
            });
        }
    }
    
    public ArrayList<PersonListRespBean.PersonBean> getSelectedPersonList(){
        List<PersonListRespBean.PersonBean> allPerson = getData();
        ArrayList<PersonListRespBean.PersonBean> selectedPersonList = new ArrayList<>();
        for (PersonListRespBean.PersonBean personBean:allPerson) {
            if (personBean.isSelected){
                selectedPersonList.add(personBean);
            }
        }
        return selectedPersonList;
    }
}
