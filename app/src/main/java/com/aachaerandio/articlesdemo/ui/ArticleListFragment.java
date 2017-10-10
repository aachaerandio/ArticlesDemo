package com.aachaerandio.articlesdemo.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.aachaerandio.articlesdemo.R;
import com.aachaerandio.articlesdemo.model.Article;
import com.aachaerandio.articlesdemo.presenter.ArticleListPresenter;
import com.aachaerandio.articlesdemo.presenter.DaggerArticlePresenterComponent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ArticleListFragment extends Fragment implements ArticleListPresenter.UserInterface {

    @Inject
    ArticleListPresenter articleListPresenter;
    private ArticlesAdapter adapter;
    private List<Article> articles = new ArrayList<>();
    RecyclerView recyclerView;
    private Callback callback;
    CoordinatorLayout coordinatorLayout;
    ProgressBar loading;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerArticlePresenterComponent.builder().build().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_articles, container, false);
        recyclerView = rootView.findViewById(R.id.articles_recyclerView);
        coordinatorLayout = rootView.findViewById(R.id.coordinatorLayout);
        loading = rootView.findViewById(R.id.loading);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        adapter = new ArticlesAdapter(articles, this);
        recyclerView.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        articleListPresenter.setView(this);
        if(savedInstanceState == null) {
            articleListPresenter.loadArticles();
        }
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        articleListPresenter.dispose();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (Callback) context;
    }

    @Override
    public void onDetach() {
        callback = null;
        super.onDetach();
    }

    @Override
    public void showArticles(List<Article> articles) {
        this.articles.clear();
        this.articles.addAll(articles);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onArticleClicked(String articleId) {
        callback.onArticleClicked(articleId);
    }

    @Override
    public void showErrorMessage(String error) {
        String userError = getString(R.string.error_general) + error;
        Snackbar.make(coordinatorLayout, userError, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.error_action_retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        articleListPresenter.loadArticles();
                    }
                }).show();
    }

    @Override
    public void showLoading(boolean isLoading) {
        loading.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    public interface Callback
    {
        void onArticleClicked(String articleId);
    }
}
