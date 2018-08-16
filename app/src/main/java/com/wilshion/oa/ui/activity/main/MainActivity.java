package com.wilshion.oa.ui.activity.main;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.luck.picture.lib.permissions.RxPermissions;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.wilshion.oa.R;
import com.wilshion.oa.ui.activity.LoginActivity;
import com.wilshion.oa.ui.activity.base.BaseTitleBarActivity;
import com.wilshion.oa.ui.activity.main.HomeDataSource.HomeItemBean;
import com.wilshion.oa.ui.adapter.MainAdapter;
import com.wilshion.oa.ui.data.UserInfoUtil;

import java.util.List;

import io.reactivex.functions.Consumer;


public class MainActivity extends BaseTitleBarActivity implements BaseQuickAdapter.OnItemClickListener {
    private RecyclerView rv_content;
    private MainAdapter mAdapter;
    private List<HomeItemBean> mData;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mData = HomeDataSource.getHomeData();
        rv_content = findViewById(R.id.rv_content);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        rv_content.setLayoutManager(gridLayoutManager);
//        rv_content.addItemDecoration(new HomeItemLine());
        mAdapter = new MainAdapter(R.layout.item_main_ac, mData);
        rv_content.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
       
        requestPermissions();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }


    @Override
    protected void setTitleBar() {
        getLeft().setVisibility(View.GONE);
        setTitle(getString(R.string.app_title));
        setRightImageRes(R.drawable.ic_logout);
    }

    @Override
    protected void onRightClick() {
        super.onRightClick();
        UserInfoUtil.clear();
        goToActivity(LoginActivity.class);
        finish();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        HomeItemBean homeItemBean = mData.get(position);
        goToActivity(homeItemBean.targetClazz);
    }


    private void requestPermissions() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.CAMERA,
                        Manifest.permission.READ_PHONE_STATE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        if (granted) {
                            // All requested permissions are granted
                            showLogD("同意权限");
                        } else {
                            // At least one permission is denied
                            showLogD("拒绝权限");
                        }
                    }
                });
    }

    private void setUMengAlias() {
        String alias = UserInfoUtil.getCurUserId() + "";
        PushAgent.getInstance(this).setAlias(alias, "user_id", new UTrack.ICallBack() {
            @Override
            public void onMessage(boolean b, String s) {
                showLogD(b + "  " + s);
            }
        });
    }
}
