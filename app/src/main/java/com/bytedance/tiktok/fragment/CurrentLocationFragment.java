package com.bytedance.tiktok.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bytedance.tiktok.R;
import com.bytedance.tiktok.adapter.GridVideoAdapter;
import com.bytedance.tiktok.bean.DataCreate;
import com.bytedance.tiktok.databinding.FragmentCurrentLocationBinding;
import com.bytedance.tiktok.project.BaseFragment;

/**
 * create by carl shen
 * create on 2020-05-19
 * description 附近的人fragment
 */
public class CurrentLocationFragment extends BaseFragment {
    private FragmentCurrentLocationBinding binding;
    RecyclerView recyclerView;
    private GridVideoAdapter adapter;
    SwipeRefreshLayout refreshLayout;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        collectViewModel = new ViewModelProvider(this).get(CollectViewModel.class);
        View root = inflater.inflate(R.layout.fragment_current_location, container, false);
        showActionBar();
        binding = FragmentCurrentLocationBinding.bind(root);
//        binding.setVm(collectViewModel);
//        binding.setClick(new ProxyClick());
        binding.setLifecycleOwner(this);

        new DataCreate().initData();
        recyclerView = binding.recyclerview;
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        adapter = new GridVideoAdapter(getActivity(), DataCreate.datas);
        recyclerView.setAdapter(adapter);

        refreshLayout = binding.refreshlayout;
        refreshLayout.setColorSchemeResources(R.color.color_link);
        refreshLayout.setOnRefreshListener(() -> new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                refreshLayout.setRefreshing(false);
            }
        }.start());

        return root;
    }

}
