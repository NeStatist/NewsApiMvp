package com.example.newsApi.network;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class NetManager {

    private static RestApi restApi;
    private static NetManager INSTANCE;

    static NetManager getInstance() {
        if (INSTANCE == null) {
            synchronized ((NetManager.class)) {
                if (INSTANCE == null) {
                    INSTANCE = new NetManager();
                }
            }
        }
        return INSTANCE;
    }

    private OkHttpClient okHttpClient = new OkHttpClient()
            .newBuilder()
            .connectTimeout(3, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)
            .readTimeout(3, TimeUnit.MINUTES)
            .addInterceptor(new ResponseInterceptor()).build();

    private Retrofit getRetofit() {
        String URL_APINEWS = "https://newsapi.org/";
        return new Retrofit.Builder()
                .baseUrl(URL_APINEWS)
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .client(okHttpClient)
                .build();
    }

    class ResponseInterceptor implements Interceptor {

        @NotNull
        @Override
        public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
            Response response = chain.proceed(chain.request());

            Response.Builder builder;

            builder = response.newBuilder()
                    .addHeader("SerialResponse-Type:", "application/x-www-form-urlencoded");

            return builder.build();
        }
    }

    final RestApi getRestApi() {
        if(restApi == null) {
            restApi = getRetofit().create(RestApi.class);
        }
        return restApi;
    }

    private Gson createGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .setLenient()
                .create();
    }
}
