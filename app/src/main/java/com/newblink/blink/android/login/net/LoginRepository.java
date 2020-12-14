package com.newblink.blink.android.login.net;

import com.newblink.blink.android.base.netbase.NetFactory;
import com.newblink.blink.android.model.remote.request.LoginBean;
import com.newblink.blink.android.model.remote.responses.LoginDataBean;
import com.newblink.blink.android.model.remote.responses.ResponsesBean;

import io.reactivex.Flowable;

public class LoginRepository {

    private static LoginRepository INSTANCE = new LoginRepository();
    private final LoginApi serverApi;

    public static LoginRepository getInstance() {
        return INSTANCE;
    }

    private LoginRepository() {
        serverApi = NetFactory.getRetrofit().create(LoginApi.class);
    }


    public Flowable<ResponsesBean<LoginDataBean>> login(LoginBean loginBean) {
        return serverApi.signUp(loginBean);
    }

}
