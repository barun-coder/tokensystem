
package com.displayfort.dftoken.data.model.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Husain  on 07/07/17.
 */

public final class TokenRequest {

    private TokenRequest() {
        // This class is not publicly instantiable
    }

    public static class TokenStatus {

        @Expose
        @SerializedName("is_active")
        private String is_active;

        @Expose
        @SerializedName("subcounter_id")
        private String subcounter_id;

        public TokenStatus(String is_active, String subcounter_id) {
            this.is_active = is_active;
            this.subcounter_id = subcounter_id;
        }
    }
}
