package com.bytedance.tiktok.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.activity.FocusActivity;
import com.bytedance.tiktok.activity.ShowImageActivity;
import com.bytedance.tiktok.base.CommPagerAdapter;
import com.bytedance.tiktok.bean.CurUserBean;
import com.bytedance.tiktok.bean.VideoBean;
import com.bytedance.tiktok.databinding.FragmentPersonalHomeBinding;
import com.bytedance.tiktok.project.BaseFragment;
import com.bytedance.tiktok.project.HomeViewModel;
import com.bytedance.tiktok.project.RequestHomeViewModel;
import com.bytedance.tiktok.utils.NumUtils;
import com.bytedance.tiktok.view.CircleImageView;
import com.bytedance.tiktok.view.IconFontTextView;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;

/**
 * create by carl shen
 * create on 2020-05-19
 * description 个人主页fragment
 */
public class PersonalHomeFragment extends BaseFragment {
    private FragmentPersonalHomeBinding binding;
    private HomeViewModel homeViewModel;
    private RequestHomeViewModel requestHomeViewModel;
    ImageView ivBg;
    CircleImageView ivHead;
    RelativeLayout rlDropdown;
    LinearLayout llFocus;
    LinearLayout llFans;
    ImageView ivReturn;
    TextView tvTitle;
    TextView tvFocus;
    IconFontTextView ivMore;
    Toolbar toolbar;
    XTabLayout tabLayout;
    AppBarLayout appBarLayout;
    ViewPager viewPager;
    TextView tvNickname;
    TextView tvSign;
    TextView tvGetLikeCount;
    TextView tvFocusCount;
    TextView tvFansCount;
    TextView tvAddfocus;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private CommPagerAdapter pagerAdapter;
    private VideoBean.UserBean cUserBean = new VideoBean.UserBean();
//    private Subscription subscription;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        requestHomeViewModel = new ViewModelProvider(this).get(RequestHomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_personal_home, container, false);
        showActionBar();
        binding = FragmentPersonalHomeBinding.bind(root);
        binding.setVm(homeViewModel);
        binding.setClick(new ProxyClick());

