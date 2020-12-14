package com.newblink.blink.android.home.realizationmvp;

import com.newblink.blink.android.base.mvpbase.BasePresenter;

public class HomePagePresenter  extends BasePresenter<HomePageContract.View> implements HomePageContract.Presenter{

    public HomePagePresenter(HomePageContract.View view) {
        super(view);
    }

}
