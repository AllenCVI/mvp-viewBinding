package com.newblink.blink.android.base.mvpbase;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.dueeeke.videoplayer.player.VideoView;
import com.newblink.blink.android.base.BaseViewFragment;

public abstract class BaseMvpFragment<VB extends ViewBinding,T extends VideoView,P extends IPresenter> extends BaseViewFragment<VB, T> implements IView<P>{

    @Nullable
    protected P mPresenter;


    @Override
    public void setPresenter(P presenter) {
        this.mPresenter = presenter;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (mPresenter != null) {
            mPresenter.unsubscribe();
        }
    }
}