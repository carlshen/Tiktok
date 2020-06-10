package com.bytedance.tiktok.activity;

import androidx.databinding.DataBindingUtil;

import com.bytedance.tiktok.R;
import com.bytedance.tiktok.fragment.RecommendFragment;
import com.bytedance.tiktok.project.BaseActivity;

/**
 * create by carl shen
 * create on 2020-05-24
 * description 视频全屏播放页
 */
public class PlayListActivity extends BaseActivity {
    public static int initPos;

    @Override
    protected void init() {
        DataBindingUtil.setContentView(this, R.layout.activity_play_list);
        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new RecommendFragment()).commit();
    }
}
