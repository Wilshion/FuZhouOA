package com.wilshion.oa.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by lyy on 18/7/28.
 */

public class ZipCodeAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<String> mList;
    private int mLayoutId;

    public ZipCodeAdapter(Context context,List<String> list,int layoutId){
        mContext = context;
        mList = list;
        mLayoutId = layoutId;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mLayoutId,parent,false);
        ZipCodeHolder zipCodeHolder = new ZipCodeHolder(view);
        return zipCodeHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ZipCodeHolder extends RecyclerView.ViewHolder{
        public ZipCodeHolder(View itemView) {
            super(itemView);
        }
    }
}
