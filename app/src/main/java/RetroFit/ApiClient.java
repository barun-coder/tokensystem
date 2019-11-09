package RetroFit;

import com.displayfort.dftoken.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/***
 *
 */
public class ApiClient {


    public static final String BASE_URL = BuildConfig.BASE_URL;


    private static Retrofit posting_APIClient = null;

    // http://192.168.5.147
    public static Retrofit getClient() {
        if (posting_APIClient == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            posting_APIClient = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(getHttpClient())
                    .build();
        }
        return posting_APIClient;
    }

    public static Retrofit getExternalClient() {
        if (posting_APIClient == null) {
            posting_APIClient = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getHttpClient())
                    .build();
        }
        return posting_APIClient;
    }

    public static OkHttpClient getHttpClient() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(45, TimeUnit.SECONDS)
                .connectTimeout(45, TimeUnit.SECONDS)

                .build();
        return okHttpClient;
    }
}
