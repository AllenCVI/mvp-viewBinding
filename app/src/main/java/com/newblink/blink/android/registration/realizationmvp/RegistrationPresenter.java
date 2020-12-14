package com.newblink.blink.android.registration.realizationmvp;

import com.newblink.blink.android.base.mvpbase.BasePresenter;
import com.newblink.blink.android.base.netbase.DefaultDisposableSubscriber;
import com.newblink.blink.android.common.LocalRepository;
import com.newblink.blink.android.model.remote.request.SignUpBean;
import com.newblink.blink.android.model.remote.responses.LoginDataBean;
import com.newblink.blink.android.model.remote.responses.ResponsesBean;
import com.newblink.blink.android.registration.net.RegistrationRepository;

public class RegistrationPresenter extends BasePresenter<RegistrationContract.View> implements RegistrationContract.Presenter{

    public RegistrationPresenter(RegistrationContract.View view) {
        super(view);
    }


    @Override
    public void signUp(SignUpBean signUpBean) {
        addDispose(RegistrationRepository.getInstance().signUp(signUpBean).subscribeWith(new DefaultDisposableSubscriber<ResponsesBean<LoginDataBean>>() {
            @Override
            protected void success(ResponsesBean<LoginDataBean> response) {
                LocalRepository.getInstance().saveUserInfo(response.record);
                mRootView.get().signUpSuccess();
            }


            @Override
            protected void failure(int errCode) {
                mRootView.get().hideLoading();
            }
        }));
    }


}
