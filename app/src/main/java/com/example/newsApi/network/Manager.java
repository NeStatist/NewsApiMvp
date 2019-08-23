package com.example.newsApi.network;

import retrofit2.Call;

public class Manager implements RestApi {

    private RestApi restApi;

    public Manager() {
        restApi = NetManager.getInstance().getRestApi();
    }

    @Override
    public Call<com.example.newsApi.model.News> getSearchNews(String keyword, String apiKey) {
        return restApi.getSearchNews(keyword, apiKey);
    }
}
