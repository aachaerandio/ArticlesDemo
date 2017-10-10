package com.aachaerandio.articlesdemo.presenter;

import com.aachaerandio.articlesdemo.domain.GetArticleDetailsUseCase;
import com.aachaerandio.articlesdemo.model.Article;
import com.aachaerandio.articlesdemo.model.ArticleDetailsWrapper;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ArticleDetailsPresenter {

    private ArticleDetailsPresenter.UserInterface view;

    GetArticleDetailsUseCase getArticleDetails;
    private CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    public ArticleDetailsPresenter(GetArticleDetailsUseCase getArticleDetails) {
        this.getArticleDetails = getArticleDetails;
    }

    public void setView(ArticleDetailsPresenter.UserInterface view) {
        this.view = view;
    }

    public void loadDetails(String articleId) {
        Disposable disposable = getArticleDetails.execute(articleId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArticleDetailsWrapper>() {
                    @Override
                    public void accept(ArticleDetailsWrapper articleWrapper) throws Exception {
                        if (articleWrapper != null) {
                            view.showDetails(articleWrapper.getArticle());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable t) throws Exception {
                        view.showError(t.getMessage());
                    }
                });
        disposables.add(disposable);
    }

    public void dispose() {
        disposables.dispose();
    }

    public interface UserInterface {
        void showDetails(Article article);
        void showError(String error);
    }
}
