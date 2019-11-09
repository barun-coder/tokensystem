
package com.displayfort.dftoken.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Husain  on 07/07/17.
 */

public final class TokenResponse {

    @Expose
    @SerializedName("status")
    private boolean status;
    @Expose
    @SerializedName("data")
    private List<TokenDao> data;

    @Expose
    @SerializedName("total")
    private int total;


    public static class TokenDao {

        @Expose
        @SerializedName("token_id")
        private int token_id;


        @Expose
        @SerializedName("token_display_name")
        private String token_display_name;

        @Expose
        @SerializedName("counter_name")
        private String counter_name;
        @Expose
        @SerializedName("subcounter_name")
        private String subcounter_name;
        @Expose
        @SerializedName("counter_id")
        private String counter_id;

        @Expose
        @SerializedName("subcounter_id")
        private String subcounter_id;

        public int getToken_id() {
            return token_id;
        }

        public String getCounter_name() {
            return counter_name;
        }

        public String getSubcounter_name() {
            return subcounter_name;
        }

        public String getCounter_id() {
            return counter_id;
        }

        public String getSubcounter_id() {
            return subcounter_id;
        }

        public String getToken_display_name() {
            return token_display_name;
        }
    }

    public List<TokenDao> getData() {
        return data;
    }

    public boolean isStatus() {
        return status;
    }

    public int getTotal() {
        return total;
    }
}
