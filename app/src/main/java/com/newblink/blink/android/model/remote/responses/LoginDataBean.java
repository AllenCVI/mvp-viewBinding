package com.newblink.blink.android.model.remote.responses;

import com.google.gson.annotations.SerializedName;

public class LoginDataBean {
    @SerializedName("aocmceeosbshTyovksern")
    public String loginToken;

    @SerializedName("ihmoAwcqcdemsdseTbojkyebn")
    public String imLoginToken;

    @SerializedName("powaMtomdbiafxisedd")
    public boolean PasswordMDF;

    @SerializedName("utsqeqr")
    public User consumer;
}
