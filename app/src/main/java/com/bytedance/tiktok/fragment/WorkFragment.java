package com.bytedance.tiktok.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bytedance.tiktok.R;
import com.bytedance.tiktok.adapter.WorkAdapter;
import com.bytedance.tiktok.bean.DataCreate;
import com.bytedance.tiktok.databinding.FragmentWorkBinding;
import com.bytedance.tiktok.project.BaseFragment;

/**
 * create by carl shen
 * create on 2020-05-19
 * description 个人作品fragment
 */
public class WorkFragment extends BaseFragment {
    private FragmentWorkBinding binding;
    private RecyclerView recyclerView;
    private WorkAdapter workAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_work, container, false);
        showActionBar();
        binding = FragmentWorkBinding.bind(root);

        recyclerView = binding.recyclerview;
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        workAdapter = new WorkAdapter(getActivity(), DataCreate.datas);
        recyclerView.setAdapter(workAdapter);
        return root;
    }

}
