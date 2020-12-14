package com.newblink.blink.android.registration.net;

import com.newblink.blink.android.model.remote.request.SignUpBean;
import com.newblink.blink.android.model.remote.responses.LoginDataBean;
import com.newblink.blink.android.model.remote.responses.ResponsesBean;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegistrationApi {

    //注册
    @POST("/a5Mlk2ZZCEjuYTJEmuOQSA==/LGuVnzLKi3ioF8neUQSsKg==")
    Flowable<ResponsesBean<LoginDataBean>> signUp(@Body SignUpBean signUpBean);


}