        init();
        return root;
    }

    protected void init() {
        ivBg = binding.ivBg;
        ivHead = binding.ivHead;
        rlDropdown = binding.rlDropdown;
        llFocus = binding.llFocus;
        llFans = binding.llFans;
        ivReturn = binding.ivReturn;
        tvTitle = binding.tvTitle;
        tvFocus = binding.tvFocus;
        ivMore = binding.ivMore;
        toolbar = binding.toolbar;
        tabLayout = binding.tablayout;
        appBarLayout = binding.appbarlayout;
        viewPager = binding.viewpager;
        tvNickname = binding.tvNickname;
        tvSign = binding.tvSign;
        tvGetLikeCount = binding.tvGetlikeCount;
        tvFocusCount = binding.tvFocusCount;
        tvFansCount = binding.tvFansCount;
        tvAddfocus = binding.tvAddfocus;
        //解决toolbar左边距问题
        toolbar.setContentInsetsAbsolute(0, 0);

        setAppbarLayoutPercent();

//        ivReturn.setOnClickListener(this);
//        ivHead.setOnClickListener(this);
//        ivBg.setOnClickListener(this);
//        llFocus.setOnClickListener(this);
//        llFans.setOnClickListener(this);

        setTabLayout();
        setUserInfo();
    }

    // 必须写成public
    public class ProxyClick {

        public void toLinkReturn() {
            Toast.makeText(mActivity, "即将发起进入跳转动作1...", Toast.LENGTH_SHORT).show();
//            RxBus.getDefault().post(new MainPageChangeEvent(0));
        }

        public void toLinkHead() {
            Toast.makeText(mActivity, "即将发起进入跳转动作2...", Toast.LENGTH_SHORT).show();
            transitionAnim(ivHead, cUserBean.getHead());
        }

        public void toLinkFocus() {
            Toast.makeText(mActivity, "即将发起进入跳转动作3...", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), FocusActivity.class));
        }

        public void toLinkFans() {
            Toast.makeText(mActivity, "即将发起进入跳转动作4...", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), FocusActivity.class));
        }

    }

    /**
     * 过渡动画跳转页面
     *
     * @param view
     */
    public void transitionAnim(View view, int res) {
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), view, getString(R.string.trans));
        Intent intent = new Intent(getActivity(), ShowImageActivity.class);
        intent.putExtra("res", res);
        ActivityCompat.startActivity(getActivity(), intent, compat.toBundle());
    }

    public void setUserInfo() {
        // 观察数据的变化 ==== 变化到界面中去
        requestHomeViewModel.getHomeDataResultMutableLiveData().observe(getViewLifecycleOwner(), new Observer<CurUserBean>() {
            @Override
            public void onChanged(CurUserBean curUserBean) {

                coordinatorLayoutBackTop();
                cUserBean = curUserBean.getUserBean();

                ivBg.setImageResource(curUserBean.getUserBean().getHead());
                ivHead.setImageResource(curUserBean.getUserBean().getHead());
                tvNickname.setText(curUserBean.getUserBean().getNickName());
                tvSign.setText(curUserBean.getUserBean().getSign());
                tvTitle.setText(curUserBean.getUserBean().getNickName());

                String subCount = NumUtils.numberFilter(curUserBean.getUserBean().getSubCount());
                String focusCount = NumUtils.numberFilter(curUserBean.getUserBean().getFocusCount());
                String fansCount = NumUtils.numberFilter(curUserBean.getUserBean().getFansCount());

                //获赞 关注 粉丝
                tvGetLikeCount.setText(subCount);
                tvFocusCount.setText(focusCount);
                tvFansCount.setText(fansCount);

                //关注状态
                if (curUserBean.getUserBean().isFocused()) {
                    tvAddfocus.setText("已关注");
                    tvAddfocus.setBackgroundResource(R.drawable.shape_round_halfwhite);
                } else {
                    tvAddfocus.setText("关注");
                    tvAddfocus.setBackgroundResource(R.drawable.shape_round_red);
                }

                setTabLayout();
//                String imagePath1 = CurUserBean.getData().getCompany_list().get(0).getImage();
//                homeViewModel.getImageURL1().setValue(imagePath1);
//
//                String imagePath2 = homeDataResult.getData().getAd_list().get(0).getImage();
//                homeViewModel.getImageURL2().setValue(imagePath2);
//
//                String linkPath1 = homeDataResult.getData().getCompany_list().get(0).getLink();
//                homeViewModel.getLinkURL1().setValue(linkPath1);
//
//                String linkPath2 = homeDataResult.getData().getAd_list().get(0).getLink();
//                homeViewModel.getLinkURL2().setValue(linkPath2);

            }
        });

        // 触发一次
        requestHomeViewModel.touchOffHomeData();
    }

    private void setTabLayout() {
        String[] titles = new String[]{"作品 " + cUserBean.getWorkCount(), "动态 " + cUserBean.getDynamicCount(), "喜欢 " + cUserBean.getLikeCount()};

        fragments.clear();
        for (int i = 0; i < titles.length; i++) {
            fragments.add(new WorkFragment());
            tabLayout.addTab(tabLayout.newTab().setText(titles[i]));
        }

        pagerAdapter = new CommPagerAdapter(getChildFragmentManager(), fragments, titles);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * 根据滚动比例渐变view
     */
    private void setAppbarLayoutPercent() {
        appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            float percent = (Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange());  //滑动比例

            if (percent > 0.8) {
                tvTitle.setVisibility(View.VISIBLE);
                tvFocus.setVisibility(View.VISIBLE);

                float alpha = 1 - (1 - percent) * 5;  //渐变变换
                tvTitle.setAlpha(alpha);
                tvFocus.setAlpha(alpha);
            } else {
                tvTitle.setVisibility(View.GONE);
                tvFocus.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 自动回顶部
     */
    private void coordinatorLayoutBackTop() {
        CoordinatorLayout.Behavior behavior =
                ((CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams()).getBehavior();
        if (behavior instanceof AppBarLayout.Behavior) {
            AppBarLayout.Behavior appBarLayoutBehavior = (AppBarLayout.Behavior) behavior;
            int topAndBottomOffset = appBarLayoutBehavior.getTopAndBottomOffset();
            if (topAndBottomOffset != 0) {
                appBarLayoutBehavior.setTopAndBottomOffset(0);
            }
        }
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.iv_return:
//                RxBus.getDefault().post(new MainPageChangeEvent(0));
//                break;
//            case R.id.iv_head:
//                transitionAnim(ivHead, curUserBean.getHead());
//                break;
//            case R.id.iv_bg:
//
//                break;
//            case R.id.ll_focus:
//                startActivity(new Intent(getActivity(), FocusActivity.class));
//                break;
//            case R.id.ll_fans:
//                startActivity(new Intent(getActivity(), FocusActivity.class));
//                break;
//        }
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//
//        if (subscription != null) {
//            subscription.unsubscribe();
//        }
    }
}
