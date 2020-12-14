package com.newblink.blink.android.login.net;

import com.newblink.blink.android.model.remote.request.LoginBean;
import com.newblink.blink.android.model.remote.responses.LoginDataBean;
import com.newblink.blink.android.model.remote.responses.ResponsesBean;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginApi {

    //登陆
    @POST("/a5Mlk2ZZCEjuYTJEmuOQSA==/YGMIUkYIN7KzFjLeYjf0tw==")
    Flowable<ResponsesBean<LoginDataBean>> signUp(@Body LoginBean loginBean);


}
