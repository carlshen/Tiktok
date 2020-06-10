package com.bytedance.tiktok.activity;

import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.bytedance.tiktok.R;
import com.bytedance.tiktok.databinding.ActivityMainBinding;
import com.bytedance.tiktok.project.BaseActivity;
import com.bytedance.tiktok.project.MainActivityViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * create by libo
 * create on 2020/5/19
 * description 主页面
 */
public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel mainActivityViewModel;
    /** 上次点击返回键时间 */
    private long lastTime;
    /** 连续按返回键退出时间 */
    private final int EXIT_TIME = 2000;

    @Override
    protected void init() {
        mainActivityViewModel = getActivityViewModelProvider(this).get(MainActivityViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        binding.setVm(mainActivityViewModel);

        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(
                        R.id.navigation_home,
                        R.id.navigation_recommend,
                        R.id.navigation_collect,
                        R.id.navigation_msg,
                        R.id.navigation_personal)
                        .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public void onBackPressed() {
        //双击返回退出App
        if (System.currentTimeMillis() - lastTime > EXIT_TIME) {
            Toast.makeText(getApplicationContext(), "再按一次退出", Toast.LENGTH_SHORT).show();
            lastTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }
}
