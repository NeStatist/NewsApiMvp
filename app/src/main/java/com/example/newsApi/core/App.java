package com.example.newsApi.core;

import android.app.Application;

import com.example.newsApi.network.Manager;
import com.example.newsApi.network.RestApi;

public class App extends Application implements Injection{

    private RestApi restApi;

    @Override
    public void onCreate() {
        super.onCreate();
        restApi = new Manager();

    }

    @Override
    public RestApi injectRepository() {
        return restApi;
    }
}
