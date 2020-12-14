package com.newblink.blink.android.base;

import android.app.Application;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.MemoryCategory;
import com.newblink.blink.android.common.utils.BlinkActivityLifecycleCallbacks;
import com.scwang.smart.refresh.footer.BallPulseFooter;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshFooterCreator;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator;


public class App extends Application {
    private static App sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new BlinkActivityLifecycleCallbacks());
//      MultiDex.install(this);
        sInstance = this;
        //因为app中有大量的图片所以扩大缓存使加载速度更快
        Glide.get(this).setMemoryCategory(MemoryCategory.HIGH);
    }

    public static App getInstance() {
        return sInstance;
    }

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                return new MaterialHeader(context);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
//                ClassicsFooter footer = new ClassicsFooter(context).setDrawableSize(20);
//                footer.setFinishDuration(0);
                return new BallPulseFooter(context);
            }
        });

        // 测试人员提出的需求
//        ClassicsFooter.REFRESH_FOOTER_FINISH = "";

    }

}
