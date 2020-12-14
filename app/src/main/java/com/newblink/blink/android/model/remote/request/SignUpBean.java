package com.newblink.blink.android.model.remote.request;

import com.google.gson.annotations.SerializedName;


public class SignUpBean extends RequestBean {
    @SerializedName("aqgpe")
    public int userAG;

    @SerializedName("ggelnmddedr")
    public String sex;

    public SignUpBean(int userAG, String sex) {
        this.userAG = userAG;
        this.sex = sex;
    }
}
