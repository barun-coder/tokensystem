
package com.displayfort.dftoken.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.displayfort.dftoken.data.DataManager;
import com.displayfort.dftoken.di.PreferenceInfo;

import javax.inject.Inject;

/**
 * Created by Yogesh  on 07/07/17.
 */

public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";

    private static final String PREF_KEY_CURRENT_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL";

    private static final String PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID";

    private static final String PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME";

    private static final String PREF_KEY_CURRENT_USER_PROFILE_PIC_URL = "PREF_KEY_CURRENT_USER_PROFILE_PIC_URL";

    private static final String PREF_KEY_CURRENT_BG_PIC_URL = "PREF_KEY_BG_PIC_URL";

    private static final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE";

    public static final String PREF_KEY_HEADER_TEXT = "PREF_KEY_HEADER_TEXT";

    public static final String PREF_KEY_SUB_HEADER_TEXT = "PREF_KEY_SUB_HEADER_TEXT";

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(Context context, @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public String getAccessToken() {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null);
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply();
    }

    @Override
    public String getCurrentUserEmail() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_EMAIL, null);
    }

    @Override
    public void setCurrentUserEmail(String email) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_EMAIL, email).apply();
    }

    @Override
    public String getCurrentUserId() {
        try {
            return mPrefs.getString(PREF_KEY_CURRENT_USER_ID, null);
        } catch (Exception e) {
            return mPrefs.getLong(PREF_KEY_CURRENT_USER_ID, 0) + "";
        }

    }

//    @Override
//    public void setCurrentUserId(Long userId) {
//        long id = userId == null ? AppConstants.NULL_INDEX : userId;
//        mPrefs.edit().putLong(PREF_KEY_CURRENT_USER_ID, id).apply();
//    }

    @Override
    public void setCurrentUserId(String userId) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_ID, userId).apply();
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPrefs.getInt(PREF_KEY_USER_LOGGED_IN_MODE,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType());
    }


    @Override
    public void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode) {
        mPrefs.edit().putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.getType()).apply();
    }

    @Override
    public String getCurrentUserName() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_NAME, null);
    }

    @Override
    public void setCurrentUserName(String userName) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_NAME, userName).apply();
    }

    @Override
    public String getCurrentUserProfilePicUrl() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, null);
    }

    @Override
    public void setCurrentUserProfilePicUrl(String profilePicUrl) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, profilePicUrl).apply();
    }

    @Override
    public String getCurrentBgPath() {

        return mPrefs.getString(PREF_KEY_CURRENT_BG_PIC_URL, null);
    }

    @Override
    public void setCurrentBgPath(String BGPath) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_BG_PIC_URL, BGPath).apply();
    }

    @Override
    public void setValue(String key, String userName) {
        mPrefs.edit().putString(key, userName).apply();
    }

    @Override
    public String getValue(String key) {
        return mPrefs.getString(key, null);
    }
}
