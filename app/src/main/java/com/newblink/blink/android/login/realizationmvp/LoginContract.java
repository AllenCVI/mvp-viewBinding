package com.newblink.blink.android.login.realizationmvp;

import com.newblink.blink.android.base.mvpbase.IPresenter;
import com.newblink.blink.android.base.mvpbase.IView;
import com.newblink.blink.android.model.remote.request.LoginBean;

public class LoginContract {
    public interface Presenter extends IPresenter {

        void login(LoginBean loginBean);

    }



    public interface View extends IView<Presenter> {
        void loginSuccess();
        void showLoading();
        void hideLoading();

    }
}
