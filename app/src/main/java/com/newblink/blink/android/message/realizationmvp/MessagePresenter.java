package com.newblink.blink.android.message.realizationmvp;

import com.newblink.blink.android.base.mvpbase.BasePresenter;

public class MessagePresenter extends BasePresenter<MessageContract.View> implements MessageContract.Presenter{

    public MessagePresenter(MessageContract.View view) {
        super(view);
    }

}
