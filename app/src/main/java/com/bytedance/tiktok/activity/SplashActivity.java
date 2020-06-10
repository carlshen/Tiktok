package com.bytedance.tiktok.activity;

import android.content.Intent;
import android.os.CountDownTimer;

import androidx.databinding.DataBindingUtil;
import com.bytedance.tiktok.databinding.ActivitySplashBinding;

import com.bytedance.tiktok.R;
import com.bytedance.tiktok.project.BaseActivity;

/**
 * create by carl shen
 * create on 2020/5/19
 * description App启动页面
 */
public class SplashActivity extends BaseActivity {

    private ActivitySplashBinding binding;
    @Override
    protected void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        binding.setLifecycleOwner(this);
        new CountDownTimer(500, 500) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }.start();
    }
}
