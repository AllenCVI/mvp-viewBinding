package com.newblink.blink.android.base.netbase;

import com.newblink.blink.android.common.constant.Network;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class NetFactory {

    private static final Retrofit RETROFIT = new MRetrofit().getInstance();

    public static Retrofit getRetrofit() {
        return RETROFIT;
    }

    private static class MRetrofit {

        Retrofit getInstance() {
            return new Retrofit.Builder()
                    .baseUrl(Network.BASE_URL)
                    .addCallAdapterFactory(new ObserveOnMainCallAdapterFactory(AndroidSchedulers.mainThread()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .client(OkHttpClientProvider.client())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }

}
