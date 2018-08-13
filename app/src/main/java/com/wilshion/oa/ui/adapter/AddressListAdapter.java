package com.wilshion.oa.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.bean.AddressBean;

/**
 * Created by lyy on 18/7/28.
 */

public class AddressListAdapter extends BaseQuickAdapter<AddressBean, BaseViewHolder> {


    public AddressListAdapter(int layoutResId) {
        super(layoutResId);
    }


    @Override
    protected void convert(BaseViewHolder helper, AddressBean item) {

        helper.setText(R.id.id_work_name, item.getPSN_NAME())
                .setText(R.id.id_work_danwei, item.getDEPT_NAME())
                .setText(R.id.id_work_zhiwu, item.getMINISTRATION())
                .setText(R.id.id_work_birth, item.getBIRTHDAY())
                .setText(R.id.id_work_phone, item.getTEL_NO_DEPT())
                .setText(R.id.id_work_family_phone, item.getTEL_NO_HOME())
                .setText(R.id.id_work_moblie_phone, item.getMOBIL_NO())
                .setText(R.id.id_work_email, item.getEMAIL());
    }
}
