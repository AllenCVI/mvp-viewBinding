package com.newblink.blink.android.common.utils;

import java.util.Calendar;

public class DateUtil {

    public static int getSysYear() {
        Calendar date = Calendar.getInstance();
        return date.get(Calendar.YEAR);
    }

}
