package RetroFit;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by prashantm on 10/27/2016.
 */
public interface ApiInterface {

    @GET
    Call<ResponseBody> getData(@Url String remainingURL, @Header("token_code") String session_key);

    @Headers({"Content-Type: application/json"})
    @POST
    Call<JsonElement> postData(@Url String remainingURL, @Body JsonObject jsonObject, @Header("token_code") String session_key);
}


