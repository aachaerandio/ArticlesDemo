package com.aachaerandio.articlesdemo.presenter.di;


import com.aachaerandio.articlesdemo.domain.di.ArticlesModule;
import com.aachaerandio.articlesdemo.presenter.ArticleListPresenter;
import com.aachaerandio.articlesdemo.service.di.ArticlesServiceModule;
import com.aachaerandio.articlesdemo.ui.ArticleListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ArticlesModule.class, ArticlesServiceModule.class})
public interface ArticlePresenterComponent {

    ArticleListPresenter articleListPresenter();

    void inject(ArticleListFragment articleListFragment);
}
