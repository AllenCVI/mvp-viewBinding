package com.newblink.blink.android.mine.realizationmvp;

import com.newblink.blink.android.base.mvpbase.BasePresenter;

public class MinePresenter extends BasePresenter<MineContract.View> implements MineContract.Presenter{

    public MinePresenter(MineContract.View view) {
        super(view);
    }

}
