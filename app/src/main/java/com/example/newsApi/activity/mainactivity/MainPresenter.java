package com.example.newsApi.activity.mainactivity;

import androidx.annotation.NonNull;
import com.example.newsApi.core.Injection;
import com.example.newsApi.interfaces.ContractSearch;
import com.example.newsApi.network.RestApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements ContractSearch.ArticlePresenter {

    private RestApi restApi;

    MainPresenter(Injection injection) {
        this.restApi = injection.injectRepository();
    }

    @Override
    public void prArticle(final ContractSearch.ArticleView articleView, String keyword) {

        String apiKey = "4ec64204b05f466f9e1a813070fd111a";
        restApi.getSearchNews(keyword, apiKey).enqueue(new Callback<com.example.newsApi.model.News>() {
            @Override
            public void onResponse(@NonNull Call<com.example.newsApi.model.News> call, @NonNull Response<com.example.newsApi.model.News> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    articleView.viewArticle(response.body().getArticles());
                } else {
                    try {
                        throw new Throwable(response.message());
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<com.example.newsApi.model.News> call, Throwable t) {
            try {
                throw new Throwable(t);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            }
        });
    }
}

