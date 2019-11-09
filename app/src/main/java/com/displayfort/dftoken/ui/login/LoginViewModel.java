
package com.displayfort.dftoken.ui.login;

import android.text.TextUtils;

import com.displayfort.dftoken.data.DataManager;
import com.displayfort.dftoken.data.model.api.request.LoginRequest;
import com.displayfort.dftoken.ui.base.BaseViewModel;
import com.displayfort.dftoken.utils.rx.SchedulerProvider;


public class LoginViewModel extends BaseViewModel<LoginNavigator> {

    public LoginViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public int isEmailAndPasswordValid(String email, String password) {
        // validate email and password
        if (TextUtils.isEmpty(email)) {
            return 1;
        }
        if (TextUtils.isEmpty(password)) {
            return 2;
        }
        return 0;
    }

    public void login(String email, String password) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doServerLoginApiCall(new LoginRequest.ServerLoginRequest(email, password))
                .doOnSuccess(response -> getDataManager()
                        .updateLoginUserInfo(
                                response.getData().getToken_code(),
                                response.getData().getUser_id(),
                                DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
                                response.getData().getCompany_name(),
                                response.getData().getBg_image(),
                                response.getData().getLogo(),
                                response.getData().getHeader_text(),
                                response.getData().getSub_header_text()
                        ))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().openMainActivity(response.getData());
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);

                }));
    }

    public void liscensreCheck(String uniqueId, String lecensekey) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doLicenseCheckApiCall(new LoginRequest.LicenseRequest(lecensekey, uniqueId))
                .doOnSuccess(response -> getDataManager()
                        .updateLoginUserInfo(
                                response.getData().getToken_code(),
                                response.getData().getSub_user_id(),
                                DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
                                response.getData().getCompany_name(),
                                response.getData().getBg_image(),
                                response.getData().getLogo(),
                                response.getData().getHeader_text(),
                                response.getData().getSub_header_text()
                        ))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().openMainActivity(response.getData());
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);

                }));
    }


    public void onServerLoginClick() {
        getNavigator().login();
    }

    public void onForgotPasswordClick() {
        getNavigator().OpenForgotPasswordActivity();
    }

    public void onAdminLogin() {
        getNavigator().adminlogin();
    }
}
