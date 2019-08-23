package com.example.newsApi.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApi {

    @GET("/v2/everything")
    Call<com.example.newsApi.model.News> getSearchNews(

            @Query("q") String keyword,
            @Query("apiKey") String apiKey

    );
}
