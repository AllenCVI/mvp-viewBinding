package com.newblink.blink.android.message.fragment;

import android.os.Bundle;
import android.view.View;

import com.dueeeke.videoplayer.player.VideoView;
import com.newblink.blink.android.databinding.FragmentMessageBinding;
import com.newblink.blink.android.message.realizationmvp.MessageContract;
import com.newblink.blink.android.base.mvpbase.BaseMvpFragment;

public class MessageFragment extends BaseMvpFragment<FragmentMessageBinding,VideoView,MessageContract.Presenter> implements MessageContract.View{

    public static MessageFragment newInstance() {
        return new MessageFragment();
    }



    @Override
    protected void initView(Bundle savedInstanceState, View rootView) {

    }


}
