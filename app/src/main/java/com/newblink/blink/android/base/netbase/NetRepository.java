package com.newblink.blink.android.base.netbase;


public class NetRepository {

    private static NetRepository INSTANCE = new NetRepository();
    private final NetApi serverApi;

    public static NetRepository getInstance() {
        return INSTANCE;
    }

    private NetRepository() {
        serverApi = NetFactory.getRetrofit().create(NetApi.class);
    }



}
