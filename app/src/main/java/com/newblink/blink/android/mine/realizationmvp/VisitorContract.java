package com.newblink.blink.android.mine.realizationmvp;

import com.newblink.blink.android.base.mvpbase.IPresenter;
import com.newblink.blink.android.base.mvpbase.IView;

public class VisitorContract {

    public interface Presenter extends IPresenter {


    }



    public interface View extends IView<Presenter> {



    }
}
