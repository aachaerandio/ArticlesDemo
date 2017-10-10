package com.aachaerandio.articlesdemo.presenter;

import com.aachaerandio.articlesdemo.domain.GetArticlesUseCase;
import com.aachaerandio.articlesdemo.model.Article;
import com.aachaerandio.articlesdemo.model.ArticlesWrapper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ArticleListPresenter {

    private UserInterface view;
    GetArticlesUseCase getArticlesUseCase;
    private CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    public ArticleListPresenter(GetArticlesUseCase getArticlesUseCase) {
        this.getArticlesUseCase = getArticlesUseCase;
    }

    public void setView(ArticleListPresenter.UserInterface view)
    {
        this.view = view;
    }

    public void loadArticles() {
        view.showLoading(true);
        Disposable disposable = getArticlesUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArticlesWrapper>() {
                    @Override
                    public void accept(ArticlesWrapper result) throws Exception {
                        view.showLoading(false);
                        if (result != null) {
                            view.showArticles(result.getArticles());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable t) throws Exception {
                        view.showLoading(false);
                        view.showErrorMessage(t.getMessage());
                    }
                });
        disposables.add(disposable);
    }

    public void dispose() {
        disposables.dispose();
    }


    public interface UserInterface {
        void showArticles(List<Article> articles);
        void onArticleClicked(String articleId);
        void showErrorMessage(String error);
        void showLoading(boolean isLoading);
    }
}
