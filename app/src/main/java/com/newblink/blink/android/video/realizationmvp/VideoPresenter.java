package com.newblink.blink.android.video.realizationmvp;

import com.newblink.blink.android.base.mvpbase.BasePresenter;

public class VideoPresenter extends BasePresenter<VideoContract.View> implements VideoContract.Presenter{

    public VideoPresenter(VideoContract.View view) {
        super(view);
    }

}
