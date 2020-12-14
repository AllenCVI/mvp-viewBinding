package com.newblink.blink.android.mine.realizationmvp;

import com.newblink.blink.android.base.mvpbase.BasePresenter;

public class VisitorPresenter extends BasePresenter<VisitorContract.View> implements VisitorContract.Presenter{

    public VisitorPresenter(VisitorContract.View view) {
        super(view);
    }



}
