
package com.displayfort.dftoken.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Husain  on 07/07/17.
 */

public final class LoginResponse {

    @Expose
    @SerializedName("status")
    private boolean status;
    @Expose
    @SerializedName("data")
    private UserDao data;

    @Expose
    @SerializedName("message")
    private String message;


    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        LoginResponse that = (LoginResponse) object;


        return message != null ? message.equals(that.message) : that.message == null;

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public UserDao getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }


    public static class UserDao {
        @Expose
        @SerializedName("logo")
        private String logo;
        @Expose
        @SerializedName("bg_image")
        private String bg_image;

        @Expose
        @SerializedName("company_name")
        private String company_name;
        @Expose
        @SerializedName("token_code")
        private String token_code;

        @Expose
        @SerializedName("user_id")
        private String user_id;

        @Expose
        @SerializedName("header_text")
        private String header_text;
        @Expose
        @SerializedName("sub_header_text")
        private String sub_header_text;

        @Expose
        @SerializedName("licence_id")
        private String licence_id;
        @Expose
        @SerializedName("licence_code")
        private String licence_code;
        @Expose
        @SerializedName("unique_id")
        private String unique_id;
        @Expose
        @SerializedName("is_active")
        private String is_active;
        @Expose
        @SerializedName("is_deleted")
        private String is_deleted;
        @Expose
        @SerializedName("created_on")
        private String created_on;
        @Expose
        @SerializedName("created_by")
        private String created_by;
        @Expose
        @SerializedName("modify_on")
        private String modify_on;
        @Expose
        @SerializedName("modify_by")
        private String modify_by;
        @Expose
        @SerializedName("sub_user_id")
        private String sub_user_id;


        public String getLicence_id() {
            return licence_id;
        }

        public String getLicence_code() {
            return licence_code;
        }

        public String getUnique_id() {
            return unique_id;
        }

        public String getIs_active() {
            return is_active;
        }

        public String getIs_deleted() {
            return is_deleted;
        }

        public String getCreated_on() {
            return created_on;
        }

        public String getCreated_by() {
            return created_by;
        }

        public String getModify_on() {
            return modify_on;
        }

        public String getModify_by() {
            return modify_by;
        }

        public String getSub_user_id() {
            return sub_user_id;
        }

        public String getToken_code() {
            return token_code;
        }

        public String getUser_id() {
            return user_id;
        }

        public String getLogo() {
            return logo;
        }

        public String getBg_image() {
            return bg_image;
        }

        public String getCompany_name() {
            return company_name;
        }

        public String getHeader_text() {
            return header_text;
        }

        public String getSub_header_text() {
            return sub_header_text;
        }
    }
}
