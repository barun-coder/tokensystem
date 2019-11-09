package com.displayfort.dftoken.data.model.api.retrofitUtils;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

/**
 * Created by Husain on 22/05/2019 12:00.
 * SportsInCode
 */
public interface UploadImageInterface {
    @Multipart
    @POST("uploads/user_profile")
    Call<UploadObject> uploadFile(@Part MultipartBody.Part file, @Header("session-key") String session_key);

    @Multipart
    @POST("uploads/user_cover")
    Call<UploadObject> uploadFileCover(@Part MultipartBody.Part file, @Header("session-key") String session_key);

    @Multipart
    @POST
    Call<UploadObject> uploadOrganFile(@Url String remainingURL, @Part MultipartBody.Part file, @Header("session-key") String session_key);

    @Multipart
    @POST
    Call<UploadObject> uploadOrganFileCover(@Url String remainingURL, @Part MultipartBody.Part file, @Header("session-key") String session_key);


}
