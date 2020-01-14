
package com.displayfort.dftoken.ui.feedback;


import com.displayfort.dftoken.data.model.api.response.TokenResponse;
import com.displayfort.dftoken.ui.base.BaseNavigator;

public interface TokenNavigator extends BaseNavigator {


    void onSelectToken();

    void onSkip();

    void onLogout();

    void onNext();

    void onCancel();

    void openLoginActivity();

    void getTokenDetail(TokenResponse response);

    void onLogoutClick();
}
