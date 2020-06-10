package com.bytedance.tiktok.activity;

import android.widget.ImageView;

import androidx.databinding.DataBindingUtil;

import com.bytedance.tiktok.R;
import com.bytedance.tiktok.project.BaseActivity;

public class ShowImageActivity extends BaseActivity {
    ImageView ivHead;

    @Override
    protected void init() {
        DataBindingUtil.setContentView(this, R.layout.activity_show_image);
        ivHead = findViewById(R.id.iv_head);
        ivHead.setOnClickListener(v -> finish());

        int headRes = getIntent().getIntExtra("res", 0);
        ivHead.setImageResource(headRes);
    }
}
