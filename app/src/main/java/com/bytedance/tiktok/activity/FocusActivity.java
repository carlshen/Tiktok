package com.bytedance.tiktok.activity;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.base.CommPagerAdapter;
import com.bytedance.tiktok.databinding.ActivityFocusBinding;
import com.bytedance.tiktok.fragment.FansFragment;
import com.bytedance.tiktok.project.BaseActivity;

import java.util.ArrayList;

/**
 * create by carl shen
 * create on 2020-06-14
 * description 粉丝关注人页面
 */
public class FocusActivity extends BaseActivity {
    private ActivityFocusBinding activityFocusBinding;
    XTabLayout tabLayout;
    ViewPager viewPager;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private CommPagerAdapter pagerAdapter;
    private String[] titles = new String[] {"关注 128", "粉丝 128", "推荐关注"};

    @Override
    protected void init() {
        activityFocusBinding = DataBindingUtil.setContentView(this, R.layout.activity_focus);
        activityFocusBinding.setLifecycleOwner(this);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);

        for (int i=0;i<titles.length;i++) {
            fragments.add(new FansFragment());
            tabLayout.addTab(tabLayout.newTab().setText(titles[i]));
        }

        pagerAdapter = new CommPagerAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
