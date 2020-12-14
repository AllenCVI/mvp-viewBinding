package com.newblink.blink.android.common;

import android.text.TextUtils;

import com.newblink.blink.android.common.utils.SpUtil;
import com.newblink.blink.android.model.remote.responses.LoginDataBean;


/**
 * 保存登陆信息、状态
 */
public class LocalRepository {


    private static volatile LocalRepository sInstance = null;

    private LocalRepository() {

    }

    public static LocalRepository getInstance() {
        if (sInstance == null) {
            synchronized (LocalRepository.class) {
                if (sInstance == null) {
                    sInstance = new LocalRepository();
                }
            }
        }
        return sInstance;
    }



    /**
     * 保存用户登录后信息
     */
    public void saveUserInfo(LoginDataBean loginResult) {
        SpUtil.getSP().put(SpKeyConfig.ACCESS_TOKEN_KEY, loginResult.loginToken);
        SpUtil.getSP().put(SpKeyConfig.IM_ACCESS_TOKEN_KEY,loginResult.imLoginToken);
        SpUtil.getSP().put(SpKeyConfig.ACCOUNT,loginResult.consumer.userName);
        SpUtil.getSP().put(SpKeyConfig.PASSWORD,loginResult.consumer.password);
        SpUtil.getSP().put(SpKeyConfig.USER_ID,loginResult.consumer.consumerId);
        SpUtil.getSP().put(SpKeyConfig.USER_STRING_ID,loginResult.consumer.consumerStrId);
        SpUtil.getSP().put(SpKeyConfig.IM_ID,loginResult.consumer.imIdentify);
        SpUtil.getSP().put(SpKeyConfig.NICKNAME,loginResult.consumer.petName);
        SpUtil.getSP().put(SpKeyConfig.ICON,loginResult.consumer.logo);


    }

    /**
     * 清空本地保存用户信息，在退出登录/或者账号异地登录时调用
     */
    public void clearUserInfo() {
        SpUtil.getSP().put(SpKeyConfig.ACCESS_TOKEN_KEY, "");
        SpUtil.getSP().put(SpKeyConfig.IM_ACCESS_TOKEN_KEY,"");
        SpUtil.getSP().put(SpKeyConfig.USER_ID,"");
        SpUtil.getSP().put(SpKeyConfig.USER_STRING_ID,"");
        SpUtil.getSP().put(SpKeyConfig.IM_ID,"");
        SpUtil.getSP().put(SpKeyConfig.NICKNAME,"");
        SpUtil.getSP().put(SpKeyConfig.ICON,"");

    }

    public String getAccessToken() {
        return SpUtil.getSP().getString(SpKeyConfig.ACCESS_TOKEN_KEY, "");
    }

    public String getIMAccessToken(){
        return SpUtil.getSP().getString(SpKeyConfig.IM_ACCESS_TOKEN_KEY, "");
    }

    public String getPassword(){
        return SpUtil.getSP().getString(SpKeyConfig.PASSWORD,"");
    }

    public String getAccount(){
        return SpUtil.getSP().getString(SpKeyConfig.ACCOUNT,"");
    }

    //更新密码
    public void updatePassword(String password){
        SpUtil.getSP().put(SpKeyConfig.PASSWORD,password);
    }

    //更新账号
    public void updateAccount(String account){
        SpUtil.getSP().put(SpKeyConfig.ACCOUNT,account);
    }


    public boolean isLogin(){
      boolean isLogin = TextUtils.isEmpty(getAccessToken());
      return !isLogin;
    }




    //登陆和登陆状态的字段
    interface SpKeyConfig {
        String ACCESS_TOKEN_KEY = "accessToken";
        String IM_ACCESS_TOKEN_KEY ="imAccessToken";
        String PASSWORD = "password";
        String ACCOUNT = "account";
        String USER_ID = "userId";
        String USER_STRING_ID = "userStringId";
        String IM_ID = "imId";
        String NICKNAME = "nickname";
        String ICON = "icon";



    }
}
