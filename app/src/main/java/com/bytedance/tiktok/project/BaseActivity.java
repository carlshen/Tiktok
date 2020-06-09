package com.bytedance.tiktok.project;

import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bytedance.tiktok.application.App;
import com.xiangxue.common.data.manager.CustomNetworkStateManager;
import com.xiangxue.common.utils.AdaptScreenUtils;
import com.xiangxue.common.utils.BarUtils;
import com.xiangxue.common.utils.ScreenUtils;

public abstract class BaseActivity extends AppCompatActivity {

    // 同学们这个是 贯穿整个项目的
    protected ProjectViewModel mSharedViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 给工具类初始化
        // BarUtils.setStatusBarColor(this, Color.RED);
        // BarUtils.setStatusBarLightMode(this, true);
        BarUtils.setStatusBarVisibility(this, false); // 隐藏 状态栏

        mSharedViewModel = getAppViewModelProvider().get(ProjectViewModel.class);

        // 准备：lifecycle
        // 意味着 BaseActivity被观察者  -----> NetworkStateManager观察者（一双眼睛 盯着看 onResume/onPause）
        // BaseActivity就是被观察者 ---> NetworkStateManager.getInstance()
        getLifecycle().addObserver(CustomNetworkStateManager.getInstance());

        // 同学们这是初始化工作函数
        init();
    }

    /**
     * 让自己的孩子，可以完成此动作
     */
    protected abstract void init();

    /**
     * 方便调试Debug
     * @return
     */
    public boolean isDebug() {
        return getApplicationContext().getApplicationInfo() != null &&
                (getApplicationContext().getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

    @Override
    public Resources getResources() {
        if (ScreenUtils.isPortrait()) {
            return AdaptScreenUtils.adaptWidth(super.getResources(), 360);
        } else {
            return AdaptScreenUtils.adaptHeight(super.getResources(), 640);
        }
    }

    /**
     * 暴露给自己的还是 Toast
     * @param text
     */
    public void showLongToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    /**
     * 暴露给自己的还是 Toast
     * @param text
     */
    public void showShortToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 给此BaseActivity 得到ViewModelProvider
     * application
     * @return
     */
    private ViewModelProvider getAppViewModelProvider() {
        return ((App) getApplicationContext()).getAppViewModelProvider(this);
    }

    /**
     * 暴露给自己的孩子 得到ViewModelProvider
     * @param activity
     * 子类的 activity
     * @return
     */
    protected ViewModelProvider getActivityViewModelProvider(AppCompatActivity activity) {
        return new ViewModelProvider(activity, activity.getDefaultViewModelProviderFactory());
    }

    /**
     * 暴露给自己的孩子 隐藏ActionBar
     */
    public void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
    }

    /**
     * 暴露给自己的孩子 显示ActionBar
     */
    public void showActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.show();
        }
    }
}
