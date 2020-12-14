package com.newblink.blink.android.registration.realizationmvp;

import com.newblink.blink.android.base.mvpbase.IPresenter;
import com.newblink.blink.android.base.mvpbase.IView;
import com.newblink.blink.android.model.remote.request.SignUpBean;

public class RegistrationContract {
    public interface Presenter extends IPresenter {

        void signUp(SignUpBean signUpBean);

    }



    public interface View extends IView<Presenter> {

        void signUpSuccess();
        void showLoading();
        void hideLoading();


    }
}
