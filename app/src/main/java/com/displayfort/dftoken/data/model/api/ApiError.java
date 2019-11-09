package com.displayfort.dftoken.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Husain  on 07/07/19.
 */

public class ApiError {

    private int errorCode;

    @Expose
    @SerializedName("message")
    private String message = "Unknown Error.";

    @Expose
    @SerializedName("status_code")
    private String statusCode = "Success";

    public ApiError(int errorCode, String statusCode, String message) {
        this.errorCode = errorCode;
        this.statusCode = statusCode;
        this.message = message;
    }

    public ApiError(String message, int statusCode) {
        if (statusCode == 401) {

        } else {
            if (message != null) {
                try {
                    JSONObject jsonObject = new JSONObject(message);
                    this.statusCode = "" + statusCode;
                    this.message = jsonObject.optString("error");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        ApiError apiError = (ApiError) object;

        if (errorCode != apiError.errorCode) {
            return false;
        }
        if (statusCode != null ? !statusCode.equals(apiError.statusCode)
                : apiError.statusCode != null) {
            return false;
        }
        return message != null ? message.equals(apiError.message) : apiError.message == null;

    }

    @Override
    public int hashCode() {
        int result = errorCode;
        result = 31 * result + (statusCode != null ? statusCode.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
