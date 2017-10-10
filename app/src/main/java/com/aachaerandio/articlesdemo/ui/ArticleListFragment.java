package com.aachaerandio.articlesdemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aachaerandio.articlesdemo.R;
import com.aachaerandio.articlesdemo.model.Article;
import com.aachaerandio.articlesdemo.presenter.ArticleListPresenter;

import java.util.List;

public class ArticleListFragment extends Fragment implements ArticleListPresenter.UserInterface {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_articles, container, false);

        return rootView;
    }

    @Override
    public void showArticles(List<Article> articles) {

    }

    @Override
    public void onArticleClicked(String articleId) {

    }

    @Override
    public void showErrorMessage(String error) {

    }

    @Override
    public void showLoading(boolean isLoading) {

    }
}
