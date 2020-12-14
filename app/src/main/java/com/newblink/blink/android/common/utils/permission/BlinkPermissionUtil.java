package com.newblink.blink.android.common.utils.permission;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.tbruyelle.rxpermissions2.RxPermissions;

public class BlinkPermissionUtil {

    /**
     * 申请权限
     * @param activity
     * @param callback
     * @param permissions
     */
    public static void requestPermissions(FragmentActivity activity, PermissionCallback callback, String... permissions) {

        RxPermissions rxPermissions = new RxPermissions(activity);
        requestInternal(callback, rxPermissions, permissions);
    }

    /**
     * 申请权限
     * @param fragment
     * @param callback
     * @param permissions
     */
    public static void requestPermissions(Fragment fragment, PermissionCallback callback, String... permissions) {

        RxPermissions rxPermissions = new RxPermissions(fragment);
        requestInternal(callback, rxPermissions, permissions);
    }

    private static void requestInternal(PermissionCallback callback, RxPermissions rxPermissions, String[] permissions) {
        rxPermissions.request(permissions)
                .subscribe(granted -> {
                    if (granted) {
                        if (callback != null) {
                            callback.onPermissionGranted();
                        }
                    } else {
                        if (callback != null) {
                            callback.onPermissionDenied();
                        }
                    }
                });
    }

    public interface PermissionCallback {

        void onPermissionGranted();

        void onPermissionDenied();
    }
}
