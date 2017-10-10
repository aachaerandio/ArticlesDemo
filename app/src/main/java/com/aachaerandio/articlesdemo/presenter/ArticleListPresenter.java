package com.aachaerandio.articlesdemo.presenter;

import com.aachaerandio.articlesdemo.domain.GetArticlesUseCase;
import com.aachaerandio.articlesdemo.model.Article;
import com.aachaerandio.articlesdemo.model.ArticlesWrapper;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ArticleListPresenter {

    private UserInterface view;

    GetArticlesUseCase getArticlesUseCase = new GetArticlesUseCase();

    public void loadArticles() {

        getArticlesUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArticlesWrapper>() {
                    @Override
                    public void accept(ArticlesWrapper result) throws Exception {
                        if (result != null) {
                            result.getArticles();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable t) throws Exception {
                    }
                });

    }


    public interface UserInterface {
        void showArticles(List<Article> articles);
        void onArticleClicked(String articleId);
        void showErrorMessage(String error);
        void showLoading(boolean isLoading);
    }
}
