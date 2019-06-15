package com.golong.commlib.user;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.golong.commlib.util.GsonUtil;
import com.golong.commlib.util.SPUtil;

/**
 * @author Andy
 * @date   2019/3/25 15:47
 * @link   {http://blog.csdn.net/andy_l1}
 * Desc:    UserInfoManager.java
 */
public class UserInfoManager {
    private Context mContext;
    private String mToken;
    private String userId;
    public static final String SP_TOKEN = "token";
    public static final String SP_USERINFO = "userInfo";
    public static final String SP_USERID = "userID";

    public static boolean isLoginDisable=false;
    private static UserInfoManager sUserInfoManager = new UserInfoManager();
    private UserInfo mUserInfo=new UserInfo();

    private UserInfoManager() {
    }

    public static UserInfoManager getInstance() {
        return sUserInfoManager;
    }
    public void setInstance(UserInfoManager m){
        sUserInfoManager=m;
    }

    public void initOnApplicationCreate(Context context) {
        mContext = context;
        mToken = (String) SPUtil.get(mContext, SP_TOKEN, "");
        String userInfoJson = (String) SPUtil.get(mContext, SP_USERINFO, "");
        userId= (String) SPUtil.get(mContext,SP_USERID,"");
        Log.d("UserInfoManager", userInfoJson);
        if (!TextUtils.isEmpty(userInfoJson)) {
            mUserInfo = GsonUtil.parseJson(userInfoJson, UserInfo.class);
        }
    }

    public UserInfo getUserInfo() {
        return mUserInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        mUserInfo = userInfo;
        SPUtil.put(mContext, SP_USERINFO, GsonUtil.toJsonStr(userInfo));
        setToken(userInfo.getToken());
        setUserId(userInfo.getShop_key());
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
        SPUtil.put(mContext, SP_TOKEN, token);
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
        SPUtil.put(mContext,SP_USERID,userId);
    }
    public void clearData() {
        mToken = "";
        mUserInfo = new UserInfo();
        SPUtil.remove(mContext, SP_TOKEN);
        SPUtil.remove(mContext, SP_USERINFO);
        SPUtil.remove(mContext, SP_USERID);
    }
    
    public boolean isLogin(){
        return !TextUtils.isEmpty(mToken);
    }
}
