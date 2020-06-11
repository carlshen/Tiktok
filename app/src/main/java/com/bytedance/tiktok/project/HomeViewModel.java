package com.bytedance.tiktok.project;

import android.graphics.drawable.Drawable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bytedance.tiktok.R;
import com.xiangxue.common.utils.Utils;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mNikeName;
    private MutableLiveData<String> imageHead;
    private MutableLiveData<String> imageBack;
    private MutableLiveData<String> mSign;
    private MutableLiveData<String> mTitle;
    private MutableLiveData<Drawable> placeHolder;

    public HomeViewModel() {
        mNikeName = new MutableLiveData<>();
        mNikeName.setValue("");

        imageHead = new MutableLiveData<>();
        imageHead.setValue(null);

        imageBack = new MutableLiveData<>();
        imageBack.setValue(null);

        mSign = new MutableLiveData<>();
        mTitle = new MutableLiveData<>();

        placeHolder = new MutableLiveData<>();
        placeHolder.setValue(Utils.getApp().getResources().getDrawable(R.drawable.pictures_no));
    }

    public MutableLiveData<String> getNickName() {
        return mNikeName;
    }

    public MutableLiveData<String> getImageHead() {
        return imageHead;
    }

    public MutableLiveData<String> getImageBack() {
        return imageBack;
    }

    public MutableLiveData<String> getSign() {
        return mSign;
    }

    public MutableLiveData<String> getTitle() {
        return mTitle;
    }

    public MutableLiveData<Drawable> getPlaceHolder() {
        return placeHolder;
    }
}