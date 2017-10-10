package com.aachaerandio.articlesdemo.domain.di;

import com.aachaerandio.articlesdemo.ArticleDetailsPresenterTest;
import com.aachaerandio.articlesdemo.ArticleListPresenterTest;
import com.aachaerandio.articlesdemo.presenter.ArticleDetailsPresenter;
import com.aachaerandio.articlesdemo.presenter.ArticleListPresenter;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {TestArticleModule.class})
public interface TestArticleComponent {
    ArticleListPresenter articleListPresenter();
    ArticleDetailsPresenter articleDetailsPresenter();

    void inject(ArticleListPresenterTest presenter);
    void inject(ArticleDetailsPresenterTest presenter);
}
