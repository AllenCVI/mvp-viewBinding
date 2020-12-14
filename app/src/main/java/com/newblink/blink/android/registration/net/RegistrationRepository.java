package com.newblink.blink.android.registration.net;

import com.newblink.blink.android.base.netbase.NetFactory;
import com.newblink.blink.android.model.remote.request.SignUpBean;
import com.newblink.blink.android.model.remote.responses.LoginDataBean;
import com.newblink.blink.android.model.remote.responses.ResponsesBean;

import io.reactivex.Flowable;

public class RegistrationRepository {

    private static RegistrationRepository INSTANCE = new RegistrationRepository();
    private final RegistrationApi serverApi;

    public static RegistrationRepository getInstance() {
        return INSTANCE;
    }

    private RegistrationRepository() {
        serverApi = NetFactory.getRetrofit().create(RegistrationApi.class);
    }


    public Flowable<ResponsesBean<LoginDataBean>> signUp(SignUpBean signUpBean) {
        return serverApi.signUp(signUpBean);
    }

}
