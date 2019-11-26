package com.displayfort.dftoken;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by Husain on 15-03-2016.
 */
public class NrFtPrefrence {


    private static final String SHARED_PREFERENCE_NAME = "FeedBackSharedPreference";
    private static final String SHARED_PREFERENCE_NAME_TUTORIAL = "FeedBackSharedPreferenceTUORIAL";

    private SharedPreferences sharedPreferences;
    private SharedPreferences sharedPreferencesTutorial;
    public static String TIME_FILTER = "timeFilter";
    public static String PAYMENT_FILTER = "paymentFilter";

    public void setClearPrefrence() {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.clear();
        prefsEditor.commit();
    }

    public NrFtPrefrence(Context context) {
//        context = RestaurantApplication.getInstance();
        this.sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        this.sharedPreferencesTutorial = context.getSharedPreferences(SHARED_PREFERENCE_NAME_TUTORIAL,
                Context.MODE_PRIVATE);
    }


    public void setIsLogin(boolean IsLogin) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putBoolean("IsLogin", IsLogin);
        prefsEditor.commit();
    }


    public void setLoginSessionKey(String sessionKey) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString("session_Key", null == sessionKey ? "" : sessionKey);
        prefsEditor.commit();
    }

    public String getLoginSessionKey() {
        return sharedPreferences.getString("session_Key", "");
    }


    public boolean IsLogin() {
        return sharedPreferences.getBoolean("IsLogin", false);
    }

    public void setLoginMacAdd(String MacAdd) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString("MacAdd", null == MacAdd ? "" : MacAdd);
        prefsEditor.commit();
    }

    public String getLoginMacAdd() {
        return sharedPreferences.getString("MacAdd", null);
    }

    public void setIP_ADDRESS(String IP_ADDRESS) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString("IP_ADDRESS", null == IP_ADDRESS ? "" : IP_ADDRESS);
        prefsEditor.commit();
    }


    public String getIP_ADDRESS() {
        return "http://" + sharedPreferences.getString("IP_ADDRESS", null) + "/displayfort-api/api/";
    }

    public String getImageIP_ADDRESS() {
        return "http://" + sharedPreferences.getString("IP_ADDRESS", null) + "/displayfort-dashboard/assets/img/";
    }

    public String getOnlyIP_ADDRESS() {
        return sharedPreferences.getString("IP_ADDRESS", "");
    }

}