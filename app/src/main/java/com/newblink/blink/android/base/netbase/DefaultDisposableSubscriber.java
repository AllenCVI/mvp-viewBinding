package com.newblink.blink.android.base.netbase;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.util.Log;

import com.newblink.blink.android.R;
import com.newblink.blink.android.base.App;
import com.newblink.blink.android.common.constant.Network;
import com.newblink.blink.android.common.utils.StringUtil;
import com.newblink.blink.android.common.utils.ToastUtil;
import com.newblink.blink.android.login.activity.LoginActivity;
import com.newblink.blink.android.model.remote.responses.ResponsesBean;

import io.reactivex.subscribers.DisposableSubscriber;

public abstract class DefaultDisposableSubscriber<T> extends DisposableSubscriber<T> {

    private boolean needLoading = false;
    private Activity mContext;
    private Dialog mDialog;
    private boolean autoDismiss = true;

    public DefaultDisposableSubscriber() {

    }


    public DefaultDisposableSubscriber(Activity context, boolean needLoading) {
        this.needLoading = needLoading;
        mContext = context;
    }

    abstract protected void success(T data);

    abstract protected void failure(int errCode);

    @Override
    protected void onStart() {
        super.onStart();

        if (needLoading && mContext != null) {
            mDialog = new ProgressDialog(mContext);
            mDialog.show();
        }
    }

    @Override
    public void onNext(T t) {
        done(true);

        if (t instanceof ResponsesBean) {
            if (((ResponsesBean) t).num == 0) {
                success(t);
            } else {
                doFailure(((ResponsesBean) t).num);
            }
        } else {
            doFailure(Network.NET_ERROR_CODE);
        }

    }

    @Override
    public void onError(Throwable t) {
        done(false);

        if(t instanceof Exception){
            //访问获得对应的Exception
            onMyError(ExceptionHandle.handleException(t));
        }else {
            //将Throwable 和 未知错误的status code返回
            onMyError(new ExceptionHandle.ResponeThrowable(t,ExceptionHandle.ERROR.UNKNOWN));
        }
    }

    private void onMyError(ExceptionHandle.ResponeThrowable handleException) {
        doFailure(handleException.code);
    }

    private void doFailure(int errCod) {
        failure(errCod);
    }

    @Override
    public void onComplete() {

    }

    protected void done(boolean isSuccess) {
        if (autoDismiss) {
            dismiss();
        }

        mContext = null;
        mDialog = null;
    }

    private void dismiss() {
        if (mDialog != null) {
            try {
                mDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
