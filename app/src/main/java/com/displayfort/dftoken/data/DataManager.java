
package com.displayfort.dftoken.data;

import com.displayfort.dftoken.data.local.db.DbHelper;
import com.displayfort.dftoken.data.local.prefs.PreferencesHelper;
import com.displayfort.dftoken.data.remote.ApiHelper;

/**
 * Created by Yogesh  on 07/07/17.
 */

public interface DataManager extends DbHelper, PreferencesHelper, ApiHelper {

//    Observable<List<QuestionCardData>> getQuestionCardData();
//
//    Observable<Boolean> seedDatabaseOptions();
//
//    Observable<Boolean> seedDatabaseQuestions();

    void setUserAsLoggedOut();

    void updateApiHeader(Long userId, String accessToken);

    void updateApiHeader(String userId, String accessToken);

    void updateUserInfo(
            String accessToken,
            Long userId,
            LoggedInMode loggedInMode,
            String userName,
            String email,
            String profilePicPath);

    void updateUserInfo(
            String accessToken,
            String userId,
            LoggedInMode loggedInMode,
            String userName,
            String email
    );

    void updateLoginUserInfo(
            String accessToken,
            String userId,
            LoggedInMode loggedInMode,
            String userName,
            String bgPath,
            String profilePicPath
    );

    void updateLoginUserInfo(
            String accessToken,
            String userId,
            LoggedInMode loggedInMode,
            String userName,
            String bgPath,
            String profilePicPath,
            String header_text,
            String sub_header_text
    );

    void updateUserInfo(
            String accessToken,
            String userId,
            LoggedInMode loggedInMode

    );


    void updateUserInfo(
            String userId
    );

    enum LoggedInMode {

        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_GOOGLE(1),
        LOGGED_IN_MODE_FB(2),
        LOGGED_IN_MODE_SERVER(3);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }
}
