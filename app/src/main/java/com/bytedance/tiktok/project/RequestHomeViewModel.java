package com.bytedance.tiktok.project;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bytedance.tiktok.bean.CurUserBean;
import com.bytedance.tiktok.bean.DataCreate;

import java.util.Random;

public class RequestHomeViewModel extends ViewModel {

    private MutableLiveData<CurUserBean> homeDataResultMutableLiveData;

    public MutableLiveData<CurUserBean> getHomeDataResultMutableLiveData() {
        if (homeDataResultMutableLiveData == null) {
            homeDataResultMutableLiveData = new MutableLiveData<>();
        }
        return homeDataResultMutableLiveData;
    }

    public void touchOffHomeData() {
        requestHomeData(getHomeDataResultMutableLiveData());
    }

    // mock the request value
    public void requestHomeData(MutableLiveData<CurUserBean> homeDataResultLiveData) {
        if (DataCreate.datas == null) {
            return;
        }
        int position = (new Random()).nextInt(DataCreate.datas.size());
        CurUserBean curUserBean = new CurUserBean(DataCreate.datas.get(position).getUserBean());
        homeDataResultLiveData.postValue(curUserBean);
    }

}
