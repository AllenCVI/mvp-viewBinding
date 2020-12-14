package com.newblink.blink.android.model.remote.responses;

import com.google.gson.annotations.SerializedName;


public class ResponsesBean<T>{

    @SerializedName("meseg")
    public String message;

    @SerializedName("cvondre")
    public int num;

    @SerializedName("dkaitna")
    public T record;

}
