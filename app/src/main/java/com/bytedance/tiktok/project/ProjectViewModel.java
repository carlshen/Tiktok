package com.bytedance.tiktok.project;

import androidx.lifecycle.ViewModel;

import com.xiangxue.common.project.CustomProjectLiveData;

public class ProjectViewModel extends ViewModel {

    private final CustomProjectLiveData<Boolean> isActive;
    private final CustomProjectLiveData<Boolean> isLogin;

    public CustomProjectLiveData<Boolean> getIsActive() {
        return isActive;
    }

    public CustomProjectLiveData<Boolean> getIsLogin() {
        return isLogin;
    }

    {
        isActive = new CustomProjectLiveData<>();
        isActive.setValue(false);

        isLogin = new CustomProjectLiveData<>();
        isLogin.setValue(false);
    }

}
