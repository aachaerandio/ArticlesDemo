package com.aachaerandio.articlesdemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aachaerandio.articlesdemo.Constants;
import com.aachaerandio.articlesdemo.R;
import com.aachaerandio.articlesdemo.model.Article;
import com.aachaerandio.articlesdemo.presenter.ArticleDetailsPresenter;
import com.aachaerandio.articlesdemo.presenter.di.DaggerArticlePresenterComponent;

import javax.inject.Inject;

public class ArticleDetailsFragment extends Fragment implements ArticleDetailsPresenter.UserInterface {

    @Inject
    ArticleDetailsPresenter articleDetailsPresenter;

    private String articleId;
    protected TextView title, subtitle, description, date;
    CoordinatorLayout coordinatorLayout;
    ProgressBar loading;

    public ArticleDetailsFragment() {
    }

    public static ArticleDetailsFragment getInstance(String articleId) {
        Bundle args = new Bundle();
        args.putString(Constants.ARTICLE_ID, articleId);
        ArticleDetailsFragment articleDetailsFragment = new ArticleDetailsFragment();
        articleDetailsFragment.setArguments(args);

        return articleDetailsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerArticlePresenterComponent.builder().build().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_article_details, container, false);
        title = rootView.findViewById(R.id.article_title);
        subtitle = rootView.findViewById(R.id.article_subtitle);
        description = rootView.findViewById(R.id.article_description);
        date = rootView.findViewById(R.id.article_date);
        coordinatorLayout = rootView.findViewById(R.id.coordinatorLayout);
        loading = rootView.findViewById(R.id.loading);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(savedInstanceState == null) {
            articleId = getArguments().getString(Constants.ARTICLE_ID);
        } else {
            articleId = savedInstanceState.getString(Constants.ARTICLE_ID);
        }
        articleDetailsPresenter.setView(this);
        articleDetailsPresenter.loadDetails(articleId);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(Constants.ARTICLE_ID, articleId);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        articleDetailsPresenter.dispose();
    }

    @Override
    public void showDetails(Article article) {
        title.setText(article.getTitle());
        subtitle.setText(article.getSubtitle());
        description.setText(article.getBody());
        date.setText(article.getDate());
    }

    @Override
    public void showError(String error) {
        String userError = getString(R.string.error_general) + error;
        Snackbar.make(coordinatorLayout, userError, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.error_action_retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        articleDetailsPresenter.loadDetails(articleId);
                    }
                }).show();
    }
}
