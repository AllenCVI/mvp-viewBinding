package com.newblink.blink.android.model.remote.request;

import com.google.gson.annotations.SerializedName;

public class LoginBean extends RequestBean {

    @SerializedName("arciceocuenst")
    public String userName;

    @SerializedName("paw")
    public String password;

    public LoginBean(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
