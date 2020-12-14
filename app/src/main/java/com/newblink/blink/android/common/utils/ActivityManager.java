package com.newblink.blink.android.common.utils;


import android.app.Activity;

import java.util.Stack;

public class ActivityManager {

    private volatile static ActivityManager sInstance = null;


    private ActivityManager() {

    }

    public static ActivityManager getInstance() {

        if (sInstance == null) {
            synchronized (ActivityManager.class) {
                if (sInstance == null) {
                    sInstance = new ActivityManager();
                }
            }
        }

        return sInstance;
    }

    private Stack<Activity> stack = new Stack<>();

    public void addActivity(Activity activity) {
        stack.add(activity);
    }

    public void removeActivity(Activity activity) {
        stack.remove(activity);
    }

    public void clearActivities() {

        for (Activity activity : stack) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }

    }

    public void removeAboveActivities(Class activityClass) {

        try {
            for (Activity activity = stack.peek(); !activity.getClass().equals(activityClass); activity = stack.pop()) {
                activity.finish();
            }
        } catch (Exception e) {

        }

    }
}