
package com.displayfort.dftoken.ui.feedback;

import com.displayfort.dftoken.data.DataManager;
import com.displayfort.dftoken.data.model.api.request.LoginRequest;
import com.displayfort.dftoken.data.model.api.request.TokenRequest;
import com.displayfort.dftoken.ui.base.BaseViewModel;
import com.displayfort.dftoken.utils.rx.SchedulerProvider;


public class TokenViewModel extends BaseViewModel<TokenNavigator> {

    public TokenViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        getTokenDetail("2",true);
    }


    public void getTokenDetail(String status ,boolean isLoading) {
        setIsLoading(isLoading);
        getCompositeDisposable().add(getDataManager()
                .doGetTokenStatus(getDataManager().getCurrentUserId(), status)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().getTokenDetail(response);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }


    public void onTokenSkip(String tokenId) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doSkipTokenReq(new TokenRequest.TokenStatus("3", getDataManager().getCurrentUserId()),tokenId,"")
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().getTokenDetail(response);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);

                }));
    }


    public void onGetSkipToken(String tokenId) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doSkipTokenReq(null,tokenId, getDataManager().getCurrentUserId())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().getTokenDetail(response);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);

                }));
    }

    public void onLogout() {
        getDataManager().setUserAsLoggedOut();
        setIsLoading(false);
        getNavigator().openLoginActivity();
    }


    public void onPrevious() {
        getNavigator().onSkip();
    }

    public void onNext() {
        getNavigator().onNext();
    }

    public void onSelectToken() {
        getNavigator().onSelectToken();
    }

    public void onCancel() {
        getNavigator().onCancel();
    }
}
