package com.newblink.blink.android.common.utils;

import android.widget.Toast;

import androidx.annotation.StringRes;

import com.newblink.blink.android.base.App;


public class ToastUtil {


    private static Toast toast;

    public static void showToast(String msg) {

        if (toast == null) {
            toast = Toast.makeText(App.getInstance().getApplicationContext(), msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }

        toast.show();
    }

    public static void showToast(@StringRes int resId) {
        showToast(App.getInstance().getString(resId));
    }

    public static void reset() {
        toast = null;
    }
}
