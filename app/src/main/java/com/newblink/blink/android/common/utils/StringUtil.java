package com.newblink.blink.android.common.utils;

import androidx.annotation.StringRes;

import com.newblink.blink.android.base.App;

public class StringUtil {
    /**
     * 给小于10的int值前面加0
     */
    public static String initIntToString(String str,int intValue){
        return String.format("%02d",intValue);
    }

    /**
     * 把生日字符串搞成int值 通过"/"
     * @param Str 例"01/12/1996"
     * @param count 分割成几个int值
     */
    public static int[] StringToIntValueBySplit(String Str,int count,String format){
        int[] birthDayIntArr = new int[count];
        String[] birthDayStrArr = Str.split(format);
        for (int i=0;i<count;i++){
            birthDayIntArr[i] = Integer.valueOf(birthDayStrArr[i]);
        }
        return birthDayIntArr;
    }

    public static String getString(@StringRes int stringId) {
        return App.getInstance().getString(stringId);
    }


}
