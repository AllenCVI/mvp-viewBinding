package com.newblink.blink.android.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.newblink.blink.android.base.App;

import java.util.HashMap;
import java.util.Map;


public class SpUtil {

    private static Map<String, SpUtil> blink_SPMap = new HashMap<>();
    private SharedPreferences sp;

    /**
     * 获取SP实例
     */
    public static SpUtil getSP() {
        return getSP("");
    }

    /**
     * 获取SP实例
     *
     * @param name sp名
     * @return {@link SpUtil}
     */
    public static SpUtil getSP(String name) {
        if (isSpace(name)) name = "SpUtils";
        SpUtil sp = blink_SPMap.get(name);
        if (sp == null) {
            sp = new SpUtil(name);
            blink_SPMap.put(name, sp);
        }
        return sp;
    }

    private SpUtil(String name) {
        sp = App.getInstance().getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    /**
     * SP中写入String
     */
    public void put(@NonNull String key, @NonNull String value) {
        sp.edit().putString(key, value).apply();
    }

    /**
     * SP中读取String
     */
    public String getString(@NonNull String key) {
        return getString(key, "");
    }

    /**
     * SP中读取String
     */
    public String getString(@NonNull String key, @NonNull String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    /**
     * SP中写入int
     */
    public void put(@NonNull String key, int value) {
        sp.edit().putInt(key, value).apply();
    }

    /**
     * SP中读取int
     */
    public int getInt(@NonNull String key) {
        return getInt(key, -1);
    }

    /**
     * SP中读取int
     */
    public int getInt(@NonNull String key, int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    /**
     * SP中写入long
     */
    public void put(@NonNull String key, long value) {
        sp.edit().putLong(key, value).apply();
    }

    /**
     * SP中读取long
     */
    public long getLong(@NonNull String key) {
        return getLong(key, -1L);
    }

    /**
     * SP中读取long
     */
    public long getLong(@NonNull String key, long defaultValue) {
        return sp.getLong(key, defaultValue);
    }

    /**
     * SP中写入float
     */
    public void put(@NonNull String key, float value) {
        sp.edit().putFloat(key, value).apply();
    }

    /**
     * SP中读取float
     */
    public float getFloat(@NonNull String key) {
        return getFloat(key, -1f);
    }

    /**
     * SP中读取float
     */
    public float getFloat(@NonNull String key, float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }

    /**
     * SP中写入boolean
     */
    public void put(@NonNull String key, boolean value) {
        sp.edit().putBoolean(key, value).apply();
    }

    /**
     * SP中读取boolean
     */
    public boolean getBoolean(@NonNull String key) {
        return getBoolean(key, false);
    }

    /**
     * SP中读取boolean
     */
    public boolean getBoolean(@NonNull String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }


    /**
     * SP中获取所有键值对
     */
    public Map<String, ?> getAll() {
        return sp.getAll();
    }

    /**
     * SP中移除该key
     */
    public void remove(@NonNull String key) {
        sp.edit().remove(key).apply();
    }

    /**
     * SP中是否存在该key
     */
    public boolean isContains(@NonNull String key) {
        return sp.contains(key);
    }

    /**
     * SP中清除所有数据
     */
    public void clearData() {
        sp.edit().clear().apply();
    }

    private static boolean isSpace(String str) {
        if (str == null) return true;
        for (int i = 0, len = str.length(); i < len; ++i) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
