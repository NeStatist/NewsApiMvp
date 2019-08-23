package com.example.newsApi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.newsApi.R;
import com.example.newsApi.interfaces.ArticleListener;
import com.example.newsApi.model.Article;

public class RNAdapter extends ListAdapter<Article, RNAdapter.ViewHolder> {

    private ArticleListener itemClick;

    public RNAdapter(ArticleListener itemClick) {
        super(Article.DIFF_CALLBACK);
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view, itemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Article article = getItem(position);
        if(article != null) {
            holder.bindTo(article);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView title, author, source;
        private ImageView imageView;
        ArticleListener itemClick;

        ViewHolder(View itemView, ArticleListener itemClick) {
            super(itemView);
            title = itemView.findViewById(R.id.newsTitle);
            author = itemView.findViewById(R.id.author);
            source = itemView.findViewById(R.id.newsSource);
            imageView = itemView.findViewById(R.id.imageView);

            this.itemClick = itemClick;
            itemView.setOnClickListener(this);
        }

        void bindTo(Article article) {

            Glide.with(itemView.getContext())
                    .load(article.getUrlToImage())
                    .into(imageView);

            title.setText(article.getTitle());
            author.setText(article.getAuthor());
            source.setText(article.getSource().getName());

        }

        @Override
        public void onClick(View view) {
            itemClick.onItemNewsClick(getAdapterPosition());
        }
    }

}
