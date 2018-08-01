package com.wilshion.oa.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wilshion.common.network.HttpCallBack;

import java.util.List;

/**
 * Created by lyy on 18/7/28.
 */

public class AddressListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<String> mList;
    private int mLayoutId;

    public AddressListAdapter (Context context, List<String> list, int layoutId){

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

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class AddressHolder extends RecyclerView.ViewHolder{

        public AddressHolder(View itemView) {
            super(itemView);
        }
    }
}
