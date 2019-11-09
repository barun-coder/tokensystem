
package com.displayfort.dftoken.data.local.prefs;

import com.displayfort.dftoken.data.DataManager;

/**
 * Created by Husain  on 07/07/17.
 */

public interface PreferencesHelper {

    String getAccessToken();

    void setAccessToken(String accessToken);

    String getCurrentUserEmail();

    void setCurrentUserEmail(String email);

    String getCurrentUserId();

//    void setCurrentUserId(Long userId);

    void setCurrentUserId(String userId);

    int getCurrentUserLoggedInMode();

    void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode);

    String getCurrentUserName();

    void setCurrentUserName(String userName);

    String getCurrentUserProfilePicUrl();

    void setCurrentUserProfilePicUrl(String profilePicUrl);


    String getCurrentBgPath();

    void setCurrentBgPath(String BGPath);


    void setValue(String key, String userName);

    String getValue(String key);
}
