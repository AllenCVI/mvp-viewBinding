package com.newblink.blink.android.login.realizationmvp;

import com.newblink.blink.android.base.mvpbase.BasePresenter;
import com.newblink.blink.android.base.netbase.DefaultDisposableSubscriber;
import com.newblink.blink.android.common.LocalRepository;
import com.newblink.blink.android.login.net.LoginRepository;
import com.newblink.blink.android.model.remote.request.LoginBean;
import com.newblink.blink.android.model.remote.responses.LoginDataBean;
import com.newblink.blink.android.model.remote.responses.ResponsesBean;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter{

    public LoginPresenter(LoginContract.View view) {
        super(view);
    }


    @Override
    public void login(LoginBean loginBean) {
        addDispose(LoginRepository.getInstance().login(loginBean).subscribeWith(new DefaultDisposableSubscriber<ResponsesBean<LoginDataBean>>() {
            @Override
            protected void success(ResponsesBean<LoginDataBean> responses) {
                LocalRepository.getInstance().saveUserInfo(responses.record);
                mRootView.get().loginSuccess();
            }

            @Override
            protected void failure(int errCode) {
                mRootView.get().hideLoading();
            }
        }));
    }
}
