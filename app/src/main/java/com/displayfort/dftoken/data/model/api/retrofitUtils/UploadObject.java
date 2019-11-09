package com.displayfort.dftoken.data.model.api.retrofitUtils;

/**
 * Created by Husain on 22/05/2019 12:00.
 * SportsInCode
 */
public class UploadObject {
    private String success;

    public UploadObject(String success) {
        this.success = success;
    }

    public String getSuccess() {
        return success;
    }
}
