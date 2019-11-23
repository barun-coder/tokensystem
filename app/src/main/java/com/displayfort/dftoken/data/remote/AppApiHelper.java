
package com.displayfort.dftoken.data.remote;

import com.displayfort.dftoken.data.model.api.request.LoginRequest;
import com.displayfort.dftoken.data.model.api.request.TokenRequest;
import com.displayfort.dftoken.data.model.api.response.TokenResponse;
import com.displayfort.dftoken.data.model.api.response.LoginResponse;
import com.displayfort.dftoken.data.model.api.response.LogoutResponse;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by Husain  on 07/07/17.
 */

@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader mApiHeader;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }


    @Override
    public Single<LogoutResponse> doLogoutApiCall() {
        //TODO
        return Rx2AndroidNetworking.post( ApiHeader.CUSTOM_BASE_URL + ApiEndPoint.ENDPOINT_SERVER_LOGIN)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(LogoutResponse.class);
    }

    @Override
    public Single<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request) {
        return Rx2AndroidNetworking.post( ApiHeader.CUSTOM_BASE_URL + ApiEndPoint.ENDPOINT_SERVER_LOGIN)
                .addHeaders(mApiHeader.getPublicApiHeader())
                .addApplicationJsonBody(request)
                .build()
                .getObjectSingle(LoginResponse.class);
    }

    @Override
    public Single<LoginResponse> doLicenseCheckApiCall(LoginRequest.LicenseRequest request) {
        return Rx2AndroidNetworking.post( ApiHeader.CUSTOM_BASE_URL + ApiEndPoint.ENDPOINT_SERVER_LICENSE)
                .addHeaders(mApiHeader.getPublicApiHeader())
                .addApplicationJsonBody(request)
                .build()
                .getObjectSingle(LoginResponse.class);
    }

    @Override
    public Single<TokenResponse> doSkipTokenReq(TokenRequest.TokenStatus tokenStatus, String tokenId, String subcounter_id) {
        if (tokenStatus == null) {
            return Rx2AndroidNetworking.get( ApiHeader.CUSTOM_BASE_URL + ApiEndPoint.ENDPOINT_SERVER_GET_NEW_TOKEN_SKIP + subcounter_id + ":token_display_name:" + tokenId)
                    .addHeaders(mApiHeader.getProtectedApiHeader())
                    .build()
                    .getObjectSingle(TokenResponse.class);
        } else {
            return Rx2AndroidNetworking.put( ApiHeader.CUSTOM_BASE_URL + ApiEndPoint.ENDPOINT_SERVER_TOKEN_SKIP + tokenId)
                    .addApplicationJsonBody(tokenStatus)
                    .addHeaders(mApiHeader.getProtectedApiHeader())
                    .build()
                    .getObjectSingle(TokenResponse.class);
        }
    }


    @Override
    public Single<TokenResponse> doGetTokenStatus(String subcounterid, String status) {
        return Rx2AndroidNetworking.get( ApiHeader.CUSTOM_BASE_URL + ApiEndPoint.ENDPOINT_SERVER_TOKEN_STATUS + "subcounter_id:" + subcounterid + ":is_active:" + status)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(TokenResponse.class);
    }


    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

}
