package com.aachaerandio.articlesdemo.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aachaerandio.articlesdemo.R;
import com.aachaerandio.articlesdemo.model.Article;
import com.aachaerandio.articlesdemo.presenter.ArticleListPresenter;

import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {
    private List<Article> articles;
    private final ArticleListPresenter.UserInterface view;

    public ArticlesAdapter(List<Article> articles, ArticleListPresenter.UserInterface view) {

        this.articles = articles;
        this.view = view;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_article, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Article article = articles.get(position);
        holder.title.setText(article.title);
        holder.subtitle.setText(article.subtitle);
        holder.date.setText(article.date);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArticlesAdapter.this.view.onArticleClicked(article.getId().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return (articles == null) ? 0 : articles.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView title, subtitle, date;

        public ViewHolder(View v)
        {
            super(v);
            title = v.findViewById(R.id.title);
            subtitle = v.findViewById(R.id.subtitle);
            date = v.findViewById(R.id.date);
        }
    }
}
