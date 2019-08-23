package com.example.newsApi.interfaces;

import com.example.newsApi.model.Article;

import java.util.List;

public interface ContractSearch {

    interface ArticleView {
        void viewArticle(List<Article> article);
    }

    interface ArticlePresenter {
        void prArticle(ArticleView view, String keyword);
    }
}
