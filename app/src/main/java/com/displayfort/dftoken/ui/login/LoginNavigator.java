
package com.displayfort.dftoken.ui.login;


import com.displayfort.dftoken.data.model.api.response.LoginResponse;
import com.displayfort.dftoken.ui.base.BaseNavigator;

public interface LoginNavigator  extends BaseNavigator {

    void login();

    void adminlogin();

    void OpenForgotPasswordActivity();

    void openMainActivity(LoginResponse.UserDao data);

    void OpenVirificationActivity(String message);
}
