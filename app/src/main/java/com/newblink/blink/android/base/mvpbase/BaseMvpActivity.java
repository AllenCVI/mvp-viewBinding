package com.newblink.blink.android.base.mvpbase;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.newblink.blink.android.base.BaseViewActivity;

public abstract class BaseMvpActivity<VB extends ViewBinding,P extends IPresenter> extends BaseViewActivity<VB> implements IView<P>{

    @Nullable
    protected P mPresenter;


    @Override
    public void setPresenter(P presenter) {
        this.mPresenter = presenter;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.unsubscribe();
        }


    }

}
