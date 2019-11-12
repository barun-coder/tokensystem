
package com.displayfort.dftoken.data.remote;

import com.displayfort.dftoken.BuildConfig;

/**
 * Created by Yogesh  on 07/07/17.
 */

public final class ApiEndPoint {
    public static final String ENDPOINT_SERVER_LICENSE = BuildConfig.BASE_URL + "login/licence";
    public static final String ENDPOINT_SERVER_TOKEN_SKIP = BuildConfig.BASE_URL + "token/token/";
    static final String ENDPOINT_SERVER_LOGIN = BuildConfig.BASE_URL + "login/ThirdParty";
    static final String ENDPOINT_SERVER_TOKEN_STATUS = BuildConfig.BASE_URL + "token/token/";
    static final String ENDPOINT_SERVER_GET_TOKEN_SKIP= BuildConfig.BASE_URL + "token/token/token_id:";
    static final String ENDPOINT_SERVER_GET_NEW_TOKEN_SKIP= BuildConfig.BASE_URL + "token/token/subcounter_id:";

    private ApiEndPoint() {
        // This class is not publicly instantiable
    }
}



