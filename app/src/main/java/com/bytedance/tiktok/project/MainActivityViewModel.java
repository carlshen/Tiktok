package com.bytedance.tiktok.project;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<Boolean> isActive;
    private MutableLiveData<Integer> useActiveCount;
    private MutableLiveData<String> activeState;

    public MutableLiveData<Boolean> getIsActive() {
        if (isActive == null) {
            isActive = new MutableLiveData<>();
            isActive.setValue(false);
        }
        return isActive;
    }

    public MutableLiveData<Integer> getUseActiveCount() {
        if (useActiveCount == null) {
            useActiveCount = new MutableLiveData<>();
            useActiveCount.setValue(0);
        }
        return useActiveCount;
    }

    public MutableLiveData<String> getActiveState() {
        if (activeState == null) {
            activeState = new MutableLiveData<>();
            activeState.setValue("not status");
        }
        return activeState;
    }

    // TODO 同学们注意： 后面的功能可以无限次的扩展下去，这块内容交给同学们自己去扩展了
    // TODO ............
}
