package com.example.newsApi.core;

import com.example.newsApi.network.RestApi;

public interface Injection {
    RestApi injectRepository();
}
