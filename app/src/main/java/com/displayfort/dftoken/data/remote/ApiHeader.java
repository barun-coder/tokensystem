
package com.displayfort.dftoken.data.remote;

import com.displayfort.dftoken.di.ApiInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Yogesh  on 07/07/17.
 */

@Singleton
public class ApiHeader {
    public static String CUSTOM_BASE_URL = "";
    private ProtectedApiHeader mProtectedApiHeader;

    private PublicApiHeader mPublicApiHeader;

    @Inject
    public ApiHeader(PublicApiHeader publicApiHeader, ProtectedApiHeader protectedApiHeader) {
        mPublicApiHeader = publicApiHeader;
        mProtectedApiHeader = protectedApiHeader;
    }

    public ProtectedApiHeader getProtectedApiHeader() {
        return mProtectedApiHeader;
    }

    public PublicApiHeader getPublicApiHeader() {
        return mPublicApiHeader;
    }

    public static final class ProtectedApiHeader {

        @Expose
        @SerializedName("token_code")
        private String mAccessToken;

        @Expose
        @SerializedName("api_key")
        private String mApiKey;


        @Expose
        @SerializedName("user_id")
        private String mUserId;

        public ProtectedApiHeader(String mApiKey, String mUserId, String mAccessToken) {
            this.mApiKey = mApiKey;
            this.mUserId = "" + mUserId;
            this.mAccessToken = mAccessToken;
        }

        public String getAccessToken() {
            return mAccessToken;
        }

        public void setAccessToken(String accessToken) {
            mAccessToken = accessToken;
        }

        public String getApiKey() {
            return mApiKey;
        }

        public void setApiKey(String apiKey) {
            mApiKey = apiKey;
        }

        public String getUserId() {
            return mUserId;
        }

        public void setUserId(Long mUserId) {
            this.mUserId = mUserId + "";
        }

        public void setUserId(String mUserId) {
            this.mUserId = mUserId;
        }
    }

    public static final class PublicApiHeader {

        @Expose
        @SerializedName("api_key")
        private String mApiKey;

        @Inject
        public PublicApiHeader(@ApiInfo String apiKey) {
            mApiKey = apiKey;
        }

        public String getApiKey() {
            return mApiKey;
        }

        public void setApiKey(String apiKey) {
            mApiKey = apiKey;
        }
    }
}
