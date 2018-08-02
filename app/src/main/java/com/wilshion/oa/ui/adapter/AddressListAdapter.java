package com.wilshion.oa.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wilshion.oa.R;
import com.wilshion.oa.ui.bean.AddressBean;

import java.util.List;

/**
 * Created by lyy on 18/7/28.
 */

public class AddressListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<AddressBean> mList;
    private int mLayoutId;

    public AddressListAdapter (Context context, List<AddressBean> list, int layoutId){
        mContext = context;
        mList = list;
        mLayoutId = layoutId;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mLayoutId,parent,false);
        AddressHolder addressHolder = new AddressHolder(view);
        return addressHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AddressHolder aHolder = (AddressHolder) holder;

        AddressBean bean = mList.get(position);

        aHolder.tv_work_name.setText(bean.getPSN_NAME());
        aHolder.tv_work_danwei.setText(bean.getGROUP_NAME());
        aHolder.tv_work_zhiwu.setText(bean.getMINISTRATION());
        aHolder.tv_work_phone.setText(bean.getTEL_NO_DEPT());
        aHolder.tv_work_famil_phone.setText(bean.getTEL_NO_HOME());
        aHolder.tv_work_birth.setText(bean.getBIRTHDAY());
        aHolder.tv_work_mobile_phone.setText(bean.getMOBIL_NO());
        aHolder.tv_work_email.setText(bean.getEMAIL());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class AddressHolder extends RecyclerView.ViewHolder{

        TextView tv_work_name;
        TextView tv_work_danwei;
        TextView tv_work_zhiwu;
        TextView tv_work_birth;
        TextView tv_work_phone;
        TextView tv_work_famil_phone;
        TextView tv_work_mobile_phone;
        TextView tv_work_email;


        public AddressHolder(View itemView) {
            super(itemView);

            tv_work_name = itemView.findViewById(R.id.id_work_name);
            tv_work_danwei = itemView.findViewById(R.id.id_work_danwei);
            tv_work_zhiwu = itemView.findViewById(R.id.id_work_zhiwu);
            tv_work_birth = itemView.findViewById(R.id.id_work_birth);
            tv_work_phone = itemView.findViewById(R.id.id_work_phone);
            tv_work_famil_phone = itemView.findViewById(R.id.id_work_family_phone);
            tv_work_mobile_phone = itemView.findViewById(R.id.id_work_moblie_phone);
            tv_work_email = itemView.findViewById(R.id.id_work_email);

        }
    }
}
