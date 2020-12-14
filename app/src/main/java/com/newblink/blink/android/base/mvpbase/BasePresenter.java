package com.newblink.blink.android.base.mvpbase;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class BasePresenter<V extends IView> implements IPresenter {

    protected WeakReference<V> mRootView;
    protected CompositeDisposable mCompositeDisposable;

    @SuppressWarnings("unchecked")
    public BasePresenter(V view) {
        this.mRootView = new WeakReference<>(view);
        view.setPresenter(this);
    }

    @Override
    public void unsubscribe() {
        unDispose();
    }

    /**
     * 添加dispose
     * @param disposable
     */
    public void addDispose(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }

        //将所有 Disposable 放入集中处理
        if(mCompositeDisposable.delete(disposable)) {
            disposable.dispose();
        }

        mCompositeDisposable.add(disposable);
    }

    /**
     * 停止集合中正在执行的 RxJava 任务
     */
    public void unDispose() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();//保证 Activity 结束时取消所有正在执行的订阅
        }
        mCompositeDisposable = null;
    }
}
