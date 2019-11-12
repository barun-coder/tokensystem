
package com.displayfort.dftoken.data.remote;

import com.displayfort.dftoken.data.model.api.request.LoginRequest;
import com.displayfort.dftoken.data.model.api.request.TokenRequest;
import com.displayfort.dftoken.data.model.api.response.TokenResponse;
import com.displayfort.dftoken.data.model.api.response.LoginResponse;
import com.displayfort.dftoken.data.model.api.response.LogoutResponse;

import io.reactivex.Single;

/**
 * Created by Husain  on 07/07/17.
 */

public interface ApiHelper {

    Single<LogoutResponse> doLogoutApiCall();

    Single<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request);

    Single<LoginResponse> doLicenseCheckApiCall(LoginRequest.LicenseRequest request);

    Single<TokenResponse> doSkipTokenReq(TokenRequest.TokenStatus tokenStatus, String tokenId,String subcounter_id);

    Single<TokenResponse> doGetTokenStatus(String subcounterid, String status);


    ApiHeader getApiHeader();


}

