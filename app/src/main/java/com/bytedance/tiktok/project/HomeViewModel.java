package com.bytedance.tiktok.project;

import android.graphics.drawable.Drawable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bytedance.tiktok.R;
import com.xiangxue.common.utils.Utils;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String> imageURL1;
    private MutableLiveData<String> imageURL2;
    private MutableLiveData<String> linkURL1;
    private MutableLiveData<String> linkURL2;
    private MutableLiveData<Drawable> placeHolder;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("首页中心");

        imageURL1 = new MutableLiveData<>();
        imageURL1.setValue(null);

        imageURL2 = new MutableLiveData<>();
        imageURL2.setValue(null);

        linkURL1 = new MutableLiveData<>();
        linkURL2 = new MutableLiveData<>();

        placeHolder = new MutableLiveData<>();
        placeHolder.setValue(Utils.getApp().getResources().getDrawable(R.drawable.pictures_no));
    }

    public LiveData<String> getText() {
        return mText;
    }

    public MutableLiveData<String> getImageURL1() {
        return imageURL1;
    }

    public MutableLiveData<String> getImageURL2() {
        return imageURL2;
    }

    public MutableLiveData<String> getLinkURL1() {
        return linkURL1;
    }

    public MutableLiveData<String> getLinkURL2() {
        return linkURL2;
    }

    public MutableLiveData<Drawable> getPlaceHolder() {
        return placeHolder;
    }
}