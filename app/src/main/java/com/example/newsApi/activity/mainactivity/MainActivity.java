package com.example.newsApi.activity.mainactivity;

import androidx.appcompat.app.AppCompatActivity;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;


import com.example.newsApi.R;
import com.example.newsApi.activity.secondactivity.SecondActivity;
import com.example.newsApi.adapter.RNAdapter;
import com.example.newsApi.core.Injection;
import com.example.newsApi.interfaces.ContractSearch;
import com.example.newsApi.interfaces.ArticleListener;
import com.example.newsApi.model.Article;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements ArticleListener, ContractSearch.ArticleView {

    private ContractSearch.ArticlePresenter articlePresenter;
    private RNAdapter rnAdapter;
    private String keyword;
    private List<Article> articleList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        rnAdapter = new RNAdapter(this);
        recyclerView.setAdapter(rnAdapter);

        articlePresenter = new MainPresenter((Injection) getApplicationContext());
    }


    @Override
    public void viewArticle(List<Article> article) {
        this.articleList = article;
        rnAdapter.submitList(article);
    }

    @Override
    public void onItemNewsClick(int id) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("url", articleList.get(id).getUrl());
            startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.search);

        assert searchManager != null;
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Поиск");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                    keyword = query;
                    articlePresenter.prArticle(MainActivity.this, keyword);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchMenuItem.getIcon().setVisible(false, false);
        return true;
    }
}
