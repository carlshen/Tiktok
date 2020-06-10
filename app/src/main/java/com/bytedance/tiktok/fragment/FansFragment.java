package com.bytedance.tiktok.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bytedance.tiktok.R;
import com.bytedance.tiktok.adapter.FansAdapter;
import com.bytedance.tiktok.bean.DataCreate;
import com.bytedance.tiktok.databinding.FragmentFansBinding;
import com.bytedance.tiktok.project.BaseFragment;

public class FansFragment extends BaseFragment {
    private FragmentFansBinding binding;
    private RecyclerView recyclerView;
    private FansAdapter fansAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_fans, container, false);
        showActionBar();
        binding = FragmentFansBinding.bind(root);

        recyclerView = binding.recyclerview;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fansAdapter = new FansAdapter(getContext(), DataCreate.userList);
        recyclerView.setAdapter(fansAdapter);
        return root;
    }

}
